# SQL Generation Language
<img src="fig/seagull_logo.jpg" alt="logo" width="160"/>

SQL Generation Language (SGL) is a metasyntax language designed to effectively and efficiently specify SQL dialects for fuzzing purposes.
SGL has an EBNF-like design based on [ANTLR](www.antlr.org). Beyond typical context-free grammar features, SGL has a variable system for maintaining context for test case generation. SGL also features a predicate system on the grammar level to enforce semantic constraints in the target languages.

This repository contains a tool `Seagull` to compile SGL grammars into Java test case generators.

Seagull takes in a series of SGL grammar files and a single JSON configuration file as input and output a single Java class for the test case generator.

This tool is inspired by [Grammarinator](https://github.com/renatahodovan/grammarinator) and [StringTemplate](https://github.com/antlr/stringtemplate4/tree/master).

## Terms Used in this Documentation

- Grammar: the grammar of the language of the test cases, written in SGL
- Target language: the language of the test cases (SQL dialects)
- Compile: the process of generating executable test case generators from a grammar
- Run time: the time when a test case generator is executed
- Turnaround time: the time from the test case generator is called to a test case is produced
- Throughput: the amount of test case that can be generated in a given time limit (one minute unless otherwise specified)

## SGL

SGL is a metasyntax designed based on ANTLR, which is commonly used for generating parsers.
SGL uses ANTLR language constructs to specify the syntactical structures of the target language.
For maintaining context, SGL employs a variable system inspired by common general-purpose programming languages.
SGL also supports both 