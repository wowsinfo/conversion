from abc import ABC, abstractmethod


class PostProcessing(ABC):
    @abstractmethod
    def process(self, data: str) -> str:
        pass
