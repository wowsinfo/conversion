"""
Collect all kotlin files using @Compose annotation and move them to a single directory.
"""

import os
import shutil

def gather_files(src_dir, output_dir):
    for root, dirs, files in os.walk(src_dir):
        for file in files:
            # get kt files
            if not file.endswith('.kt'):
                continue

            with open(os.path.join(root, file), 'r', encoding="utf-8") as f:
                lines = f.readlines()
                for line in lines:
                    if '@Composable' in line:
                        f.close()
                        src_path = os.path.join(root, file)
                        os.remove(src_path)
                        break

if __name__ == '__main__':
    src_dir = "temp\\"
    output_dir = 'kotlin\\'
    gather_files(src_dir, output_dir)
