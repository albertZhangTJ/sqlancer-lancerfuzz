# Details

Date : 2023-04-17 11:40:21

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/postgres

Total : 63 files,  6741 codes, 271 comments, 1101 blanks, all 8113 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/sqlancer/postgres/PostgresCompoundDataType.java](/src/sqlancer/postgres/PostgresCompoundDataType.java) | Java | 35 | 0 | 11 | 46 |
| [src/sqlancer/postgres/PostgresExpectedValueVisitor.java](/src/sqlancer/postgres/PostgresExpectedValueVisitor.java) | Java | 143 | 10 | 28 | 181 |
| [src/sqlancer/postgres/PostgresGlobalState.java](/src/sqlancer/postgres/PostgresGlobalState.java) | Java | 124 | 4 | 26 | 154 |
| [src/sqlancer/postgres/PostgresOptions.java](/src/sqlancer/postgres/PostgresOptions.java) | Java | 72 | 0 | 17 | 89 |
| [src/sqlancer/postgres/PostgresProvider.java](/src/sqlancer/postgres/PostgresProvider.java) | Java | 308 | 13 | 29 | 350 |
| [src/sqlancer/postgres/PostgresSchema.java](/src/sqlancer/postgres/PostgresSchema.java) | Java | 276 | 6 | 46 | 328 |
| [src/sqlancer/postgres/PostgresToStringVisitor.java](/src/sqlancer/postgres/PostgresToStringVisitor.java) | Java | 317 | 6 | 31 | 354 |
| [src/sqlancer/postgres/PostgresVisitor.java](/src/sqlancer/postgres/PostgresVisitor.java) | Java | 104 | 0 | 28 | 132 |
| [src/sqlancer/postgres/ast/PostgresAggregate.java](/src/sqlancer/postgres/ast/PostgresAggregate.java) | Java | 42 | 4 | 14 | 60 |
| [src/sqlancer/postgres/ast/PostgresAlias.java](/src/sqlancer/postgres/ast/PostgresAlias.java) | Java | 26 | 0 | 10 | 36 |
| [src/sqlancer/postgres/ast/PostgresBetweenOperation.java](/src/sqlancer/postgres/ast/PostgresBetweenOperation.java) | Java | 55 | 0 | 12 | 67 |
| [src/sqlancer/postgres/ast/PostgresBinaryArithmeticOperation.java](/src/sqlancer/postgres/ast/PostgresBinaryArithmeticOperation.java) | Java | 88 | 0 | 22 | 110 |
| [src/sqlancer/postgres/ast/PostgresBinaryBitOperation.java](/src/sqlancer/postgres/ast/PostgresBinaryBitOperation.java) | Java | 35 | 0 | 12 | 47 |
| [src/sqlancer/postgres/ast/PostgresBinaryComparisonOperation.java](/src/sqlancer/postgres/ast/PostgresBinaryComparisonOperation.java) | Java | 122 | 0 | 18 | 140 |
| [src/sqlancer/postgres/ast/PostgresBinaryLogicalOperation.java](/src/sqlancer/postgres/ast/PostgresBinaryLogicalOperation.java) | Java | 78 | 0 | 11 | 89 |
| [src/sqlancer/postgres/ast/PostgresBinaryRangeOperation.java](/src/sqlancer/postgres/ast/PostgresBinaryRangeOperation.java) | Java | 54 | 0 | 20 | 74 |
| [src/sqlancer/postgres/ast/PostgresCastOperation.java](/src/sqlancer/postgres/ast/PostgresCastOperation.java) | Java | 35 | 0 | 11 | 46 |
| [src/sqlancer/postgres/ast/PostgresCollate.java](/src/sqlancer/postgres/ast/PostgresCollate.java) | Java | 24 | 0 | 10 | 34 |
| [src/sqlancer/postgres/ast/PostgresColumnValue.java](/src/sqlancer/postgres/ast/PostgresColumnValue.java) | Java | 25 | 0 | 10 | 35 |
| [src/sqlancer/postgres/ast/PostgresConcatOperation.java](/src/sqlancer/postgres/ast/PostgresConcatOperation.java) | Java | 30 | 0 | 8 | 38 |
| [src/sqlancer/postgres/ast/PostgresConstant.java](/src/sqlancer/postgres/ast/PostgresConstant.java) | Java | 480 | 0 | 118 | 598 |
| [src/sqlancer/postgres/ast/PostgresExpression.java](/src/sqlancer/postgres/ast/PostgresExpression.java) | Java | 10 | 0 | 5 | 15 |
| [src/sqlancer/postgres/ast/PostgresFunction.java](/src/sqlancer/postgres/ast/PostgresFunction.java) | Java | 203 | 40 | 46 | 289 |
| [src/sqlancer/postgres/ast/PostgresFunctionWithUnknownResult.java](/src/sqlancer/postgres/ast/PostgresFunctionWithUnknownResult.java) | Java | 137 | 27 | 23 | 187 |
| [src/sqlancer/postgres/ast/PostgresInOperation.java](/src/sqlancer/postgres/ast/PostgresInOperation.java) | Java | 54 | 0 | 12 | 66 |
| [src/sqlancer/postgres/ast/PostgresJoin.java](/src/sqlancer/postgres/ast/PostgresJoin.java) | Java | 36 | 0 | 14 | 50 |
| [src/sqlancer/postgres/ast/PostgresLikeOperation.java](/src/sqlancer/postgres/ast/PostgresLikeOperation.java) | Java | 31 | 0 | 8 | 39 |
| [src/sqlancer/postgres/ast/PostgresOrderByTerm.java](/src/sqlancer/postgres/ast/PostgresOrderByTerm.java) | Java | 31 | 0 | 12 | 43 |
| [src/sqlancer/postgres/ast/PostgresPOSIXRegularExpression.java](/src/sqlancer/postgres/ast/PostgresPOSIXRegularExpression.java) | Java | 49 | 0 | 17 | 66 |
| [src/sqlancer/postgres/ast/PostgresPostfixOperation.java](/src/sqlancer/postgres/ast/PostgresPostfixOperation.java) | Java | 120 | 0 | 32 | 152 |
| [src/sqlancer/postgres/ast/PostgresPostfixText.java](/src/sqlancer/postgres/ast/PostgresPostfixText.java) | Java | 29 | 0 | 9 | 38 |
| [src/sqlancer/postgres/ast/PostgresPrefixOperation.java](/src/sqlancer/postgres/ast/PostgresPrefixOperation.java) | Java | 92 | 2 | 26 | 120 |
| [src/sqlancer/postgres/ast/PostgresSelect.java](/src/sqlancer/postgres/ast/PostgresSelect.java) | Java | 102 | 0 | 34 | 136 |
| [src/sqlancer/postgres/ast/PostgresSimilarTo.java](/src/sqlancer/postgres/ast/PostgresSimilarTo.java) | Java | 30 | 0 | 11 | 41 |
| [src/sqlancer/postgres/gen/PostgresAlterTableGenerator.java](/src/sqlancer/postgres/gen/PostgresAlterTableGenerator.java) | Java | 358 | 8 | 16 | 382 |
| [src/sqlancer/postgres/gen/PostgresAnalyzeGenerator.java](/src/sqlancer/postgres/gen/PostgresAnalyzeGenerator.java) | Java | 35 | 1 | 7 | 43 |
| [src/sqlancer/postgres/gen/PostgresClusterGenerator.java](/src/sqlancer/postgres/gen/PostgresClusterGenerator.java) | Java | 27 | 0 | 6 | 33 |
| [src/sqlancer/postgres/gen/PostgresCommentGenerator.java](/src/sqlancer/postgres/gen/PostgresCommentGenerator.java) | Java | 59 | 3 | 7 | 69 |
| [src/sqlancer/postgres/gen/PostgresCommon.java](/src/sqlancer/postgres/gen/PostgresCommon.java) | Java | 380 | 21 | 34 | 435 |
| [src/sqlancer/postgres/gen/PostgresDeleteGenerator.java](/src/sqlancer/postgres/gen/PostgresDeleteGenerator.java) | Java | 41 | 0 | 6 | 47 |
| [src/sqlancer/postgres/gen/PostgresDiscardGenerator.java](/src/sqlancer/postgres/gen/PostgresDiscardGenerator.java) | Java | 32 | 1 | 7 | 40 |
| [src/sqlancer/postgres/gen/PostgresDropIndexGenerator.java](/src/sqlancer/postgres/gen/PostgresDropIndexGenerator.java) | Java | 45 | 0 | 7 | 52 |
| [src/sqlancer/postgres/gen/PostgresExpressionGenerator.java](/src/sqlancer/postgres/gen/PostgresExpressionGenerator.java) | Java | 530 | 12 | 63 | 605 |
| [src/sqlancer/postgres/gen/PostgresIndexGenerator.java](/src/sqlancer/postgres/gen/PostgresIndexGenerator.java) | Java | 129 | 13 | 12 | 154 |
| [src/sqlancer/postgres/gen/PostgresInsertGenerator.java](/src/sqlancer/postgres/gen/PostgresInsertGenerator.java) | Java | 117 | 0 | 10 | 127 |
| [src/sqlancer/postgres/gen/PostgresNotifyGenerator.java](/src/sqlancer/postgres/gen/PostgresNotifyGenerator.java) | Java | 39 | 0 | 9 | 48 |
| [src/sqlancer/postgres/gen/PostgresRandomQueryGenerator.java](/src/sqlancer/postgres/gen/PostgresRandomQueryGenerator.java) | Java | 57 | 0 | 7 | 64 |
| [src/sqlancer/postgres/gen/PostgresReindexGenerator.java](/src/sqlancer/postgres/gen/PostgresReindexGenerator.java) | Java | 57 | 3 | 8 | 68 |
| [src/sqlancer/postgres/gen/PostgresSequenceGenerator.java](/src/sqlancer/postgres/gen/PostgresSequenceGenerator.java) | Java | 79 | 6 | 6 | 91 |
| [src/sqlancer/postgres/gen/PostgresSetGenerator.java](/src/sqlancer/postgres/gen/PostgresSetGenerator.java) | Java | 104 | 35 | 10 | 149 |
| [src/sqlancer/postgres/gen/PostgresStatisticsGenerator.java](/src/sqlancer/postgres/gen/PostgresStatisticsGenerator.java) | Java | 64 | 0 | 10 | 74 |
| [src/sqlancer/postgres/gen/PostgresTableGenerator.java](/src/sqlancer/postgres/gen/PostgresTableGenerator.java) | Java | 281 | 9 | 18 | 308 |
| [src/sqlancer/postgres/gen/PostgresTransactionGenerator.java](/src/sqlancer/postgres/gen/PostgresTransactionGenerator.java) | Java | 18 | 4 | 6 | 28 |
| [src/sqlancer/postgres/gen/PostgresTruncateGenerator.java](/src/sqlancer/postgres/gen/PostgresTruncateGenerator.java) | Java | 30 | 4 | 7 | 41 |
| [src/sqlancer/postgres/gen/PostgresUpdateGenerator.java](/src/sqlancer/postgres/gen/PostgresUpdateGenerator.java) | Java | 68 | 2 | 11 | 81 |
| [src/sqlancer/postgres/gen/PostgresVacuumGenerator.java](/src/sqlancer/postgres/gen/PostgresVacuumGenerator.java) | Java | 65 | 5 | 8 | 78 |
| [src/sqlancer/postgres/gen/PostgresViewGenerator.java](/src/sqlancer/postgres/gen/PostgresViewGenerator.java) | Java | 78 | 9 | 6 | 93 |
| [src/sqlancer/postgres/oracle/PostgresNoRECOracle.java](/src/sqlancer/postgres/oracle/PostgresNoRECOracle.java) | Java | 155 | 1 | 13 | 169 |
| [src/sqlancer/postgres/oracle/PostgresPivotedQuerySynthesisOracle.java](/src/sqlancer/postgres/oracle/PostgresPivotedQuerySynthesisOracle.java) | Java | 132 | 3 | 17 | 152 |
| [src/sqlancer/postgres/oracle/tlp/PostgresTLPAggregateOracle.java](/src/sqlancer/postgres/oracle/tlp/PostgresTLPAggregateOracle.java) | Java | 161 | 18 | 17 | 196 |
| [src/sqlancer/postgres/oracle/tlp/PostgresTLPBase.java](/src/sqlancer/postgres/oracle/tlp/PostgresTLPBase.java) | Java | 108 | 1 | 13 | 122 |
| [src/sqlancer/postgres/oracle/tlp/PostgresTLPHavingOracle.java](/src/sqlancer/postgres/oracle/tlp/PostgresTLPHavingOracle.java) | Java | 57 | 0 | 11 | 68 |
| [src/sqlancer/postgres/oracle/tlp/PostgresTLPWhereOracle.java](/src/sqlancer/postgres/oracle/tlp/PostgresTLPWhereOracle.java) | Java | 38 | 0 | 8 | 46 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)