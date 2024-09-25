from openai import OpenAI
from dataclasses import dataclass


@dataclass
class EngineConfig:
    max_tokens: int = 2048
    temperature: float = 0.3
    top_p: float = None
    repetition_penalty: float = None


NO_KEY_NEEDED = "NOT_NEEDED"


class Engine:
    """
    Use any LLM models as an engine, either it is local or remote.
    As long as it follows the OPENAI API interface, it can be used.
    """

    def __init__(
        self,
        api_key: str,
        model_name: str,
        host_url: str = None,
        config: EngineConfig = None,
    ):
        self.openai = OpenAI(api_key=api_key, base_url=host_url)
        self.model_name = model_name
        self.config = config

    # region Factory methods

    def create_ollama_engine(
        model_name: str,
        host_url: str = "http://localhost:11434/v1",
        config: EngineConfig = EngineConfig(),
    ):
        return Engine(NO_KEY_NEEDED, model_name, host_url, config)

    def create_lm_studio_engine(
        model_name: str,
        host_url: str = "http://localhost:1234/v1",
        config: EngineConfig = EngineConfig(),
    ):
        return Engine(NO_KEY_NEEDED, model_name, host_url, config)

    def create_chatgpt_engine(
        api_key: str,
        model_name: str,
        config: EngineConfig = EngineConfig(),
    ):
        return Engine(api_key, model_name, None, config)

    # endregion
    # region functions

    def get_chat_response(self, prompt: str):
        response = self.openai.chat.completions.create(
            model=self.model_name,
            messages=[
                {
                    "role": "user",
                    "content": prompt,
                },
                # {
                #     "role": "system",
                #     "content": rules,
                # },
                # {
                #     "role": "assistant",
                #     "content": r"```\n{insert code here}\n```",
                # },
            ],
            max_tokens=self.config.max_tokens,
            temperature=self.config.temperature,
            top_p=self.config.temperature,
            presence_penalty=self.config.repetition_penalty,
            frequency_penalty=self.config.repetition_penalty,
        )
        return response.choices[0].message.content.strip()

    def get_response(self, prompt: str):
        """
        For older models only, use get_chat_response for newer models.
        """
        response = self.openai.completions.create(
            model=self.model_name,
            prompt=prompt,
            max_tokens=self.config.max_tokens,
            temperature=self.config.temperature,
            top_p=self.config.temperature,
        )
        return response.choices[0].text.strip()

    # endregion
