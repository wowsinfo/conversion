from typing import Iterator
from . import Backend


class ConsoleBackend(Backend):
    """
    Take the code as it is without any modification
    """

    def __init__(self, target_lang: str):
        self.target_lang = target_lang

    def break_down(self, code: str) -> Iterator[str]:
        yield code.strip()

    def build_rules(self) -> str:
        """
        Build the rules/instructions for the engine
        """
        return self.default_rules(self.target_lang, "the given code")
