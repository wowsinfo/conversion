from abc import ABC, abstractmethod
from typing import Iterator


class Backend(ABC):
    """
    Backend for any programming language:
    - Instruct how to break down the code into smaller parts
    """

    @abstractmethod
    def break_down(self, code: str) -> Iterator[str]:
        """
        Break down the code into smaller parts
        """
        pass

    @abstractmethod
    def build_rules(self) -> str:
        """
        Build the rules/instructions for the engine
        """
        pass

    # region generic methods

    def merge_code(self, parts: list[str]) -> str:
        """
        Merge the parts back together
        """
        return "\n\n".join(parts)

    def default_rules(self, target_lang: str, original: str) -> str:
        """
        Default rules for the engine, free to update as needed
        """
        return (
            f"###\nConvert the source code above from {original} into {target_lang}\n"
            f"Use ``` to wrap the codeblock\n"
            f"Output strictly the `{target_lang}` codeblock without additional explainations\n"
            f"Ensure the code is strictly one to one mapping from `{original}` to `{target_lang}`\n"
        )

    def parse_codeblock(self, text: str) -> str:
        """
        Parse the codeblock from the response
        """
        start = text.find("```") + 3
        end = text.find("```", start)
        if start == -1 or end == -1:
            raise ValueError("Codeblock not found")
        result = text[start:end].strip()
        # remove the first line
        return "\n".join(result.split("\n")[1:])

    # endregion
