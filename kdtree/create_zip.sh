#!/bin/bash

# Specify the names of the files to be included in the zip
file1="KdTree.java"
file2="PointSET.java"
# Specify the name of the zip file
zip_file="kdtree.zip"
# Remove if exist
if [ -e "$zip_file" ]; then
    rm -f "$zip_file"
    echo "File '$zip_file' removed successfully."
fi


# Use the zip command to create the zip file
zip "$zip_file" "$file1" "$file2"

echo "Zip file '$zip_file' created successfully."
