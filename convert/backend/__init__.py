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
        # return (
        #     f"\n###\nConvert the source code above from {original} into {target_lang}. "
        #     f"Use ``` to wrap the codeblock. "
        #     f"Output strictly the `{target_lang}` codeblock without additional explainations. "
        #     f"Ensure the code uses technology in `{target_lang}`. "
        # )

        return f"""\n###\nTranslate the provided code from `{original}` to `{target_lang}`.
Instructions:
1. **Code Block**: Wrap the entire output in a code block using triple backticks (```) with no additional explanations, comments, or surrounding text.
2. **Technology Conversion**: Ensure that all libraries, frameworks, APIs, and language-specific constructs from `{original}` are replaced with direct equivalents in `{target_lang}`.
3. **Functional Code**: The translated code must be fully functional and complete in `{target_lang}`. No placeholders like `// Add your content here` or `// Implement here` are allowed.
4. **No Incomplete Code**: Every part of the original logic must be converted. Ensure that all methods, functions, and structures are fully implemented and functional without any omissions.
5. **Strict Focus**: Output only the translated `{target_lang}` code, fully complete and without commentary or explanation.
""".strip()

        return f"""Convert the provided source code from `{original}` to `{target_lang}`. 
- Wrap the entire output in a code block using triple backticks (```) and ensure no extra explanations or comments are included.
- All technology-specific constructs, libraries, and functions used in `{original}` must be replaced with equivalent constructs in `{target_lang}`.
- Ensure that any frameworks, libraries, or specific APIs are fully translated to the `{target_lang}` ecosystem.
- Output **only** the `{target_lang}` code, fully functional in its environment.
""".strip()


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
