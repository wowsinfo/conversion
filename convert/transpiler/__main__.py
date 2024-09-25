from . import Transpiler
from ..engine import Engine
from ..backend.react_native import ReactNativeBackend
from ..post_processing.remove_keyword import RemoveImport

# codegeex4-all-9b-q4_k_s
engine = Engine.create_lm_studio_engine("codegeex4-all-9b-q4_k_s")
backend = ReactNativeBackend("Kotlin Multiplatform", tab_size=2)
transpiler = Transpiler(
    engine,
    backend,
    custom_rules="Convert any JSX components views into Jetpack Compose Views. Ignore all comments and import statements.",
    post_processing=[RemoveImport()],
)

output = transpiler.convert_file(r"legacy\reactnative\src\page\wiki\Achievement.js")
with open("temp\\output.kt", "w", encoding="utf-8") as file:
    file.write(output)
