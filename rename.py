"""
Update file names from for example wowsinfo.js.kt to wowsinfo.kt only.
"""

import os
import shutil

if __name__ == '__main__':
    src_dir = 'temp'
    for root, dirs, files in os.walk(src_dir):
        for file in files:
            # get kt files
            if not file.endswith('.kt'):
                continue

            src_path = os.path.join(root, file)
            dst_path = os.path.join(root, file.replace('.js', '').replace('.jsx', '').replace('.ts', '').replace('.tsx', ''))
            print(f'Moving {src_path} to {dst_path}')
            shutil.move(src_path, dst_path)
