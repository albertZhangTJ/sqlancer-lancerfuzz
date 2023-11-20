# Diff Details

Date : 2023-11-06 16:05:31

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/cockroachdb

Total : 86 files,  2140 codes, 67 comments, 409 blanks, all 2616 lines

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details

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
| [src/sqlancer/duckdb/DuckDBErrors.java](/src/sqlancer/duckdb/DuckDBErrors.java) | Java | -88 | -5 | -23 | -116 |
| [src/sqlancer/duckdb/DuckDBOptions.java](/src/sqlancer/duckdb/DuckDBOptions.java) | Java | -122 | 0 | -36 | -158 |
| [src/sqlancer/duckdb/DuckDBProvider.java](/src/sqlancer/duckdb/DuckDBProvider.java) | Java | -141 | -1 | -23 | -165 |
| [src/sqlancer/duckdb/DuckDBSchema.java](/src/sqlancer/duckdb/DuckDBSchema.java) | Java | -239 | -5 | -38 | -282 |
| [src/sqlancer/duckdb/DuckDBToStringVisitor.java](/src/sqlancer/duckdb/DuckDBToStringVisitor.java) | Java | -83 | 0 | -9 | -92 |
| [src/sqlancer/duckdb/ast/DuckDBConstant.java](/src/sqlancer/duckdb/ast/DuckDBConstant.java) | Java | -135 | 0 | -58 | -193 |
| [src/sqlancer/duckdb/ast/DuckDBExpression.java](/src/sqlancer/duckdb/ast/DuckDBExpression.java) | Java | -3 | 0 | -3 | -6 |
| [src/sqlancer/duckdb/ast/DuckDBJoin.java](/src/sqlancer/duckdb/ast/DuckDBJoin.java) | Java | -103 | 0 | -22 | -125 |
| [src/sqlancer/duckdb/ast/DuckDBSelect.java](/src/sqlancer/duckdb/ast/DuckDBSelect.java) | Java | -12 | 0 | -7 | -19 |
| [src/sqlancer/duckdb/gen/DuckDBAlterTableGenerator.java](/src/sqlancer/duckdb/gen/DuckDBAlterTableGenerator.java) | Java | -64 | 0 | -7 | -71 |
| [src/sqlancer/duckdb/gen/DuckDBDeleteGenerator.java](/src/sqlancer/duckdb/gen/DuckDBDeleteGenerator.java) | Java | -25 | 0 | -6 | -31 |
| [src/sqlancer/duckdb/gen/DuckDBExpressionGenerator.java](/src/sqlancer/duckdb/gen/DuckDBExpressionGenerator.java) | Java | -364 | -8 | -75 | -447 |
| [src/sqlancer/duckdb/gen/DuckDBIndexGenerator.java](/src/sqlancer/duckdb/gen/DuckDBIndexGenerator.java) | Java | -53 | 0 | -7 | -60 |
| [src/sqlancer/duckdb/gen/DuckDBInsertGenerator.java](/src/sqlancer/duckdb/gen/DuckDBInsertGenerator.java) | Java | -43 | -1 | -10 | -54 |
| [src/sqlancer/duckdb/gen/DuckDBRandomQuerySynthesizer.java](/src/sqlancer/duckdb/gen/DuckDBRandomQuerySynthesizer.java) | Java | -56 | -7 | -8 | -71 |
| [src/sqlancer/duckdb/gen/DuckDBTableGenerator.java](/src/sqlancer/duckdb/gen/DuckDBTableGenerator.java) | Java | -82 | 0 | -8 | -90 |
| [src/sqlancer/duckdb/gen/DuckDBUpdateGenerator.java](/src/sqlancer/duckdb/gen/DuckDBUpdateGenerator.java) | Java | -44 | 0 | -10 | -54 |
| [src/sqlancer/duckdb/gen/DuckDBViewGenerator.java](/src/sqlancer/duckdb/gen/DuckDBViewGenerator.java) | Java | -31 | 0 | -6 | -37 |
| [src/sqlancer/duckdb/test/DuckDBNoRECOracle.java](/src/sqlancer/duckdb/test/DuckDBNoRECOracle.java) | Java | -119 | -9 | -10 | -138 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningAggregateTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningAggregateTester.java) | Java | -174 | 0 | -16 | -190 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningBase.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningBase.java) | Java | -64 | 0 | -10 | -74 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningDistinctTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningDistinctTester.java) | Java | -37 | 0 | -8 | -45 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningGroupByTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningGroupByTester.java) | Java | -44 | 0 | -10 | -54 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningHavingTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningHavingTester.java) | Java | -54 | 0 | -10 | -64 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningWhereTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningWhereTester.java) | Java | -37 | 0 | -9 | -46 |

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details