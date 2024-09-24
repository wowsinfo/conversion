from . import Engine


class LMStudioEngine(Engine):
    def __init__(self, model_name: str, host_url: str = "http://localhost:1234/v1"):
        super().__init__("NOT_NEEDED", model_name, host_url)
