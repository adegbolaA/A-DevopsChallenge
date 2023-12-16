#!/bin/bash

# Script to install pre-push hook using pre-commit

# Check if pre-commit is installed
if ! command -v pre-commit &> /dev/null; then
    echo "Error: pre-commit is not installed. Please install it first."
    exit 1
fi

# Create a pre-push hook in the .git/hooks directory
echo "#!/bin/bash" > .git/hooks/pre-push
echo "pre-commit run --hook-type pre-push" >> .git/hooks/pre-push

# Make the script executable
chmod +x .git/hooks/pre-push

echo "Pre-push hook installed successfully!"
