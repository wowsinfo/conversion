from ..backend.console import ConsoleBackend
from ..transpiler import Transpiler
from ..engine import Engine

# engine = Engine.create_lm_studio_engine("codegeex4-all-9b-q4_k_s")
engine = Engine.create_lm_studio_engine("codellama-7b.q4_0")
target_language = input("Enter the target language: ")
backend = ConsoleBackend(target_language)
additional_rules = input("Enter additional rules (if any): ")
transpiler = Transpiler(engine, backend, additional_rules)

while True:
    print("Enter the code to convert >>> (End with Ctrl-D or Ctrl-Z on Windows)")
    # read until EOF
    code = ""
    while True:
        try:
            code += input() + "\n"
        except EOFError:
            break

    print("Processing the code...")
    output = transpiler.convert_code(code)
    print(f"##########\n{output}\n##########")

    should_continue = input("Do you want to continue? (y/n): ")
    if should_continue.lower() != "y":
        break
