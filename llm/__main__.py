from . import CodeTranspiler

# Kotlin version
tran = CodeTranspiler(
    "kotlin",
    "kt",
    "Ensure it is compatible with Multiplatform, Never use or output import java.*, Convert any UI components/screens into Jetpack Compose Views.",
)

# tran = CodeTranspiler("swift", "swift", "ensure the output is in Swift language.")
tran.transpile("flatten.py")
