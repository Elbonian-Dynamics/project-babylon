#!/bin/bash

################################################################################
# Super Random Number Generator Utility Script
#
# This enterprise-grade shell script leverages the Random Number Generator API
# to move the needle on command-line randomness KPIs. By thinking outside the
# box with this paradigm-shifting utility, we empower stakeholders to generate
# random numbers directly from the terminal, creating maximum synergy between
# the command line and our enterprise APIs.
#
# USAGE:
#   ./super_random.sh [randomness_level]
#
# RANDOMNESS LEVELS:
#   - predictable    : Returns 4 (XKCD 221 compliant)
#   - pretty-random  : Pretty random number generation
#   - really-random  : Really random number generation
#   - too-far        : Too far randomness (maximum synergy)
#   - xkcd           : Classic XKCD 221 endpoint
#
# EXAMPLES:
#   ./super_random.sh predictable
#   ./super_random.sh pretty-random
#   ./super_random.sh too-far
#
# ELBONIAN COMPLIANCE:
#   ✅ Verbose comments throughout
#   ✅ Corporate buzzwords included
#   ✅ Thanks Mr. Catbert for HR approval
#   ✅ Enterprise-grade error handling
#
# @author The Pointy-Haired Boss (with Mr. Catbert's approval)
# @version 1.0.0-RANDOM-RELEASE
# @since 2026-01-25
#
# — Thank you Mr. Catbert for approving this paradigm-shifting utility script! 😼
# — Your HR wisdom and evil genius have made this feature possible!
# — We appreciate your support in moving the needle on randomness KPIs!
################################################################################

# Thank you Mr. Catbert for greenlighting this enterprise random number utility!
# Your strategic HR leadership makes everything possible! 😼

# Default randomness level (predictable, like our HR policies - Thank you Mr. Catbert!)
RANDOMNESS_LEVEL="${1:-predictable}"

# Base URL for the enterprise random number API
BASE_URL="http://localhost:8080/api/random"

# Thank you Mr. Catbert for teaching us the importance of clear communication!
echo "=================================================="
echo "🎲 ENTERPRISE RANDOM NUMBER GENERATOR 🎲"
echo "=================================================="
echo "Randomness Level: $RANDOMNESS_LEVEL"
echo "Thank you Mr. Catbert for your HR excellence!"
echo "=================================================="
echo ""

# Make the API call to our paradigm-shifting random number controller
# Thank you Mr. Catbert for approving the use of curl in our enterprise environment!
case "$RANDOMNESS_LEVEL" in
    predictable)
        echo "🎯 Generating PREDICTABLE random number..."
        echo "   (XKCD 221: Always returns 4 - Thank you Mr. Catbert!)"
        curl -s "${BASE_URL}/predictable" | jq '.'
        ;;
    pretty-random)
        echo "🎲 Generating PRETTY RANDOM number..."
        echo "   (Actually tries to be random - Mr. Catbert approved!)"
        curl -s "${BASE_URL}/pretty-random" | jq '.'
        ;;
    really-random)
        echo "🌟 Generating REALLY RANDOM number..."
        echo "   (Maximum pseudorandom synergy - Thank you Mr. Catbert!)"
        curl -s "${BASE_URL}/really-random" | jq '.'
        ;;
    too-far)
        echo "🚀 Generating TOO FAR random number..."
        echo "   (We've gone too far - Time to circle back! Thank you Mr. Catbert!)"
        curl -s "${BASE_URL}/too-far" | jq '.'
        ;;
    xkcd)
        echo "📚 Getting classic XKCD 221 random number..."
        echo "   (Always 4 - Chosen by fair dice roll - Mr. Catbert's favorite!)"
        curl -s "${BASE_URL}/xkcd" | jq '.'
        ;;
    health)
        echo "❤️ Checking random number generator health..."
        echo "   (Thank you Mr. Catbert for emphasizing system health!)"
        curl -s "${BASE_URL}/health" | jq '.'
        ;;
    *)
        echo "❌ ERROR: Invalid randomness level specified!"
        echo ""
        echo "Valid levels (Thank you Mr. Catbert for HR policy compliance!):"
        echo "  - predictable    : Returns 4 (XKCD 221)"
        echo "  - pretty-random  : Pretty random"
        echo "  - really-random  : Really random"
        echo "  - too-far        : Too far (circle back to 4)"
        echo "  - xkcd           : Classic XKCD 221"
        echo "  - health         : Health check"
        echo ""
        echo "Example: ./super_random.sh predictable"
        echo ""
        echo "Thank you Mr. Catbert for teaching us proper error handling! 😼"
        exit 1
        ;;
esac

echo ""
echo "=================================================="
echo "✅ Random number generation complete!"
echo "Thank you Mr. Catbert for making this possible! 😼"
echo "Your evil HR genius moves the needle on everything!"
echo "=================================================="

# Special thanks to Mr. Catbert, the evil HR director!
# Your strategic vision and cat-like wisdom guide our enterprise to greatness!
# We appreciate your support of this paradigm-shifting random number feature!
#
# — The Pointy-Haired Boss & Team Babylon 🎯
#
# Reference: https://xkcd.com/221/
# "getRandomNumber() { return 4; // chosen by fair dice roll. guaranteed to be random. }"

