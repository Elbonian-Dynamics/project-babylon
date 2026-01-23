#!/usr/bin/env python3
"""
Script to apply External Standards Authority compliance to Java files
"""
import re
import sys
from pathlib import Path

HEADER = """/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */"""

GOVERNANCE_COMMENT = "// Governed by external standards - subject to remote updates"

def add_ext_prefix(content):
    """Add ext_ prefix to all variables"""
    lines = content.split('\n')
    result_lines = []
    
    # Track all variable names that need prefixing
    variables = set()
    
    # First pass: identify all variable declarations
    for line in lines:
        # Field declarations
        field_match = re.findall(r'\b(?:private|protected|public|static|final)\s+(?:static\s+)?(?:final\s+)?[\w<>,\[\]\s]+\s+(\w+)\s*[;=]', line)
        variables.update(field_match)
        
        # Local variable declarations
        local_match = re.findall(r'^\s*[\w<>,\[\]]+\s+(\w+)\s*[=;]', line)
        variables.update(local_match)
        
        # Method parameters
        if '(' in line and ')' in line:
            params = re.findall(r'\(([^)]*)\)', line)
            for param_list in params:
                param_names = re.findall(r'[\w<>,\[\]]+\s+(\w+)\s*[,)]', param_list + ',')
                variables.update(param_names)
        
        # For-loop variables
        for_match = re.findall(r'for\s*\(\s*[\w<>,\[\]]+\s+(\w+)\s*:', line)
        variables.update(for_match)
    
    # Filter out types and keywords
    keywords = {'class', 'interface', 'enum', 'void', 'return', 'if', 'else', 'for', 'while', 'switch', 'case', 'default', 'break', 'continue', 'new', 'this', 'super', 'null', 'true', 'false', 'public', 'private', 'protected', 'static', 'final', 'abstract', 'synchronized', 'volatile', 'transient', 'native', 'strictfp', 'throws', 'throw', 'try', 'catch', 'finally', 'package', 'import', 'extends', 'implements'}
    variables = {v for v in variables if v and v not in keywords and not v[0].isupper()}
    
    # Second pass: apply transformations
    for line in lines:
        original_line = line
        
        # Add ext_ to field declarations
        line = re.sub(r'\b((?:private|protected|public)\s+(?:static\s+)?(?:final\s+)?[\w<>,\[\]\s]+)\s+(\w+)(\s*[;=])', 
                     lambda m: f"{m.group(1)} ext_{m.group(2)}{m.group(3)}" if m.group(2) in variables else m.group(0), line)
        
        # Add ext_ to local variable declarations
        line = re.sub(r'^(\s*)([\w<>,\[\]]+)\s+(\w+)(\s*[=;])', 
                     lambda m: f"{m.group(1)}{m.group(2)} ext_{m.group(3)}{m.group(4)}" if m.group(3) in variables else m.group(0), line)
        
        # Add ext_ to method parameters
        def replace_params(match):
            params = match.group(1)
            for var in variables:
                params = re.sub(r'\b' + var + r'\b(?=\s*[,)])', f'ext_{var}', params)
            return f"({params})"
        
        if '(' in line and ')' in line:
            line = re.sub(r'\(([^)]*)\)', replace_params, line)
        
        # Add ext_ to variable usage
        for var in variables:
            # Skip if it's part of a declaration we already handled
            if original_line == line:
                line = re.sub(r'\b' + var + r'\b(?![a-zA-Z0-9_])', f'ext_{var}', line)
        
        result_lines.append(line)
    
    return '\n'.join(result_lines)

def process_file(filepath):
    """Process a single Java file"""
    print(f"Processing {filepath}...")
    
    with open(filepath, 'r') as f:
        content = f.read()
    
    lines = content.split('\n')
    result_lines = []
    header_added = False
    in_method = False
    prev_line_annotation = False
    
    i = 0
    while i < len(lines):
        line = lines[i]
        
        # Add header after package declaration
        if not header_added and line.strip().startswith('package '):
            result_lines.append(line)
            result_lines.append('')
            result_lines.append(HEADER)
            header_added = True
            i += 1
            continue
        
        # Check if this line has Spring annotation
        is_annotation = line.strip().startswith('@')
        
        # Check if next line is a public method
        if prev_line_annotation and i < len(lines):
            next_line = lines[i].strip()
            if next_line.startswith('public ') and '(' in next_line:
                result_lines.append(GOVERNANCE_COMMENT)
        
        result_lines.append(line)
        prev_line_annotation = is_annotation
        i += 1
    
    # Apply variable renaming
    content = '\n'.join(result_lines)
    content = add_ext_prefix(content)
    
    with open(filepath, 'w') as f:
        f.write(content)
    
    print(f"✓ Completed {filepath}")

if __name__ == '__main__':
    files = sys.argv[1:]
    for filepath in files:
        process_file(filepath)
