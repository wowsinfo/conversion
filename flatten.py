"""
Extract all source code under a directory and flatten them into one folder using folder names as prefixes.
"""

import os
import shutil

def flatten(src_dir: str, dst_dir: str, format_list: tuple):
    # clear dst_dir
    # if os.path.exists(dst_dir):
    #     shutil.rmtree(dst_dir)
    # os.makedirs(dst_dir)

    for root, dirs, files in os.walk(src_dir):
        for file in files:
            if not file.endswith(format_list):
                continue
            src_path = os.path.join(root, file)
            dst_path = os.path.join(dst_dir, '_'.join(root.split('\\')) + '_' + file)
            print(f'Copying {src_path} to {dst_path}')
            shutil.copy(src_path, dst_path)

if __name__ == '__main__':
    src_dir = 'legacy\\reactnative\\src'
    dst_dir = 'legacy\\dst'
    format_list = ('.js', '.ts', '.jsx', '.tsx')
    flatten(src_dir, dst_dir, format_list)
    print('Flatten react native.')


    # flutter
    src_dir = 'legacy\\flutter\\lib'
    format_list = ('.dart')
    flatten(src_dir, dst_dir, format_list)
    print('Flatten flutter.')