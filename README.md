# Conversion
Convert old project from React Native to Flutter, Flutter to React Native and both to Kotlin. Using LLM magic, any programming language known to the model can be converted. However, don't expect the code to compile and manual adjustments will always be needed. The result may differ based on the LLM.

- See `convert/` for the source code
- See `llm_result/` for Flutter to React Native & React Native to Flutter conversion
- See `LegacyCodeConversion/` to have over 8,000 errors in Kotlin

Note that you can use your local LLM provider as long as it implements the OpenAI API standard. The code is written in a way that it can be easily modified to work with any LLM provider. See `convert/engine/` for more details.

## Observations
- The generated TypeScript code may be the best one so far
- Lots of unnecessary code in the generated wherever there is something UI related
- Shorter functions are generally better
- only 43 cents for 4 project conversions with over 50,000 lines of generated code

Whether the generated code works or not, this kind of conversion is never possible before LLM. Even if the generated code is only half working or 20% working, that's still a huge time saver with 10,000 working lines of code within a few hours. An interactive console is also provided for you to further refine the code under `convert/console`, run it with `python -m convert.console`. This can output almost one to one mapping of the code, but it doesn't work because you have different libraries or frameworks in use. 

Check https://github.com/HenryQuan/HaxeExperiment for the "dream" programming language.
