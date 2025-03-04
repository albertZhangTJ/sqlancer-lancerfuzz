# SQLancer-SGL


![SQLancer](media/logo/png/sqlancer_logo_logo_pos_500.png)
<img src="src/SGL/fig/seagull_logo.jpg" alt="logo" width="160"/>

This repository contains a flavor of SQLancer with grammar-based fuzzing capabilities, enabled by SQL Generation Language(SGL).

SQLancer (Synthesized Query Lancer) is a tool to automatically test Database Management Systems (DBMS) in order to find logic bugs in their implementation. We refer to logic bugs as those bugs that cause the DBMS to fetch an incorrect result set (e.g., by omitting a record). For full SQLancer documentation, see [here](src/sqlancer/README.md).

SQL Generation Language (SGL, previously LancerFuzz) is a metasyntax language designed to effectively and efficiently specify SQL dialects for fuzzing purposes.
SGL has an EBNF-like design based on [ANTLR](www.antlr.org). Beyond typical context-free grammar features, SGL has a variable system for maintaining context for test case generation. SGL also features a predicate system on the grammar level to enforce semantic constraints in the target languages. For full SGL documentation, see [here](src/SGL/README.md).

## Quick Start
TODO