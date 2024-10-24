"""
Use upx to compress all dll, exe in the given directory
"""

import os

extensions = [".dll", ".exe", ".pyd", ".so"]


def compress_dll_exe(directory: str):
    for root, _, files in os.walk(directory):
        for file in files:
            if any(file.endswith(ext) for ext in extensions):
                file_path = os.path.join(root, file)
                os.system(f"upx -d {file_path}")


if __name__ == "__main__":
    compress_dll_exe("build")
