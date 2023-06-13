# Diff Details

Date : 2023-04-17 11:36:24

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/duckdb

Total : 68 files,  -1557 codes, 0 comments, -245 blanks, all -1802 lines

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/sqlancer/duckdb/DuckDBErrors.java](/src/sqlancer/duckdb/DuckDBErrors.java) | Java | 88 | 5 | 23 | 116 |
| [src/sqlancer/duckdb/DuckDBOptions.java](/src/sqlancer/duckdb/DuckDBOptions.java) | Java | 122 | 0 | 36 | 158 |
| [src/sqlancer/duckdb/DuckDBProvider.java](/src/sqlancer/duckdb/DuckDBProvider.java) | Java | 141 | 1 | 23 | 165 |
| [src/sqlancer/duckdb/DuckDBSchema.java](/src/sqlancer/duckdb/DuckDBSchema.java) | Java | 239 | 5 | 38 | 282 |
| [src/sqlancer/duckdb/DuckDBToStringVisitor.java](/src/sqlancer/duckdb/DuckDBToStringVisitor.java) | Java | 83 | 0 | 9 | 92 |
| [src/sqlancer/duckdb/ast/DuckDBConstant.java](/src/sqlancer/duckdb/ast/DuckDBConstant.java) | Java | 135 | 0 | 58 | 193 |
| [src/sqlancer/duckdb/ast/DuckDBExpression.java](/src/sqlancer/duckdb/ast/DuckDBExpression.java) | Java | 3 | 0 | 3 | 6 |
| [src/sqlancer/duckdb/ast/DuckDBJoin.java](/src/sqlancer/duckdb/ast/DuckDBJoin.java) | Java | 103 | 0 | 22 | 125 |
| [src/sqlancer/duckdb/ast/DuckDBSelect.java](/src/sqlancer/duckdb/ast/DuckDBSelect.java) | Java | 12 | 0 | 7 | 19 |
| [src/sqlancer/duckdb/gen/DuckDBAlterTableGenerator.java](/src/sqlancer/duckdb/gen/DuckDBAlterTableGenerator.java) | Java | 64 | 0 | 7 | 71 |
| [src/sqlancer/duckdb/gen/DuckDBDeleteGenerator.java](/src/sqlancer/duckdb/gen/DuckDBDeleteGenerator.java) | Java | 25 | 0 | 6 | 31 |
| [src/sqlancer/duckdb/gen/DuckDBExpressionGenerator.java](/src/sqlancer/duckdb/gen/DuckDBExpressionGenerator.java) | Java | 364 | 8 | 75 | 447 |
| [src/sqlancer/duckdb/gen/DuckDBIndexGenerator.java](/src/sqlancer/duckdb/gen/DuckDBIndexGenerator.java) | Java | 53 | 0 | 7 | 60 |
| [src/sqlancer/duckdb/gen/DuckDBInsertGenerator.java](/src/sqlancer/duckdb/gen/DuckDBInsertGenerator.java) | Java | 43 | 1 | 10 | 54 |
| [src/sqlancer/duckdb/gen/DuckDBRandomQuerySynthesizer.java](/src/sqlancer/duckdb/gen/DuckDBRandomQuerySynthesizer.java) | Java | 56 | 7 | 8 | 71 |
| [src/sqlancer/duckdb/gen/DuckDBTableGenerator.java](/src/sqlancer/duckdb/gen/DuckDBTableGenerator.java) | Java | 82 | 0 | 8 | 90 |
| [src/sqlancer/duckdb/gen/DuckDBUpdateGenerator.java](/src/sqlancer/duckdb/gen/DuckDBUpdateGenerator.java) | Java | 44 | 0 | 10 | 54 |
| [src/sqlancer/duckdb/gen/DuckDBViewGenerator.java](/src/sqlancer/duckdb/gen/DuckDBViewGenerator.java) | Java | 31 | 0 | 6 | 37 |
| [src/sqlancer/duckdb/test/DuckDBNoRECOracle.java](/src/sqlancer/duckdb/test/DuckDBNoRECOracle.java) | Java | 119 | 9 | 10 | 138 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningAggregateTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningAggregateTester.java) | Java | 174 | 0 | 16 | 190 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningBase.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningBase.java) | Java | 77 | 0 | 13 | 90 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningDistinctTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningDistinctTester.java) | Java | 37 | 0 | 8 | 45 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningGroupByTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningGroupByTester.java) | Java | 44 | 0 | 10 | 54 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningHavingTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningHavingTester.java) | Java | 54 | 0 | 10 | 64 |
| [src/sqlancer/duckdb/test/DuckDBQueryPartitioningWhereTester.java](/src/sqlancer/duckdb/test/DuckDBQueryPartitioningWhereTester.java) | Java | 37 | 0 | 9 | 46 |
| [src/sqlancer/oceanbase/OceanBaseErrors.java](/src/sqlancer/oceanbase/OceanBaseErrors.java) | Java | -40 | 0 | -7 | -47 |
| [src/sqlancer/oceanbase/OceanBaseExpectedValueVisitor.java](/src/sqlancer/oceanbase/OceanBaseExpectedValueVisitor.java) | Java | -130 | 0 | -26 | -156 |
| [src/sqlancer/oceanbase/OceanBaseGlobalState.java](/src/sqlancer/oceanbase/OceanBaseGlobalState.java) | Java | -13 | 0 | -8 | -21 |
| [src/sqlancer/oceanbase/OceanBaseOptions.java](/src/sqlancer/oceanbase/OceanBaseOptions.java) | Java | -53 | 0 | -13 | -66 |
| [src/sqlancer/oceanbase/OceanBaseProvider.java](/src/sqlancer/oceanbase/OceanBaseProvider.java) | Java | -150 | 0 | -18 | -168 |
| [src/sqlancer/oceanbase/OceanBaseSchema.java](/src/sqlancer/oceanbase/OceanBaseSchema.java) | Java | -249 | -2 | -42 | -293 |
| [src/sqlancer/oceanbase/OceanBaseToStringVisitor.java](/src/sqlancer/oceanbase/OceanBaseToStringVisitor.java) | Java | -279 | -1 | -29 | -309 |
| [src/sqlancer/oceanbase/OceanBaseUserCheckException.java](/src/sqlancer/oceanbase/OceanBaseUserCheckException.java) | Java | -7 | 0 | -4 | -11 |
| [src/sqlancer/oceanbase/OceanBaseVisitor.java](/src/sqlancer/oceanbase/OceanBaseVisitor.java) | Java | -87 | 0 | -24 | -111 |
| [src/sqlancer/oceanbase/README.md](/src/sqlancer/oceanbase/README.md) | Markdown | -30 | 0 | -6 | -36 |
| [src/sqlancer/oceanbase/ast/OceanBaseAggregate.java](/src/sqlancer/oceanbase/ast/OceanBaseAggregate.java) | Java | -18 | 0 | -8 | -26 |
| [src/sqlancer/oceanbase/ast/OceanBaseBinaryComparisonOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseBinaryComparisonOperation.java) | Java | -107 | 0 | -17 | -124 |
| [src/sqlancer/oceanbase/ast/OceanBaseBinaryLogicalOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseBinaryLogicalOperation.java) | Java | -95 | 0 | -17 | -112 |
| [src/sqlancer/oceanbase/ast/OceanBaseCastOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseCastOperation.java) | Java | -25 | -1 | -11 | -37 |
| [src/sqlancer/oceanbase/ast/OceanBaseColumnName.java](/src/sqlancer/oceanbase/ast/OceanBaseColumnName.java) | Java | -11 | 0 | -7 | -18 |
| [src/sqlancer/oceanbase/ast/OceanBaseColumnReference.java](/src/sqlancer/oceanbase/ast/OceanBaseColumnReference.java) | Java | -31 | 0 | -12 | -43 |
| [src/sqlancer/oceanbase/ast/OceanBaseComputableFunction.java](/src/sqlancer/oceanbase/ast/OceanBaseComputableFunction.java) | Java | -232 | -14 | -36 | -282 |
| [src/sqlancer/oceanbase/ast/OceanBaseConstant.java](/src/sqlancer/oceanbase/ast/OceanBaseConstant.java) | Java | -511 | -2 | -104 | -617 |
| [src/sqlancer/oceanbase/ast/OceanBaseExists.java](/src/sqlancer/oceanbase/ast/OceanBaseExists.java) | Java | -23 | 0 | -8 | -31 |
| [src/sqlancer/oceanbase/ast/OceanBaseExpression.java](/src/sqlancer/oceanbase/ast/OceanBaseExpression.java) | Java | -6 | 0 | -4 | -10 |
| [src/sqlancer/oceanbase/ast/OceanBaseInOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseInOperation.java) | Java | -46 | 0 | -11 | -57 |
| [src/sqlancer/oceanbase/ast/OceanBaseJoin.java](/src/sqlancer/oceanbase/ast/OceanBaseJoin.java) | Java | -7 | 0 | -4 | -11 |
| [src/sqlancer/oceanbase/ast/OceanBaseOrderByTerm.java](/src/sqlancer/oceanbase/ast/OceanBaseOrderByTerm.java) | Java | -26 | 0 | -11 | -37 |
| [src/sqlancer/oceanbase/ast/OceanBaseSelect.java](/src/sqlancer/oceanbase/ast/OceanBaseSelect.java) | Java | -45 | 0 | -17 | -62 |
| [src/sqlancer/oceanbase/ast/OceanBaseStringExpression.java](/src/sqlancer/oceanbase/ast/OceanBaseStringExpression.java) | Java | -16 | 0 | -7 | -23 |
| [src/sqlancer/oceanbase/ast/OceanBaseTableReference.java](/src/sqlancer/oceanbase/ast/OceanBaseTableReference.java) | Java | -11 | 0 | -7 | -18 |
| [src/sqlancer/oceanbase/ast/OceanBaseText.java](/src/sqlancer/oceanbase/ast/OceanBaseText.java) | Java | -20 | 0 | -7 | -27 |
| [src/sqlancer/oceanbase/ast/OceanBaseUnaryPostfixOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseUnaryPostfixOperation.java) | Java | -48 | 0 | -11 | -59 |
| [src/sqlancer/oceanbase/ast/OceanBaseUnaryPrefixOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseUnaryPrefixOperation.java) | Java | -81 | 0 | -14 | -95 |
| [src/sqlancer/oceanbase/gen/OceanBaseAlterTable.java](/src/sqlancer/oceanbase/gen/OceanBaseAlterTable.java) | Java | -62 | 0 | -13 | -75 |
| [src/sqlancer/oceanbase/gen/OceanBaseDeleteGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseDeleteGenerator.java) | Java | -43 | 0 | -9 | -52 |
| [src/sqlancer/oceanbase/gen/OceanBaseDropIndex.java](/src/sqlancer/oceanbase/gen/OceanBaseDropIndex.java) | Java | -23 | 0 | -6 | -29 |
| [src/sqlancer/oceanbase/gen/OceanBaseExpressionGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseExpressionGenerator.java) | Java | -192 | 0 | -21 | -213 |
| [src/sqlancer/oceanbase/gen/OceanBaseHintGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseHintGenerator.java) | Java | -113 | 0 | -13 | -126 |
| [src/sqlancer/oceanbase/gen/OceanBaseInsertGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseInsertGenerator.java) | Java | -96 | 0 | -12 | -108 |
| [src/sqlancer/oceanbase/gen/OceanBaseTableGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseTableGenerator.java) | Java | -249 | -2 | -25 | -276 |
| [src/sqlancer/oceanbase/gen/OceanBaseTruncateTableGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseTruncateTableGenerator.java) | Java | -13 | 0 | -6 | -19 |
| [src/sqlancer/oceanbase/gen/OceanBaseUpdateGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseUpdateGenerator.java) | Java | -52 | 0 | -10 | -62 |
| [src/sqlancer/oceanbase/gen/datadef/OceanBaseIndexGenerator.java](/src/sqlancer/oceanbase/gen/datadef/OceanBaseIndexGenerator.java) | Java | -120 | 0 | -15 | -135 |
| [src/sqlancer/oceanbase/oracle/OceanBaseNoRECOracle.java](/src/sqlancer/oceanbase/oracle/OceanBaseNoRECOracle.java) | Java | -192 | -14 | -19 | -225 |
| [src/sqlancer/oceanbase/oracle/OceanBasePivotedQuerySynthesisOracle.java](/src/sqlancer/oceanbase/oracle/OceanBasePivotedQuerySynthesisOracle.java) | Java | -145 | 0 | -19 | -164 |
| [src/sqlancer/oceanbase/oracle/OceanBaseTLPBase.java](/src/sqlancer/oceanbase/oracle/OceanBaseTLPBase.java) | Java | -54 | 0 | -10 | -64 |
| [src/sqlancer/oceanbase/oracle/OceanBaseTLPWhereOracle.java](/src/sqlancer/oceanbase/oracle/OceanBaseTLPWhereOracle.java) | Java | -36 | 0 | -9 | -45 |

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details