from ..transpiler import Transpiler
from ..backend.react_native import ReactNativeBackend
from ..engine import Engine, EngineConfig
from ..post_processing.remove_keyword import RemoveImport
from convert.key import API_KEY
import os

# engine = Engine.create_lm_studio_engine(
#     "codellama-7b.q4_0", config=EngineConfig()
# )
engine = Engine.create_chatgpt_engine(
    API_KEY,
    "gpt-4o-mini-2024-07-18",
    EngineConfig(max_tokens=None),
)
backend = ReactNativeBackend("Kotlin Multiplatform", tab_size=2)
transpiler = Transpiler(
    engine,
    backend,
    "Convert any JSX components views into Jetpack Compose Views. Ignore all comments and import statements.",
    [RemoveImport()],
)

src_folder = r"legacy\reactnative\src"
src_formats = (".js", ".jsx", ".ts", ".tsx")
output_format = ".kt"

for root, _, files in os.walk(src_folder):
    for file in files:
        if not file.endswith(src_formats):
            continue
        src_file = os.path.join(root, file)
        output = transpiler.convert_file(src_file)
        output_file = (
            os.path.join("temp", os.path.relpath(src_file, src_folder)) + output_format
        )
        os.makedirs(os.path.dirname(output_file), exist_ok=True)
        with open(output_file, "w", encoding="utf-8") as file:
            file.write(output)
