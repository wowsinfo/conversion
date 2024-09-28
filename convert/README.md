# Convert

This folder includes source code for using LLM engines, whether local or remote (e.g., OpenAI), to assist with code transpilation or project migration.

## Structure

The project is structured into several key modules, including **engine**, **backend**, **transpiler**.

### Engine

The **Engine** module is designed to interface with any LLM model that follows the OpenAI API v1 endpoint. This includes models hosted locally through platforms like Ollama or LM Studio, as well as OpenAI’s API directly. Additional engines can be integrated with minimal effort.

### Backend

The **Backend** is responsible for breaking down large codebases into smaller, more manageable parts for the LLM to process. You may also opt not to split the code (as seen in the `ConsoleBackend`), though splitting is recommended, as it may improve the output quality. This module also handles generating rules for the LLM engine to follow, optimizing prompt engineering for better code generation. Well-crafted rules can significantly enhance the engine's output. Ask ChatGPT for some helps if needed.

### Transpiler

The **Transpiler** takes an engine and a backend and uses them to convert code from one language to another. While the results may require manual adjustments, the process can be a helpful starting point for developing features in a different programming language. Note that this tool is designed to assist, not replace, manual development.

## Project Migration

The Project Migration feature enables you to convert an entire project, file by file, into another language using the `Transpiler`. While the quality of the output may vary, this can greatly enhance the migration process. Previously, such a task might take weeks or even months; with automation, any assistance is valuable.

## Console

The **Console** module allows you to input a code snippet, and the LLM will attempt to convert it into another language. Ideally, comments should be stripped from the input to avoid potential prompt injection, which could decrease the output quality.
