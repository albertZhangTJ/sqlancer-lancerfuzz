# Diff Details

Date : 2023-07-10 14:50:44

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/dsqlancer

Total : 77 files,  -966 codes, 1195 comments, -775 blanks, all -546 lines

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/dsqlancer/ANTLR/ANTLRv4Lexer.java](/src/dsqlancer/ANTLR/ANTLRv4Lexer.java) | Java | 631 | 16 | 21 | 668 |
| [src/dsqlancer/ANTLR/ANTLRv4Parser.java](/src/dsqlancer/ANTLR/ANTLRv4Parser.java) | Java | 4,630 | 4 | 173 | 4,807 |
| [src/dsqlancer/ANTLR/ANTLRv4ParserBaseListener.java](/src/dsqlancer/ANTLR/ANTLRv4ParserBaseListener.java) | Java | 136 | 657 | 4 | 797 |
| [src/dsqlancer/ANTLR/ANTLRv4ParserListener.java](/src/dsqlancer/ANTLR/ANTLRv4ParserListener.java) | Java | 130 | 509 | 2 | 641 |
| [src/dsqlancer/ANTLR/LexBasic.java](/src/dsqlancer/ANTLR/LexBasic.java) | Java | 274 | 4 | 20 | 298 |
| [src/dsqlancer/AST/Edge.java](/src/dsqlancer/AST/Edge.java) | Java | 25 | 3 | 12 | 40 |
| [src/dsqlancer/AST/GrammarGraph.java](/src/dsqlancer/AST/GrammarGraph.java) | Java | 41 | 0 | 12 | 53 |
| [src/dsqlancer/AST/LambdaNode.java](/src/dsqlancer/AST/LambdaNode.java) | Java | 6 | 0 | 2 | 8 |
| [src/dsqlancer/AST/Node.java](/src/dsqlancer/AST/Node.java) | Java | 23 | 0 | 8 | 31 |
| [src/dsqlancer/AST/RuleNode.java](/src/dsqlancer/AST/RuleNode.java) | Java | 20 | 2 | 8 | 30 |
| [src/dsqlancer/Main.java](/src/dsqlancer/Main.java) | Java | 23 | 23 | 13 | 59 |
| [src/dsqlancer/Options.java](/src/dsqlancer/Options.java) | Java | 21 | 6 | 14 | 41 |
| [src/dsqlancer/Processor.java](/src/dsqlancer/Processor.java) | Java | 86 | 12 | 16 | 114 |
| [src/dsqlancer/README.md](/src/dsqlancer/README.md) | Markdown | 0 | 0 | 1 | 1 |
| [src/dsqlancer/Utils.java](/src/dsqlancer/Utils.java) | Java | 25 | 6 | 8 | 39 |
| [src/dsqlancer/resources/ANTLRv4Lexer.g4](/src/dsqlancer/resources/ANTLRv4Lexer.g4) | ANTLR | 222 | 101 | 79 | 402 |
| [src/dsqlancer/resources/ANTLRv4Parser.g4](/src/dsqlancer/resources/ANTLRv4Parser.g4) | ANTLR | 233 | 79 | 67 | 379 |
| [src/dsqlancer/resources/LexBasic.g4](/src/dsqlancer/resources/LexBasic.g4) | ANTLR | 168 | 56 | 51 | 275 |
| [src/dsqlancer/resources/SQLite.g4](/src/dsqlancer/resources/SQLite.g4) | ANTLR | 761 | 45 | 97 | 903 |
| [src/dsqlancer/resources/test.g4](/src/dsqlancer/resources/test.g4) | ANTLR | 198 | 0 | 15 | 213 |
| [src/sqlancer/sqlite3/SQLite3CollateHelper.java](/src/sqlancer/sqlite3/SQLite3CollateHelper.java) | Java | -15 | 0 | -6 | -21 |
| [src/sqlancer/sqlite3/SQLite3Errors.java](/src/sqlancer/sqlite3/SQLite3Errors.java) | Java | -92 | -8 | -20 | -120 |
| [src/sqlancer/sqlite3/SQLite3ExpectedValueVisitor.java](/src/sqlancer/sqlite3/SQLite3ExpectedValueVisitor.java) | Java | -263 | 0 | -44 | -307 |
| [src/sqlancer/sqlite3/SQLite3GlobalState.java](/src/sqlancer/sqlite3/SQLite3GlobalState.java) | Java | -10 | 0 | -6 | -16 |
| [src/sqlancer/sqlite3/SQLite3Options.java](/src/sqlancer/sqlite3/SQLite3Options.java) | Java | -144 | 0 | -36 | -180 |
| [src/sqlancer/sqlite3/SQLite3Provider.java](/src/sqlancer/sqlite3/SQLite3Provider.java) | Java | -324 | -5 | -32 | -361 |
| [src/sqlancer/sqlite3/SQLite3SpecialStringGenerator.java](/src/sqlancer/sqlite3/SQLite3SpecialStringGenerator.java) | Java | -37 | 0 | -7 | -44 |
| [src/sqlancer/sqlite3/SQLite3ToStringVisitor.java](/src/sqlancer/sqlite3/SQLite3ToStringVisitor.java) | Java | -438 | -9 | -39 | -486 |
| [src/sqlancer/sqlite3/SQLite3Visitor.java](/src/sqlancer/sqlite3/SQLite3Visitor.java) | Java | -173 | -1 | -47 | -221 |
| [src/sqlancer/sqlite3/ast/SQLite3Aggregate.java](/src/sqlancer/sqlite3/ast/SQLite3Aggregate.java) | Java | -109 | -5 | -19 | -133 |
| [src/sqlancer/sqlite3/ast/SQLite3Case.java](/src/sqlancer/sqlite3/ast/SQLite3Case.java) | Java | -126 | 0 | -26 | -152 |
| [src/sqlancer/sqlite3/ast/SQLite3Cast.java](/src/sqlancer/sqlite3/ast/SQLite3Cast.java) | Java | -250 | -8 | -25 | -283 |
| [src/sqlancer/sqlite3/ast/SQLite3Constant.java](/src/sqlancer/sqlite3/ast/SQLite3Constant.java) | Java | -512 | -5 | -109 | -626 |
| [src/sqlancer/sqlite3/ast/SQLite3Expression.java](/src/sqlancer/sqlite3/ast/SQLite3Expression.java) | Java | -1,254 | -36 | -264 | -1,554 |
| [src/sqlancer/sqlite3/ast/SQLite3Function.java](/src/sqlancer/sqlite3/ast/SQLite3Function.java) | Java | -304 | -10 | -42 | -356 |
| [src/sqlancer/sqlite3/ast/SQLite3RowValueExpression.java](/src/sqlancer/sqlite3/ast/SQLite3RowValueExpression.java) | Java | -22 | 0 | -9 | -31 |
| [src/sqlancer/sqlite3/ast/SQLite3Select.java](/src/sqlancer/sqlite3/ast/SQLite3Select.java) | Java | -108 | -1 | -32 | -141 |
| [src/sqlancer/sqlite3/ast/SQLite3SetClause.java](/src/sqlancer/sqlite3/ast/SQLite3SetClause.java) | Java | -39 | -1 | -15 | -55 |
| [src/sqlancer/sqlite3/ast/SQLite3UnaryOperation.java](/src/sqlancer/sqlite3/ast/SQLite3UnaryOperation.java) | Java | -125 | -9 | -23 | -157 |
| [src/sqlancer/sqlite3/ast/SQLite3WindowFunction.java](/src/sqlancer/sqlite3/ast/SQLite3WindowFunction.java) | Java | -123 | 0 | -21 | -144 |
| [src/sqlancer/sqlite3/ast/SQLite3WindowFunctionExpression.java](/src/sqlancer/sqlite3/ast/SQLite3WindowFunctionExpression.java) | Java | -133 | 0 | -48 | -181 |
| [src/sqlancer/sqlite3/gen/SQLite3AnalyzeGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3AnalyzeGenerator.java) | Java | -35 | 0 | -7 | -42 |
| [src/sqlancer/sqlite3/gen/SQLite3ColumnBuilder.java](/src/sqlancer/sqlite3/gen/SQLite3ColumnBuilder.java) | Java | -144 | -2 | -19 | -165 |
| [src/sqlancer/sqlite3/gen/SQLite3Common.java](/src/sqlancer/sqlite3/gen/SQLite3Common.java) | Java | -120 | -2 | -19 | -141 |
| [src/sqlancer/sqlite3/gen/SQLite3CreateVirtualRtreeTabelGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3CreateVirtualRtreeTabelGenerator.java) | Java | -54 | 0 | -9 | -63 |
| [src/sqlancer/sqlite3/gen/SQLite3ExplainGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3ExplainGenerator.java) | Java | -30 | 0 | -7 | -37 |
| [src/sqlancer/sqlite3/gen/SQLite3ExpressionGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3ExpressionGenerator.java) | Java | -588 | -49 | -69 | -706 |
| [src/sqlancer/sqlite3/gen/SQLite3MatchStringGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3MatchStringGenerator.java) | Java | -84 | 0 | -10 | -94 |
| [src/sqlancer/sqlite3/gen/SQLite3PragmaGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3PragmaGenerator.java) | Java | -189 | -33 | -14 | -236 |
| [src/sqlancer/sqlite3/gen/SQLite3ReindexGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3ReindexGenerator.java) | Java | -39 | -4 | -6 | -49 |
| [src/sqlancer/sqlite3/gen/SQLite3TransactionGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3TransactionGenerator.java) | Java | -32 | -1 | -8 | -41 |
| [src/sqlancer/sqlite3/gen/SQLite3VacuumGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3VacuumGenerator.java) | Java | -18 | -3 | -6 | -27 |
| [src/sqlancer/sqlite3/gen/SQLite3VirtualFTSTableCommandGenerator.java](/src/sqlancer/sqlite3/gen/SQLite3VirtualFTSTableCommandGenerator.java) | Java | -86 | -3 | -9 | -98 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3AlterTable.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3AlterTable.java) | Java | -69 | -7 | -11 | -87 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3CreateTriggerGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3CreateTriggerGenerator.java) | Java | -131 | 0 | -15 | -146 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3CreateVirtualFTSTableGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3CreateVirtualFTSTableGenerator.java) | Java | -175 | -2 | -18 | -195 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3DropIndexGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3DropIndexGenerator.java) | Java | -23 | -1 | -6 | -30 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3DropTableGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3DropTableGenerator.java) | Java | -25 | 0 | -7 | -32 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3IndexGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3IndexGenerator.java) | Java | -104 | -11 | -12 | -127 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3TableGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3TableGenerator.java) | Java | -190 | -20 | -17 | -227 |
| [src/sqlancer/sqlite3/gen/ddl/SQLite3ViewGenerator.java](/src/sqlancer/sqlite3/gen/ddl/SQLite3ViewGenerator.java) | Java | -81 | -11 | -11 | -103 |
| [src/sqlancer/sqlite3/gen/dml/SQLite3DeleteGenerator.java](/src/sqlancer/sqlite3/gen/dml/SQLite3DeleteGenerator.java) | Java | -39 | 0 | -8 | -47 |
| [src/sqlancer/sqlite3/gen/dml/SQLite3InsertGenerator.java](/src/sqlancer/sqlite3/gen/dml/SQLite3InsertGenerator.java) | Java | -149 | -4 | -15 | -168 |
| [src/sqlancer/sqlite3/gen/dml/SQLite3StatTableGenerator.java](/src/sqlancer/sqlite3/gen/dml/SQLite3StatTableGenerator.java) | Java | -80 | 0 | -9 | -89 |
| [src/sqlancer/sqlite3/gen/dml/SQLite3UpdateGenerator.java](/src/sqlancer/sqlite3/gen/dml/SQLite3UpdateGenerator.java) | Java | -86 | -11 | -18 | -115 |
| [src/sqlancer/sqlite3/oracle/SQLite3Fuzzer.java](/src/sqlancer/sqlite3/oracle/SQLite3Fuzzer.java) | Java | -24 | -1 | -8 | -33 |
| [src/sqlancer/sqlite3/oracle/SQLite3NoRECOracle.java](/src/sqlancer/sqlite3/oracle/SQLite3NoRECOracle.java) | Java | -185 | 0 | -22 | -207 |
| [src/sqlancer/sqlite3/oracle/SQLite3PivotedQuerySynthesisOracle.java](/src/sqlancer/sqlite3/oracle/SQLite3PivotedQuerySynthesisOracle.java) | Java | -300 | -26 | -27 | -353 |
| [src/sqlancer/sqlite3/oracle/SQLite3RandomQuerySynthesizer.java](/src/sqlancer/sqlite3/oracle/SQLite3RandomQuerySynthesizer.java) | Java | -135 | -16 | -10 | -161 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPAggregateOracle.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPAggregateOracle.java) | Java | -114 | -4 | -16 | -134 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPBase.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPBase.java) | Java | -62 | 0 | -10 | -72 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPDistinctOracle.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPDistinctOracle.java) | Java | -38 | 0 | -10 | -48 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPGroupByOracle.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPGroupByOracle.java) | Java | -46 | 0 | -11 | -57 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPHavingOracle.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPHavingOracle.java) | Java | -84 | -1 | -9 | -94 |
| [src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPWhereOracle.java](/src/sqlancer/sqlite3/oracle/tlp/SQLite3TLPWhereOracle.java) | Java | -41 | 0 | -10 | -51 |
| [src/sqlancer/sqlite3/schema/SQLite3DataType.java](/src/sqlancer/sqlite3/schema/SQLite3DataType.java) | Java | -4 | 0 | -3 | -7 |
| [src/sqlancer/sqlite3/schema/SQLite3Schema.java](/src/sqlancer/sqlite3/schema/SQLite3Schema.java) | Java | -414 | -18 | -62 | -494 |

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details