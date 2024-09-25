from ..backend import Backend
from ..engine import Engine
from ..post_processing import PostProcessing
from time import sleep


class Transpiler:
    """
    Convert the code from one language to another,
    breaking down the code into smaller parts,
    use the engine to convert the code,
    and finally merge the code back together.
    """

    def __init__(
        self,
        engine: Engine,
        backend: Backend,
        custom_rules: str = None,
        post_processing: list[PostProcessing] = [],
    ):
        self.engine = engine
        self.backend = backend
        self.custom_rules = custom_rules
        self.post_processing = post_processing

    def convert_code(self, code: str) -> str:
        """
        Convert the code from one language to another
        """
        rules = self.backend.build_rules()
        if self.custom_rules:
            rules += self.custom_rules

        transpile_parts = []
        for part in self.backend.break_down(code):
            prompt = part + rules
            try:
                converted_parts = self.engine.get_chat_response(prompt)
                sleep(0.2)
                transpile_parts.append(self.backend.parse_codeblock(converted_parts))
            except ValueError:
                converted_parts = "===>>> review manually\n" + part + "\n<<<===\n"
                transpile_parts.append(converted_parts)

        before_processing = self.backend.merge_code(transpile_parts)
        after_processing = before_processing
        for process in self.post_processing:
            after_processing = process.process(before_processing)
        return after_processing

    def convert_file(self, file_path: str) -> str:
        with open(file_path, "r", encoding="utf-8") as file:
            code = file.read()
        return self.convert_code(code)
