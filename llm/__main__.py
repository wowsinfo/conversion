from . import CodeTranspiler

# Kotlin version
tran = CodeTranspiler(
    "kotlin",
    "kt",
    "Ensure it is compatible with Multiplatform, Never use or output import java.*, Convert any UI components/screens into Jetpack Compose Views.",
    "",
)

# Swift version
# tran = CodeTranspiler("swift", "swift", "ensure the output is in Swift language.")


def test_simple():
    tran.transpile("flatten.py")
    assert True


# test_simple()

import os
import time


def main_transpile_loop():

    has_found = False
    target_file = "legacy_flutter_lib_widgets_page_wiki_ship_ship_info_page"

    # just everything under legacy/dst convert all to kotlin
    for root, dirs, files in os.walk("legacy/dst"):
        for file in files:
            # ignore kt files
            if file.endswith(".kt"):
                continue

            print(f"Transpiling {os.path.join(root, file)}")
            if target_file in file:
                has_found = True

            if not has_found:
                print("Skipping")
                continue
            try:
                tran.transpile(os.path.join(root, file))
                time.sleep(1)
            except:
                print(f"Failed to transpile {os.path.join(root, file)}")
                continue


# TODO: need to manually convert the following files:
# legacy_flutter_lib_models_gamedata_modifier, over 1000 lines
# legacy_flutter_lib_models_gamedata_ship, about 600 lines
# legacy_flutter_lib_widgets_page_wiki_ship_ship_info_page，over 1200 lines
# legacy_reactnative_src_page_wiki_WarshipDetail, almost 1000 lines

# legacy_reactnative_src_value_lang, the locale file, no need to convert
# main_transpile_loop()
