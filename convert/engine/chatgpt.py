from . import Engine


class ChatGPTEngine(Engine):
    def __init__(self, api_key: str, model_name: str):
        super().__init__(api_key, model_name, None)
