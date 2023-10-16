# DSQLancer
![DSQLancer Logo](fig/DSQLancer_logo.png)

DSQLancer (Domain-Specific Language for SQLancer) is an attempt to offer an easy way to adapt [SQLancer](https://github.com/sqlancer/sqlancer) to different target DBMSs with different SQL dialects.

It is a work-in-progress grammar-based fuzzer that generates test cases from customized ANTLR grammar files. A list of available grammar files for variety of SQL dialects is available at [this GitHub repo](https://github.com/antlr/grammars-v4/tree/master/sql). Slight modifications to these grammar files are needed to run them with DSQLancer. For details, see docs under current folder.

This work is inspired by [Grammarinator](https://github.com/renatahodovan/grammarinator) and [StringTemplate](https://github.com/antlr/stringtemplate4/tree/master).