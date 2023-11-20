# Diff Details

Date : 2023-11-06 16:07:52

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/mongodb

Total : 65 files,  761 codes, -67 comments, 132 blanks, all 826 lines

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/sqlancer/mariadb/MariaDBBugs.java](/src/sqlancer/mariadb/MariaDBBugs.java) | Java | -6 | -1 | -5 | -12 |
| [src/sqlancer/mariadb/MariaDBErrors.java](/src/sqlancer/mariadb/MariaDBErrors.java) | Java | -17 | 0 | -6 | -23 |
| [src/sqlancer/mariadb/MariaDBOptions.java](/src/sqlancer/mariadb/MariaDBOptions.java) | Java | -32 | 0 | -12 | -44 |
| [src/sqlancer/mariadb/MariaDBProvider.java](/src/sqlancer/mariadb/MariaDBProvider.java) | Java | -187 | 0 | -17 | -204 |
| [src/sqlancer/mariadb/MariaDBSchema.java](/src/sqlancer/mariadb/MariaDBSchema.java) | Java | -201 | -1 | -44 | -246 |
| [src/sqlancer/mariadb/ast/MariaDBAggregate.java](/src/sqlancer/mariadb/ast/MariaDBAggregate.java) | Java | -18 | 0 | -8 | -26 |
| [src/sqlancer/mariadb/ast/MariaDBBinaryOperator.java](/src/sqlancer/mariadb/ast/MariaDBBinaryOperator.java) | Java | -38 | -2 | -17 | -57 |
| [src/sqlancer/mariadb/ast/MariaDBColumnName.java](/src/sqlancer/mariadb/ast/MariaDBColumnName.java) | Java | -11 | 0 | -7 | -18 |
| [src/sqlancer/mariadb/ast/MariaDBConstant.java](/src/sqlancer/mariadb/ast/MariaDBConstant.java) | Java | -73 | 0 | -34 | -107 |
| [src/sqlancer/mariadb/ast/MariaDBExpression.java](/src/sqlancer/mariadb/ast/MariaDBExpression.java) | Java | -3 | 0 | -3 | -6 |
| [src/sqlancer/mariadb/ast/MariaDBFunction.java](/src/sqlancer/mariadb/ast/MariaDBFunction.java) | Java | -16 | 0 | -8 | -24 |
| [src/sqlancer/mariadb/ast/MariaDBFunctionName.java](/src/sqlancer/mariadb/ast/MariaDBFunctionName.java) | Java | -36 | -9 | -15 | -60 |
| [src/sqlancer/mariadb/ast/MariaDBInOperation.java](/src/sqlancer/mariadb/ast/MariaDBInOperation.java) | Java | -21 | 0 | -9 | -30 |
| [src/sqlancer/mariadb/ast/MariaDBPostfixUnaryOperation.java](/src/sqlancer/mariadb/ast/MariaDBPostfixUnaryOperation.java) | Java | -29 | 0 | -12 | -41 |
| [src/sqlancer/mariadb/ast/MariaDBSelectStatement.java](/src/sqlancer/mariadb/ast/MariaDBSelectStatement.java) | Java | -44 | 0 | -18 | -62 |
| [src/sqlancer/mariadb/ast/MariaDBStringVisitor.java](/src/sqlancer/mariadb/ast/MariaDBStringVisitor.java) | Java | -117 | 0 | -18 | -135 |
| [src/sqlancer/mariadb/ast/MariaDBText.java](/src/sqlancer/mariadb/ast/MariaDBText.java) | Java | -20 | 0 | -7 | -27 |
| [src/sqlancer/mariadb/ast/MariaDBUnaryPrefixOperation.java](/src/sqlancer/mariadb/ast/MariaDBUnaryPrefixOperation.java) | Java | -26 | 0 | -13 | -39 |
| [src/sqlancer/mariadb/ast/MariaDBVisitor.java](/src/sqlancer/mariadb/ast/MariaDBVisitor.java) | Java | -43 | 0 | -15 | -58 |
| [src/sqlancer/mariadb/gen/MariaDBCommon.java](/src/sqlancer/mariadb/gen/MariaDBCommon.java) | Java | -13 | 0 | -6 | -19 |
| [src/sqlancer/mariadb/gen/MariaDBExpressionGenerator.java](/src/sqlancer/mariadb/gen/MariaDBExpressionGenerator.java) | Java | -126 | -6 | -18 | -150 |
| [src/sqlancer/mariadb/gen/MariaDBIndexGenerator.java](/src/sqlancer/mariadb/gen/MariaDBIndexGenerator.java) | Java | -47 | -5 | -9 | -61 |
| [src/sqlancer/mariadb/gen/MariaDBInsertGenerator.java](/src/sqlancer/mariadb/gen/MariaDBInsertGenerator.java) | Java | -34 | 0 | -6 | -40 |
| [src/sqlancer/mariadb/gen/MariaDBSetGenerator.java](/src/sqlancer/mariadb/gen/MariaDBSetGenerator.java) | Java | -139 | -38 | -21 | -198 |
| [src/sqlancer/mariadb/gen/MariaDBTableAdminCommandGenerator.java](/src/sqlancer/mariadb/gen/MariaDBTableAdminCommandGenerator.java) | Java | -74 | 0 | -13 | -87 |
| [src/sqlancer/mariadb/gen/MariaDBTableGenerator.java](/src/sqlancer/mariadb/gen/MariaDBTableGenerator.java) | Java | -128 | -5 | -15 | -148 |
| [src/sqlancer/mariadb/gen/MariaDBTruncateGenerator.java](/src/sqlancer/mariadb/gen/MariaDBTruncateGenerator.java) | Java | -14 | 0 | -6 | -20 |
| [src/sqlancer/mariadb/gen/MariaDBUpdateGenerator.java](/src/sqlancer/mariadb/gen/MariaDBUpdateGenerator.java) | Java | -39 | -1 | -6 | -46 |
| [src/sqlancer/mariadb/oracle/MariaDBNoRECOracle.java](/src/sqlancer/mariadb/oracle/MariaDBNoRECOracle.java) | Java | -113 | -1 | -12 | -126 |
| [src/sqlancer/mongodb/MongoDBComparatorHelper.java](/src/sqlancer/mongodb/MongoDBComparatorHelper.java) | Java | 87 | 0 | 11 | 98 |
| [src/sqlancer/mongodb/MongoDBConnection.java](/src/sqlancer/mongodb/MongoDBConnection.java) | Java | 26 | 0 | 10 | 36 |
| [src/sqlancer/mongodb/MongoDBLoggableFactory.java](/src/sqlancer/mongodb/MongoDBLoggableFactory.java) | Java | 33 | 0 | 8 | 41 |
| [src/sqlancer/mongodb/MongoDBOptions.java](/src/sqlancer/mongodb/MongoDBOptions.java) | Java | 58 | 0 | 17 | 75 |
| [src/sqlancer/mongodb/MongoDBProvider.java](/src/sqlancer/mongodb/MongoDBProvider.java) | Java | 108 | 0 | 21 | 129 |
| [src/sqlancer/mongodb/MongoDBQueryAdapter.java](/src/sqlancer/mongodb/MongoDBQueryAdapter.java) | Java | 12 | 0 | 4 | 16 |
| [src/sqlancer/mongodb/MongoDBQueryProvider.java](/src/sqlancer/mongodb/MongoDBQueryProvider.java) | Java | 5 | 0 | 2 | 7 |
| [src/sqlancer/mongodb/MongoDBSchema.java](/src/sqlancer/mongodb/MongoDBSchema.java) | Java | 74 | 0 | 24 | 98 |
| [src/sqlancer/mongodb/ast/MongoDBBinaryComparisonNode.java](/src/sqlancer/mongodb/ast/MongoDBBinaryComparisonNode.java) | Java | 13 | 0 | 4 | 17 |
| [src/sqlancer/mongodb/ast/MongoDBBinaryLogicalNode.java](/src/sqlancer/mongodb/ast/MongoDBBinaryLogicalNode.java) | Java | 13 | 0 | 4 | 17 |
| [src/sqlancer/mongodb/ast/MongoDBConstant.java](/src/sqlancer/mongodb/ast/MongoDBConstant.java) | Java | 188 | 0 | 65 | 253 |
| [src/sqlancer/mongodb/ast/MongoDBExpression.java](/src/sqlancer/mongodb/ast/MongoDBExpression.java) | Java | 3 | 0 | 2 | 5 |
| [src/sqlancer/mongodb/ast/MongoDBRegexNode.java](/src/sqlancer/mongodb/ast/MongoDBRegexNode.java) | Java | 18 | 0 | 7 | 25 |
| [src/sqlancer/mongodb/ast/MongoDBSelect.java](/src/sqlancer/mongodb/ast/MongoDBSelect.java) | Java | 85 | 0 | 20 | 105 |
| [src/sqlancer/mongodb/ast/MongoDBUnaryLogicalOperatorNode.java](/src/sqlancer/mongodb/ast/MongoDBUnaryLogicalOperatorNode.java) | Java | 12 | 0 | 5 | 17 |
| [src/sqlancer/mongodb/ast/MongoDBUnsupportedPredicate.java](/src/sqlancer/mongodb/ast/MongoDBUnsupportedPredicate.java) | Java | 4 | 0 | 4 | 8 |
| [src/sqlancer/mongodb/gen/MongoDBComputedExpressionGenerator.java](/src/sqlancer/mongodb/gen/MongoDBComputedExpressionGenerator.java) | Java | 72 | 0 | 18 | 90 |
| [src/sqlancer/mongodb/gen/MongoDBConstantGenerator.java](/src/sqlancer/mongodb/gen/MongoDBConstantGenerator.java) | Java | 78 | 0 | 9 | 87 |
| [src/sqlancer/mongodb/gen/MongoDBIndexGenerator.java](/src/sqlancer/mongodb/gen/MongoDBIndexGenerator.java) | Java | 21 | 0 | 5 | 26 |
| [src/sqlancer/mongodb/gen/MongoDBInsertGenerator.java](/src/sqlancer/mongodb/gen/MongoDBInsertGenerator.java) | Java | 29 | 0 | 10 | 39 |
| [src/sqlancer/mongodb/gen/MongoDBMatchExpressionGenerator.java](/src/sqlancer/mongodb/gen/MongoDBMatchExpressionGenerator.java) | Java | 245 | 0 | 47 | 292 |
| [src/sqlancer/mongodb/gen/MongoDBTableGenerator.java](/src/sqlancer/mongodb/gen/MongoDBTableGenerator.java) | Java | 45 | 0 | 10 | 55 |
| [src/sqlancer/mongodb/query/MongoDBCreateIndexQuery.java](/src/sqlancer/mongodb/query/MongoDBCreateIndexQuery.java) | Java | 64 | 0 | 14 | 78 |
| [src/sqlancer/mongodb/query/MongoDBCreateTableQuery.java](/src/sqlancer/mongodb/query/MongoDBCreateTableQuery.java) | Java | 98 | 0 | 18 | 116 |
| [src/sqlancer/mongodb/query/MongoDBInsertQuery.java](/src/sqlancer/mongodb/query/MongoDBInsertQuery.java) | Java | 76 | 0 | 12 | 88 |
| [src/sqlancer/mongodb/query/MongoDBRemoveQuery.java](/src/sqlancer/mongodb/query/MongoDBRemoveQuery.java) | Java | 49 | 0 | 11 | 60 |
| [src/sqlancer/mongodb/query/MongoDBSelectQuery.java](/src/sqlancer/mongodb/query/MongoDBSelectQuery.java) | Java | 127 | 2 | 19 | 148 |
| [src/sqlancer/mongodb/test/MongoDBColumnTestReference.java](/src/sqlancer/mongodb/test/MongoDBColumnTestReference.java) | Java | 31 | 0 | 10 | 41 |
| [src/sqlancer/mongodb/test/MongoDBDocumentRemovalBase.java](/src/sqlancer/mongodb/test/MongoDBDocumentRemovalBase.java) | Java | 81 | 0 | 10 | 91 |
| [src/sqlancer/mongodb/test/MongoDBDocumentRemovalTester.java](/src/sqlancer/mongodb/test/MongoDBDocumentRemovalTester.java) | Java | 37 | 0 | 13 | 50 |
| [src/sqlancer/mongodb/test/MongoDBQueryPartitioningBase.java](/src/sqlancer/mongodb/test/MongoDBQueryPartitioningBase.java) | Java | 84 | 0 | 10 | 94 |
| [src/sqlancer/mongodb/test/MongoDBQueryPartitioningWhereTester.java](/src/sqlancer/mongodb/test/MongoDBQueryPartitioningWhereTester.java) | Java | 35 | 0 | 14 | 49 |
| [src/sqlancer/mongodb/visitor/MongoDBNegateVisitor.java](/src/sqlancer/mongodb/visitor/MongoDBNegateVisitor.java) | Java | 147 | 0 | 15 | 162 |
| [src/sqlancer/mongodb/visitor/MongoDBToLogVisitor.java](/src/sqlancer/mongodb/visitor/MongoDBToLogVisitor.java) | Java | 174 | 0 | 22 | 196 |
| [src/sqlancer/mongodb/visitor/MongoDBToQueryVisitor.java](/src/sqlancer/mongodb/visitor/MongoDBToQueryVisitor.java) | Java | 159 | 0 | 26 | 185 |
| [src/sqlancer/mongodb/visitor/MongoDBVisitor.java](/src/sqlancer/mongodb/visitor/MongoDBVisitor.java) | Java | 35 | 0 | 11 | 46 |

[Summary](results.md) / [Details](details.md) / [Diff Summary](diff.md) / Diff Details