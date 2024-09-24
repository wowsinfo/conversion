from openai import OpenAI
from abc import ABC


class Engine(ABC):
    def __init__(self, api_key: str, model_name: str, host_url: str = None):
        self.openai = OpenAI(api_key, base_url=host_url)
        self.model_name = model_name

    def get_chat_response(self, prompt: str):
        response = self.openai.chat.completions.create(
            model=self.model_name,
            messages=[
                {
                    "role": "user",
                    "content": prompt,
                },
            ],
        )
        return response.choices[0].message.content.strip()
