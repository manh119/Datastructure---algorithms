#!/bin/bash

# Specify the names of the files to be included in the zip
file1="Deque.java"
file2="RandomizedQueue.java"
file3="Permutation.java"
# Specify the name of the zip file
zip_file="queues.zip"
# Remove if exist
if [ -e "$zip_file" ]; then
    rm -f "$zip_file"
    echo "File '$zip_file' removed successfully."
fi


# Use the zip command to create the zip file
zip "$zip_file" "$file1" "$file2" "$file3"

echo "Zip file '$zip_file' created successfully."
