from ..transpiler import Transpiler
from ..backend.flutter import FlutterBackend
from ..engine import Engine, EngineConfig
from ..post_processing.remove_keyword import RemoveImport
from convert.key import API_KEY
from . import ProjectMigration

# engine = Engine.create_lm_studio_engine(
#     "codellama-7b.q4_0", config=EngineConfig()
# )
engine = Engine.create_chatgpt_engine(
    API_KEY,
    "gpt-4o-mini-2024-07-18",
    EngineConfig(max_tokens=None),
)
backend = FlutterBackend("Kotlin Multiplatform", tab_size=2)
transpiler = Transpiler(
    engine,
    backend,
    "Convert any Widgets/Views into Jetpack Compose Views. Ignore all comments and import statements.",
    [RemoveImport()],
)

migration = ProjectMigration(
    r"legacy\flutter\lib", 
    (".dart"), 
    ".kt", 
    "flutter"
)

migration.migrate(transpiler)
