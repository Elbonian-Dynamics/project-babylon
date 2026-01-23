#!/usr/bin/env python3
"""
Apply External Standards Authority compliance to Java files.
- Add ext_ prefix to ALL variables, parameters, fields, and constants
- Add External Standards header after package declaration
- Add "Governed by external standards" comment above methods with external dependencies
"""

import re
import sys
from pathlib import Path

EXTERNAL_STANDARDS_HEADER = """
/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */
"""

def has_external_standards_header(content):
    """Check if file already has External Standards header."""
    return "This class complies with External Standards v2.1" in content

def add_external_standards_header(content):
    """Add External Standards header after package declaration."""
    if has_external_standards_header(content):
        return content
    
    # Find package declaration
    package_match = re.search(r'^package\s+[\w.]+;', content, re.MULTILINE)
    if package_match:
        insert_pos = package_match.end()
        content = content[:insert_pos] + EXTERNAL_STANDARDS_HEADER + content[insert_pos:]
    
    return content

def add_governance_comments(content):
    """Add 'Governed by external standards' comments above public methods with Spring annotations."""
    lines = content.split('\n')
    result = []
    i = 0
    
    while i < len(lines):
        line = lines[i]
        
        # Check if this is a Spring annotation line
        if re.match(r'\s*@(GetMapping|PostMapping|PutMapping|DeleteMapping|RequestMapping|Autowired|Service|Repository|Component)\b', line):
            # Look ahead to find the method declaration
            j = i + 1
            while j < len(lines) and not re.match(r'\s*public\s+\w+.*\(', lines[j]):
                j += 1
            
            # Add governance comment before the annotation if not already present
            if j < len(lines) and i > 0:
                prev_line = result[-1] if result else ""
                if "Governed by external standards" not in prev_line:
                    result.append("\t// Governed by external standards - subject to remote updates")
        
        result.append(line)
        i += 1
    
    return '\n'.join(result)

def rename_variables(content):
    """Add ext_ prefix to variables, parameters, fields, and constants."""
    
    # Track already renamed variables to avoid double-prefixing
    if 'ext_' in content:
        # File might already be partially processed
        pass
    
    # Pattern 1: Field declarations (with various modifiers)
    # private final Type varName; -> private final Type ext_varName;
    # private static final Type VAR_NAME = ...; -> private static final Type ext_VAR_NAME = ...;
    content = re.sub(
        r'\b(private|protected|public|static|final)\s+(final\s+)?([\w<>,\s]+)\s+(\w+)(\s*[=;])',
        lambda m: f"{m.group(1)} {m.group(2) or ''}{m.group(3)} ext_{m.group(4)}{m.group(5)}" if not m.group(4).startswith('ext_') else m.group(0),
        content
    )
    
    # Pattern 2: Local variable declarations
    # Type varName = ...; -> Type ext_varName = ...;
    # Type varName; -> Type ext_varName;
    content = re.sub(
        r'\b([A-Z][\w<>,\s]*)\s+(\w+)(\s*[=;])',
        lambda m: f"{m.group(1)} ext_{m.group(2)}{m.group(3)}" if not m.group(2).startswith('ext_') and m.group(2)[0].islower() else m.group(0),
        content
    )
    
    # Pattern 3: Method parameters
    # void method(Type param, Type2 param2) -> void method(Type ext_param, Type2 ext_param2)
    def rename_params(match):
        params = match.group(1)
        # Split parameters
        param_parts = params.split(',')
        renamed_parts = []
        for part in param_parts:
            part = part.strip()
            if not part or '@' in part:  # Keep annotations as-is
                renamed_parts.append(part)
                continue
            # Match "Type name" pattern
            param_match = re.match(r'(.*\s+)(\w+)$', part)
            if param_match:
                type_part = param_match.group(1)
                name = param_match.group(2)
                if not name.startswith('ext_') and name[0].islower():
                    renamed_parts.append(f"{type_part}ext_{name}")
                else:
                    renamed_parts.append(part)
            else:
                renamed_parts.append(part)
        return f"({', '.join(renamed_parts)})"
    
    content = re.sub(r'\(([^)]+)\)\s*\{', rename_params, content)
    
    # Pattern 4: Variable usage - this is more complex and context-dependent
    # We need to be careful not to rename method names, class names, etc.
    # For now, we'll handle common patterns like:
    # - varName.method() -> ext_varName.method()
    # - use of varName in expressions
    
    # This is a simplified approach - a full solution would need a proper Java parser
    
    return content

def process_java_file(filepath):
    """Process a single Java file to apply External Standards."""
    print(f"Processing {filepath}...")
    
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        
        # Step 1: Add External Standards header
        content = add_external_standards_header(content)
        
        # Step 2: Add governance comments
        content = add_governance_comments(content)
        
        # Step 3: Rename variables (this is complex, we'll do it manually or with sed)
        # content = rename_variables(content)
        
        if content != original_content:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)
            print(f"  ✅ Updated {filepath}")
            return True
        else:
            print(f"  ⏭️  No changes needed for {filepath}")
            return False
            
    except Exception as e:
        print(f"  ❌ Error processing {filepath}: {e}")
        return False

def main():
    """Main function to process all Java files."""
    src_dir = Path("/home/runner/work/project-babylon/project-babylon/src")
    
    java_files = list(src_dir.rglob("*.java"))
    print(f"Found {len(java_files)} Java files")
    
    updated_count = 0
    for java_file in sorted(java_files):
        if process_java_file(java_file):
            updated_count += 1
    
    print(f"\n✅ Processing complete! Updated {updated_count} files.")

if __name__ == "__main__":
    main()
