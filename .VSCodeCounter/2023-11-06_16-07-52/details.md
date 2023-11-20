# Details

Date : 2023-11-06 16:07:52

Directory /home/albert/Desktop/NUS/UROP/SQLancer/sqlancer/src/sqlancer/mongodb

Total : 36 files,  2426 codes, 2 comments, 512 blanks, all 2940 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
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

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)