# Diff Details

Date : 2023-04-17 11:36:15

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/oceanbase

Total : 90 files,  -28 codes, -189 comments, -39 blanks, all -256 lines

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/sqlancer/mysql/MySQLBugs.java](/src/sqlancer/mysql/MySQLBugs.java) | Java | -10 | -7 | -9 | -26 |
| [src/sqlancer/mysql/MySQLErrors.java](/src/sqlancer/mysql/MySQLErrors.java) | Java | -10 | 0 | -6 | -16 |
| [src/sqlancer/mysql/MySQLExpectedValueVisitor.java](/src/sqlancer/mysql/MySQLExpectedValueVisitor.java) | Java | -132 | 0 | -25 | -157 |
| [src/sqlancer/mysql/MySQLGlobalState.java](/src/sqlancer/mysql/MySQLGlobalState.java) | Java | -13 | 0 | -8 | -21 |
| [src/sqlancer/mysql/MySQLOptions.java](/src/sqlancer/mysql/MySQLOptions.java) | Java | -42 | 0 | -15 | -57 |
| [src/sqlancer/mysql/MySQLProvider.java](/src/sqlancer/mysql/MySQLProvider.java) | Java | -173 | -3 | -16 | -192 |
| [src/sqlancer/mysql/MySQLSchema.java](/src/sqlancer/mysql/MySQLSchema.java) | Java | -249 | -3 | -48 | -300 |
| [src/sqlancer/mysql/MySQLToStringVisitor.java](/src/sqlancer/mysql/MySQLToStringVisitor.java) | Java | -255 | -1 | -26 | -282 |
| [src/sqlancer/mysql/MySQLVisitor.java](/src/sqlancer/mysql/MySQLVisitor.java) | Java | -83 | 0 | -23 | -106 |
| [src/sqlancer/mysql/ast/MySQLBetweenOperation.java](/src/sqlancer/mysql/ast/MySQLBetweenOperation.java) | Java | -41 | -1 | -11 | -53 |
| [src/sqlancer/mysql/ast/MySQLBinaryComparisonOperation.java](/src/sqlancer/mysql/ast/MySQLBinaryComparisonOperation.java) | Java | -119 | -9 | -22 | -150 |
| [src/sqlancer/mysql/ast/MySQLBinaryLogicalOperation.java](/src/sqlancer/mysql/ast/MySQLBinaryLogicalOperation.java) | Java | -94 | 0 | -17 | -111 |
| [src/sqlancer/mysql/ast/MySQLBinaryOperation.java](/src/sqlancer/mysql/ast/MySQLBinaryOperation.java) | Java | -90 | -1 | -24 | -115 |
| [src/sqlancer/mysql/ast/MySQLCastOperation.java](/src/sqlancer/mysql/ast/MySQLCastOperation.java) | Java | -25 | -1 | -11 | -37 |
| [src/sqlancer/mysql/ast/MySQLCollate.java](/src/sqlancer/mysql/ast/MySQLCollate.java) | Java | -17 | 0 | -8 | -25 |
| [src/sqlancer/mysql/ast/MySQLColumnReference.java](/src/sqlancer/mysql/ast/MySQLColumnReference.java) | Java | -23 | 0 | -10 | -33 |
| [src/sqlancer/mysql/ast/MySQLComputableFunction.java](/src/sqlancer/mysql/ast/MySQLComputableFunction.java) | Java | -179 | -44 | -37 | -260 |
| [src/sqlancer/mysql/ast/MySQLConstant.java](/src/sqlancer/mysql/ast/MySQLConstant.java) | Java | -357 | -15 | -85 | -457 |
| [src/sqlancer/mysql/ast/MySQLExists.java](/src/sqlancer/mysql/ast/MySQLExists.java) | Java | -23 | 0 | -8 | -31 |
| [src/sqlancer/mysql/ast/MySQLExpression.java](/src/sqlancer/mysql/ast/MySQLExpression.java) | Java | -6 | 0 | -4 | -10 |
| [src/sqlancer/mysql/ast/MySQLInOperation.java](/src/sqlancer/mysql/ast/MySQLInOperation.java) | Java | -53 | -6 | -13 | -72 |
| [src/sqlancer/mysql/ast/MySQLJoin.java](/src/sqlancer/mysql/ast/MySQLJoin.java) | Java | -7 | 0 | -4 | -11 |
| [src/sqlancer/mysql/ast/MySQLOrderByTerm.java](/src/sqlancer/mysql/ast/MySQLOrderByTerm.java) | Java | -26 | 0 | -11 | -37 |
| [src/sqlancer/mysql/ast/MySQLSelect.java](/src/sqlancer/mysql/ast/MySQLSelect.java) | Java | -30 | 0 | -13 | -43 |
| [src/sqlancer/mysql/ast/MySQLStringExpression.java](/src/sqlancer/mysql/ast/MySQLStringExpression.java) | Java | -16 | 0 | -7 | -23 |
| [src/sqlancer/mysql/ast/MySQLTableReference.java](/src/sqlancer/mysql/ast/MySQLTableReference.java) | Java | -11 | 0 | -7 | -18 |
| [src/sqlancer/mysql/ast/MySQLUnaryPostfixOperation.java](/src/sqlancer/mysql/ast/MySQLUnaryPostfixOperation.java) | Java | -48 | 0 | -11 | -59 |
| [src/sqlancer/mysql/ast/MySQLUnaryPrefixOperation.java](/src/sqlancer/mysql/ast/MySQLUnaryPrefixOperation.java) | Java | -66 | -2 | -13 | -81 |
| [src/sqlancer/mysql/gen/MySQLAlterTable.java](/src/sqlancer/mysql/gen/MySQLAlterTable.java) | Java | -158 | -9 | -13 | -180 |
| [src/sqlancer/mysql/gen/MySQLDeleteGenerator.java](/src/sqlancer/mysql/gen/MySQLDeleteGenerator.java) | Java | -46 | -3 | -9 | -58 |
| [src/sqlancer/mysql/gen/MySQLDropIndex.java](/src/sqlancer/mysql/gen/MySQLDropIndex.java) | Java | -34 | -11 | -7 | -52 |
| [src/sqlancer/mysql/gen/MySQLExpressionGenerator.java](/src/sqlancer/mysql/gen/MySQLExpressionGenerator.java) | Java | -200 | -12 | -19 | -231 |
| [src/sqlancer/mysql/gen/MySQLInsertGenerator.java](/src/sqlancer/mysql/gen/MySQLInsertGenerator.java) | Java | -85 | 0 | -13 | -98 |
| [src/sqlancer/mysql/gen/MySQLSetGenerator.java](/src/sqlancer/mysql/gen/MySQLSetGenerator.java) | Java | -156 | -19 | -19 | -194 |
| [src/sqlancer/mysql/gen/MySQLTableGenerator.java](/src/sqlancer/mysql/gen/MySQLTableGenerator.java) | Java | -334 | -26 | -24 | -384 |
| [src/sqlancer/mysql/gen/MySQLTruncateTableGenerator.java](/src/sqlancer/mysql/gen/MySQLTruncateTableGenerator.java) | Java | -13 | 0 | -6 | -19 |
| [src/sqlancer/mysql/gen/admin/MySQLFlush.java](/src/sqlancer/mysql/gen/admin/MySQLFlush.java) | Java | -37 | -5 | -9 | -51 |
| [src/sqlancer/mysql/gen/admin/MySQLReset.java](/src/sqlancer/mysql/gen/admin/MySQLReset.java) | Java | -15 | 0 | -7 | -22 |
| [src/sqlancer/mysql/gen/datadef/MySQLIndexGenerator.java](/src/sqlancer/mysql/gen/datadef/MySQLIndexGenerator.java) | Java | -142 | -4 | -13 | -159 |
| [src/sqlancer/mysql/gen/tblmaintenance/MySQLAnalyzeTable.java](/src/sqlancer/mysql/gen/tblmaintenance/MySQLAnalyzeTable.java) | Java | -60 | -12 | -12 | -84 |
| [src/sqlancer/mysql/gen/tblmaintenance/MySQLCheckTable.java](/src/sqlancer/mysql/gen/tblmaintenance/MySQLCheckTable.java) | Java | -25 | -13 | -9 | -47 |
| [src/sqlancer/mysql/gen/tblmaintenance/MySQLChecksum.java](/src/sqlancer/mysql/gen/tblmaintenance/MySQLChecksum.java) | Java | -26 | -4 | -9 | -39 |
| [src/sqlancer/mysql/gen/tblmaintenance/MySQLOptimize.java](/src/sqlancer/mysql/gen/tblmaintenance/MySQLOptimize.java) | Java | -27 | -5 | -9 | -41 |
| [src/sqlancer/mysql/gen/tblmaintenance/MySQLRepair.java](/src/sqlancer/mysql/gen/tblmaintenance/MySQLRepair.java) | Java | -43 | -7 | -9 | -59 |
| [src/sqlancer/mysql/oracle/MySQLPivotedQuerySynthesisOracle.java](/src/sqlancer/mysql/oracle/MySQLPivotedQuerySynthesisOracle.java) | Java | -130 | 0 | -18 | -148 |
| [src/sqlancer/mysql/oracle/MySQLQueryPartitioningBase.java](/src/sqlancer/mysql/oracle/MySQLQueryPartitioningBase.java) | Java | -50 | -2 | -10 | -62 |
| [src/sqlancer/mysql/oracle/MySQLTLPWhereOracle.java](/src/sqlancer/mysql/oracle/MySQLTLPWhereOracle.java) | Java | -36 | 0 | -9 | -45 |
| [src/sqlancer/oceanbase/OceanBaseErrors.java](/src/sqlancer/oceanbase/OceanBaseErrors.java) | Java | 40 | 0 | 7 | 47 |
| [src/sqlancer/oceanbase/OceanBaseExpectedValueVisitor.java](/src/sqlancer/oceanbase/OceanBaseExpectedValueVisitor.java) | Java | 130 | 0 | 26 | 156 |
| [src/sqlancer/oceanbase/OceanBaseGlobalState.java](/src/sqlancer/oceanbase/OceanBaseGlobalState.java) | Java | 13 | 0 | 8 | 21 |
| [src/sqlancer/oceanbase/OceanBaseOptions.java](/src/sqlancer/oceanbase/OceanBaseOptions.java) | Java | 53 | 0 | 13 | 66 |
| [src/sqlancer/oceanbase/OceanBaseProvider.java](/src/sqlancer/oceanbase/OceanBaseProvider.java) | Java | 150 | 0 | 18 | 168 |
| [src/sqlancer/oceanbase/OceanBaseSchema.java](/src/sqlancer/oceanbase/OceanBaseSchema.java) | Java | 249 | 2 | 42 | 293 |
| [src/sqlancer/oceanbase/OceanBaseToStringVisitor.java](/src/sqlancer/oceanbase/OceanBaseToStringVisitor.java) | Java | 279 | 1 | 29 | 309 |
| [src/sqlancer/oceanbase/OceanBaseUserCheckException.java](/src/sqlancer/oceanbase/OceanBaseUserCheckException.java) | Java | 7 | 0 | 4 | 11 |
| [src/sqlancer/oceanbase/OceanBaseVisitor.java](/src/sqlancer/oceanbase/OceanBaseVisitor.java) | Java | 87 | 0 | 24 | 111 |
| [src/sqlancer/oceanbase/README.md](/src/sqlancer/oceanbase/README.md) | Markdown | 30 | 0 | 6 | 36 |
| [src/sqlancer/oceanbase/ast/OceanBaseAggregate.java](/src/sqlancer/oceanbase/ast/OceanBaseAggregate.java) | Java | 18 | 0 | 8 | 26 |
| [src/sqlancer/oceanbase/ast/OceanBaseBinaryComparisonOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseBinaryComparisonOperation.java) | Java | 107 | 0 | 17 | 124 |
| [src/sqlancer/oceanbase/ast/OceanBaseBinaryLogicalOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseBinaryLogicalOperation.java) | Java | 95 | 0 | 17 | 112 |
| [src/sqlancer/oceanbase/ast/OceanBaseCastOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseCastOperation.java) | Java | 25 | 1 | 11 | 37 |
| [src/sqlancer/oceanbase/ast/OceanBaseColumnName.java](/src/sqlancer/oceanbase/ast/OceanBaseColumnName.java) | Java | 11 | 0 | 7 | 18 |
| [src/sqlancer/oceanbase/ast/OceanBaseColumnReference.java](/src/sqlancer/oceanbase/ast/OceanBaseColumnReference.java) | Java | 31 | 0 | 12 | 43 |
| [src/sqlancer/oceanbase/ast/OceanBaseComputableFunction.java](/src/sqlancer/oceanbase/ast/OceanBaseComputableFunction.java) | Java | 232 | 14 | 36 | 282 |
| [src/sqlancer/oceanbase/ast/OceanBaseConstant.java](/src/sqlancer/oceanbase/ast/OceanBaseConstant.java) | Java | 511 | 2 | 104 | 617 |
| [src/sqlancer/oceanbase/ast/OceanBaseExists.java](/src/sqlancer/oceanbase/ast/OceanBaseExists.java) | Java | 23 | 0 | 8 | 31 |
| [src/sqlancer/oceanbase/ast/OceanBaseExpression.java](/src/sqlancer/oceanbase/ast/OceanBaseExpression.java) | Java | 6 | 0 | 4 | 10 |
| [src/sqlancer/oceanbase/ast/OceanBaseInOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseInOperation.java) | Java | 46 | 0 | 11 | 57 |
| [src/sqlancer/oceanbase/ast/OceanBaseJoin.java](/src/sqlancer/oceanbase/ast/OceanBaseJoin.java) | Java | 7 | 0 | 4 | 11 |
| [src/sqlancer/oceanbase/ast/OceanBaseOrderByTerm.java](/src/sqlancer/oceanbase/ast/OceanBaseOrderByTerm.java) | Java | 26 | 0 | 11 | 37 |
| [src/sqlancer/oceanbase/ast/OceanBaseSelect.java](/src/sqlancer/oceanbase/ast/OceanBaseSelect.java) | Java | 45 | 0 | 17 | 62 |
| [src/sqlancer/oceanbase/ast/OceanBaseStringExpression.java](/src/sqlancer/oceanbase/ast/OceanBaseStringExpression.java) | Java | 16 | 0 | 7 | 23 |
| [src/sqlancer/oceanbase/ast/OceanBaseTableReference.java](/src/sqlancer/oceanbase/ast/OceanBaseTableReference.java) | Java | 11 | 0 | 7 | 18 |
| [src/sqlancer/oceanbase/ast/OceanBaseText.java](/src/sqlancer/oceanbase/ast/OceanBaseText.java) | Java | 20 | 0 | 7 | 27 |
| [src/sqlancer/oceanbase/ast/OceanBaseUnaryPostfixOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseUnaryPostfixOperation.java) | Java | 48 | 0 | 11 | 59 |
| [src/sqlancer/oceanbase/ast/OceanBaseUnaryPrefixOperation.java](/src/sqlancer/oceanbase/ast/OceanBaseUnaryPrefixOperation.java) | Java | 81 | 0 | 14 | 95 |
| [src/sqlancer/oceanbase/gen/OceanBaseAlterTable.java](/src/sqlancer/oceanbase/gen/OceanBaseAlterTable.java) | Java | 62 | 0 | 13 | 75 |
| [src/sqlancer/oceanbase/gen/OceanBaseDeleteGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseDeleteGenerator.java) | Java | 43 | 0 | 9 | 52 |
| [src/sqlancer/oceanbase/gen/OceanBaseDropIndex.java](/src/sqlancer/oceanbase/gen/OceanBaseDropIndex.java) | Java | 23 | 0 | 6 | 29 |
| [src/sqlancer/oceanbase/gen/OceanBaseExpressionGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseExpressionGenerator.java) | Java | 192 | 0 | 21 | 213 |
| [src/sqlancer/oceanbase/gen/OceanBaseHintGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseHintGenerator.java) | Java | 113 | 0 | 13 | 126 |
| [src/sqlancer/oceanbase/gen/OceanBaseInsertGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseInsertGenerator.java) | Java | 96 | 0 | 12 | 108 |
| [src/sqlancer/oceanbase/gen/OceanBaseTableGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseTableGenerator.java) | Java | 249 | 2 | 25 | 276 |
| [src/sqlancer/oceanbase/gen/OceanBaseTruncateTableGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseTruncateTableGenerator.java) | Java | 13 | 0 | 6 | 19 |
| [src/sqlancer/oceanbase/gen/OceanBaseUpdateGenerator.java](/src/sqlancer/oceanbase/gen/OceanBaseUpdateGenerator.java) | Java | 52 | 0 | 10 | 62 |
| [src/sqlancer/oceanbase/gen/datadef/OceanBaseIndexGenerator.java](/src/sqlancer/oceanbase/gen/datadef/OceanBaseIndexGenerator.java) | Java | 120 | 0 | 15 | 135 |
| [src/sqlancer/oceanbase/oracle/OceanBaseNoRECOracle.java](/src/sqlancer/oceanbase/oracle/OceanBaseNoRECOracle.java) | Java | 192 | 14 | 19 | 225 |
| [src/sqlancer/oceanbase/oracle/OceanBasePivotedQuerySynthesisOracle.java](/src/sqlancer/oceanbase/oracle/OceanBasePivotedQuerySynthesisOracle.java) | Java | 145 | 0 | 19 | 164 |
| [src/sqlancer/oceanbase/oracle/OceanBaseTLPBase.java](/src/sqlancer/oceanbase/oracle/OceanBaseTLPBase.java) | Java | 54 | 0 | 10 | 64 |
| [src/sqlancer/oceanbase/oracle/OceanBaseTLPWhereOracle.java](/src/sqlancer/oceanbase/oracle/OceanBaseTLPWhereOracle.java) | Java | 36 | 0 | 9 | 45 |

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details