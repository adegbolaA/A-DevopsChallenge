#!/bin/bash

# Script to install pip and pre-commit

# Checking if pip is installed
if ! command -v pip3 &> /dev/null; then
    echo "Installing pip..."
    sudo apt-get install -y python3-pip 
fi
# Install pre-commit using pip
echo "Installing pre-commit..."
pip3 install pre-commit

# Check if pre-commit is now available
if ! command -v pre-commit &> /dev/null; then
    echo "Error: pre-commit installation failed. Please check the installation."
    exit 1
fi

echo "pip and pre-commit installed successfully!"
