from openai import OpenAI

# This is Kotlin specific, should be passed in instead
# Ensure it is compatible with Multiplatform, Never output import java.*,


class CodeTranspiler:
    def __init__(
        self,
        result_language: str,
        output_format: str,
        additional_prompts: str,
        output_dir: str = "temp/",
    ):
        # provide an OPENAI_API_KEY to instantly use better online models
        self.openai_client = OpenAI(
            base_url="http://localhost:1234/v1", api_key="not_needed"
        )
        self.result_language = result_language
        self.output_format = output_format
        self.prompts = self._build_prompts(additional_prompts, result_language)
        self.output_dir = output_dir
        # print(self.prompts)

    def transpile(self, filepath: str):
        with open(filepath, "r") as file:
            code = file.read()

        llm_response = self._request_transpile(code)
        # print(llm_response)
        # print(self.result_language)
        processed_code = self._parse_codeblock(llm_response, self.result_language)

        with open(f"{self.output_dir}{filepath}.{self.output_format}", "w") as file:
            file.write(processed_code)

    def _request_transpile(self, code: str) -> str:
        response = self.openai_client.chat.completions.create(
            model="codegeex4-all-9b-q4_k_s",
            # prompt=f"{code}{self.prompts}",
            max_tokens=1024,
            messages=[
                {
                    "role": "user",
                    "content": f"{code}{self.prompts}",
                },
            ],
        )

        # return response.choices[0].text.strip()
        return response.choices[0].message.content.strip()

    def _parse_codeblock(self, text: str, language_name: str) -> str:
        # print(f"```{language_name}")
        # print(text)
        return text.split(f"```{language_name}")[1].split("```")[0].strip()

    def _build_prompts(self, additionals: str, result_language: str) -> str:
        default_prompt = (
            f"###\nUpdate the source code above into {result_language} class, "
        )
        default_prompt += f"Output strictly the {result_language} codeblock without additional explainations, "
        return f"{default_prompt}{additionals}"
