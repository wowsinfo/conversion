from . import PostProcessing


class RemoveKeyword(PostProcessing):
    """
    A generic post-processing step that removes all lines that start with "import ".

    """

    def __init__(self, keyword: str):
        self.keyword = keyword

    def process(self, data: str) -> str:
        lines = data.split("\n")
        new_lines = []
        for line in lines:
            if not line.startswith(self.keyword):
                new_lines.append(line)
        return "\n".join(new_lines)


class RemoveImport(RemoveKeyword):
    def __init__(self):
        super().__init__("import ")
