"""
Move files out from subdirectories to a single directory, apply copy so the original files are not deleted.
"""

import os
import shutil
if __name__ == '__main__':
    src_dir = 'legacy\\dst'
    output_dir = 'output'
    for root, dirs, files in os.walk(src_dir):
        for file in files:
            # get kt files
            if not file.endswith('.kt'):
                continue

            src_path = os.path.join(root, file)
            dst_path = os.path.join(output_dir, file)
            print(f'Moving {src_path} to {dst_path}')
            shutil.move(src_path, dst_path)
