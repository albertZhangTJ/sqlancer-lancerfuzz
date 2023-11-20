# Details

Date : 2023-11-06 16:05:31

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/cockroachdb

Total : 61 files,  4357 codes, 103 comments, 838 blanks, all 5298 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/sqlancer/cockroachdb/CockroachDBBugs.java](/src/sqlancer/cockroachdb/CockroachDBBugs.java) | Java | 22 | 20 | 21 | 63 |
| [src/sqlancer/cockroachdb/CockroachDBCommon.java](/src/sqlancer/cockroachdb/CockroachDBCommon.java) | Java | 27 | 0 | 8 | 35 |
| [src/sqlancer/cockroachdb/CockroachDBErrors.java](/src/sqlancer/cockroachdb/CockroachDBErrors.java) | Java | 276 | 7 | 43 | 326 |
| [src/sqlancer/cockroachdb/CockroachDBOptions.java](/src/sqlancer/cockroachdb/CockroachDBOptions.java) | Java | 109 | 0 | 17 | 126 |
| [src/sqlancer/cockroachdb/CockroachDBProvider.java](/src/sqlancer/cockroachdb/CockroachDBProvider.java) | Java | 328 | 10 | 29 | 367 |
| [src/sqlancer/cockroachdb/CockroachDBSchema.java](/src/sqlancer/cockroachdb/CockroachDBSchema.java) | Java | 306 | 6 | 52 | 364 |
| [src/sqlancer/cockroachdb/CockroachDBToStringVisitor.java](/src/sqlancer/cockroachdb/CockroachDBToStringVisitor.java) | Java | 216 | 1 | 18 | 235 |
| [src/sqlancer/cockroachdb/CockroachDBVisitor.java](/src/sqlancer/cockroachdb/CockroachDBVisitor.java) | Java | 58 | 0 | 17 | 75 |
| [src/sqlancer/cockroachdb/ast/CockroachDBAggregate.java](/src/sqlancer/cockroachdb/ast/CockroachDBAggregate.java) | Java | 90 | 2 | 18 | 110 |
| [src/sqlancer/cockroachdb/ast/CockroachDBAlias.java](/src/sqlancer/cockroachdb/ast/CockroachDBAlias.java) | Java | 26 | 0 | 10 | 36 |
| [src/sqlancer/cockroachdb/ast/CockroachDBBetweenOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBBetweenOperation.java) | Java | 41 | 0 | 15 | 56 |
| [src/sqlancer/cockroachdb/ast/CockroachDBBinaryArithmeticOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBBinaryArithmeticOperation.java) | Java | 27 | 0 | 11 | 38 |
| [src/sqlancer/cockroachdb/ast/CockroachDBBinaryComparisonOperator.java](/src/sqlancer/cockroachdb/ast/CockroachDBBinaryComparisonOperator.java) | Java | 27 | 0 | 11 | 38 |
| [src/sqlancer/cockroachdb/ast/CockroachDBBinaryLogicalOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBBinaryLogicalOperation.java) | Java | 26 | 0 | 11 | 37 |
| [src/sqlancer/cockroachdb/ast/CockroachDBCaseOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBCaseOperation.java) | Java | 22 | 0 | 9 | 31 |
| [src/sqlancer/cockroachdb/ast/CockroachDBCast.java](/src/sqlancer/cockroachdb/ast/CockroachDBCast.java) | Java | 23 | 0 | 9 | 32 |
| [src/sqlancer/cockroachdb/ast/CockroachDBCollate.java](/src/sqlancer/cockroachdb/ast/CockroachDBCollate.java) | Java | 20 | 0 | 9 | 29 |
| [src/sqlancer/cockroachdb/ast/CockroachDBColumnReference.java](/src/sqlancer/cockroachdb/ast/CockroachDBColumnReference.java) | Java | 11 | 0 | 7 | 18 |
| [src/sqlancer/cockroachdb/ast/CockroachDBConcatOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBConcatOperation.java) | Java | 11 | 0 | 6 | 17 |
| [src/sqlancer/cockroachdb/ast/CockroachDBConstant.java](/src/sqlancer/cockroachdb/ast/CockroachDBConstant.java) | Java | 189 | 3 | 69 | 261 |
| [src/sqlancer/cockroachdb/ast/CockroachDBExpression.java](/src/sqlancer/cockroachdb/ast/CockroachDBExpression.java) | Java | 3 | 0 | 3 | 6 |
| [src/sqlancer/cockroachdb/ast/CockroachDBFunction.java](/src/sqlancer/cockroachdb/ast/CockroachDBFunction.java) | Java | 160 | 16 | 24 | 200 |
| [src/sqlancer/cockroachdb/ast/CockroachDBFunctionCall.java](/src/sqlancer/cockroachdb/ast/CockroachDBFunctionCall.java) | Java | 19 | 0 | 9 | 28 |
| [src/sqlancer/cockroachdb/ast/CockroachDBInOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBInOperation.java) | Java | 16 | 0 | 8 | 24 |
| [src/sqlancer/cockroachdb/ast/CockroachDBIndexReference.java](/src/sqlancer/cockroachdb/ast/CockroachDBIndexReference.java) | Java | 34 | 0 | 10 | 44 |
| [src/sqlancer/cockroachdb/ast/CockroachDBJoin.java](/src/sqlancer/cockroachdb/ast/CockroachDBJoin.java) | Java | 53 | 0 | 16 | 69 |
| [src/sqlancer/cockroachdb/ast/CockroachDBMultiValuedComparison.java](/src/sqlancer/cockroachdb/ast/CockroachDBMultiValuedComparison.java) | Java | 47 | 0 | 18 | 65 |
| [src/sqlancer/cockroachdb/ast/CockroachDBNotOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBNotOperation.java) | Java | 15 | 0 | 7 | 22 |
| [src/sqlancer/cockroachdb/ast/CockroachDBOrderingTerm.java](/src/sqlancer/cockroachdb/ast/CockroachDBOrderingTerm.java) | Java | 26 | 0 | 10 | 36 |
| [src/sqlancer/cockroachdb/ast/CockroachDBRegexOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBRegexOperation.java) | Java | 31 | 0 | 12 | 43 |
| [src/sqlancer/cockroachdb/ast/CockroachDBSelect.java](/src/sqlancer/cockroachdb/ast/CockroachDBSelect.java) | Java | 11 | 0 | 7 | 18 |
| [src/sqlancer/cockroachdb/ast/CockroachDBTableReference.java](/src/sqlancer/cockroachdb/ast/CockroachDBTableReference.java) | Java | 11 | 0 | 7 | 18 |
| [src/sqlancer/cockroachdb/ast/CockroachDBTypeAnnotation.java](/src/sqlancer/cockroachdb/ast/CockroachDBTypeAnnotation.java) | Java | 18 | 0 | 8 | 26 |
| [src/sqlancer/cockroachdb/ast/CockroachDBUnaryArithmeticOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBUnaryArithmeticOperation.java) | Java | 25 | 0 | 11 | 36 |
| [src/sqlancer/cockroachdb/ast/CockroachDBUnaryPostfixOperation.java](/src/sqlancer/cockroachdb/ast/CockroachDBUnaryPostfixOperation.java) | Java | 29 | 0 | 10 | 39 |
| [src/sqlancer/cockroachdb/gen/CockroachDBCommentOnGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBCommentOnGenerator.java) | Java | 53 | 0 | 9 | 62 |
| [src/sqlancer/cockroachdb/gen/CockroachDBCreateStatisticsGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBCreateStatisticsGenerator.java) | Java | 22 | 0 | 7 | 29 |
| [src/sqlancer/cockroachdb/gen/CockroachDBDeleteGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBDeleteGenerator.java) | Java | 31 | 0 | 6 | 37 |
| [src/sqlancer/cockroachdb/gen/CockroachDBDropTableGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBDropTableGenerator.java) | Java | 27 | 0 | 9 | 36 |
| [src/sqlancer/cockroachdb/gen/CockroachDBDropViewGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBDropViewGenerator.java) | Java | 35 | 0 | 7 | 42 |
| [src/sqlancer/cockroachdb/gen/CockroachDBExpressionGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBExpressionGenerator.java) | Java | 327 | 3 | 32 | 362 |
| [src/sqlancer/cockroachdb/gen/CockroachDBGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBGenerator.java) | Java | 26 | 0 | 8 | 34 |
| [src/sqlancer/cockroachdb/gen/CockroachDBIndexGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBIndexGenerator.java) | Java | 62 | 2 | 8 | 72 |
| [src/sqlancer/cockroachdb/gen/CockroachDBInsertGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBInsertGenerator.java) | Java | 91 | 4 | 11 | 106 |
| [src/sqlancer/cockroachdb/gen/CockroachDBRandomQuerySynthesizer.java](/src/sqlancer/cockroachdb/gen/CockroachDBRandomQuerySynthesizer.java) | Java | 69 | 0 | 9 | 78 |
| [src/sqlancer/cockroachdb/gen/CockroachDBSetClusterSettingGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBSetClusterSettingGenerator.java) | Java | 42 | 1 | 11 | 54 |
| [src/sqlancer/cockroachdb/gen/CockroachDBSetSessionGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBSetSessionGenerator.java) | Java | 40 | 7 | 11 | 58 |
| [src/sqlancer/cockroachdb/gen/CockroachDBShowGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBShowGenerator.java) | Java | 56 | 0 | 7 | 63 |
| [src/sqlancer/cockroachdb/gen/CockroachDBTableGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBTableGenerator.java) | Java | 151 | 2 | 10 | 163 |
| [src/sqlancer/cockroachdb/gen/CockroachDBTruncateGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBTruncateGenerator.java) | Java | 34 | 1 | 7 | 42 |
| [src/sqlancer/cockroachdb/gen/CockroachDBUpdateGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBUpdateGenerator.java) | Java | 53 | 0 | 10 | 63 |
| [src/sqlancer/cockroachdb/gen/CockroachDBViewGenerator.java](/src/sqlancer/cockroachdb/gen/CockroachDBViewGenerator.java) | Java | 32 | 0 | 6 | 38 |
| [src/sqlancer/cockroachdb/oracle/CockroachDBCERTOracle.java](/src/sqlancer/cockroachdb/oracle/CockroachDBCERTOracle.java) | Java | 233 | 14 | 29 | 276 |
| [src/sqlancer/cockroachdb/oracle/CockroachDBNoRECOracle.java](/src/sqlancer/cockroachdb/oracle/CockroachDBNoRECOracle.java) | Java | 129 | 0 | 13 | 142 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPAggregateOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPAggregateOracle.java) | Java | 182 | 4 | 18 | 204 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPBase.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPBase.java) | Java | 63 | 0 | 10 | 73 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPDistinctOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPDistinctOracle.java) | Java | 44 | 0 | 8 | 52 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPExtendedWhereOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPExtendedWhereOracle.java) | Java | 56 | 0 | 11 | 67 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPGroupByOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPGroupByOracle.java) | Java | 46 | 0 | 11 | 57 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPHavingOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPHavingOracle.java) | Java | 53 | 0 | 11 | 64 |
| [src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPWhereOracle.java](/src/sqlancer/cockroachdb/oracle/tlp/CockroachDBTLPWhereOracle.java) | Java | 47 | 0 | 9 | 56 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)