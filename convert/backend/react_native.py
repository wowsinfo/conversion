from typing import Iterator
from . import Backend
import re


class ReactNativeBackend(Backend):
    def __init__(self, target_lang: str, tab_size: int = 2):
        self.target_lang = target_lang
        self.tab_size = tab_size

    def break_down(self, code: str) -> Iterator[str]:
        top_level_regex = re.compile(r"^}$|^};$")
        top_level_components = top_level_regex.split(code)
        # based on the tab_size
        smaller_split_str = "\n" + " " * self.tab_size + "}"
        for component in top_level_components:
            # break down into functions or smaller parts
            for part in component.split(smaller_split_str):
                # print(part)
                # print("\n==============================\n")
                yield part.strip()

    def build_rules(self) -> str:
        """
        Build the rules/instructions for the engine
        """
        return self.default_rules(self.target_lang, "React Native")
