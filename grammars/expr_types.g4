// Type annotations for SQL expressions
// This file contains type annotations for the core expression rules in SQL

// Expression rules with type annotations
expr returns [SQLType type]
    : boolPri (IS_SYMBOL notRule? type = (TRUE_SYMBOL | FALSE_SYMBOL | UNKNOWN_SYMBOL))? # exprIs
    | NOT_SYMBOL expr # exprNot
    | expr op = (AND_SYMBOL | LOGICAL_AND_OPERATOR) expr # exprAnd
    | expr XOR_SYMBOL expr # exprXor
    | expr op = (OR_SYMBOL | LOGICAL_OR_OPERATOR) expr # exprOr
    ;

boolPri returns [SQLType type]
    : predicate # primaryExprPredicate
    | boolPri IS_SYMBOL notRule? NULL_SYMBOL # primaryExprIsNull
    | boolPri compOp predicate # primaryExprCompare
    | boolPri compOp (ALL_SYMBOL | ANY_SYMBOL) subquery # primaryExprAllAny
    ;

predicate returns [SQLType type]
    : bitExpr (
        notRule? predicateOperations
        | MEMBER_SYMBOL OF_SYMBOL? simpleExprWithParentheses
        | SOUNDS_SYMBOL LIKE_SYMBOL bitExpr
    )?
    ;

bitExpr returns [SQLType type]
    : simpleExpr # bitExprSimple
    | bitExpr op = BITWISE_XOR_OPERATOR bitExpr # bitExprXor
    | bitExpr op = (MULT_OPERATOR | DIV_OPERATOR | MOD_OPERATOR | DIV_SYMBOL | MOD_SYMBOL) bitExpr # bitExprArithmetic
    | bitExpr op = (PLUS_OPERATOR | MINUS_OPERATOR) bitExpr # bitExprAddSub
    | bitExpr op = (PLUS_OPERATOR | MINUS_OPERATOR) INTERVAL_SYMBOL expr interval # bitExprInterval
    | bitExpr op = (SHIFT_LEFT_OPERATOR | SHIFT_RIGHT_OPERATOR) bitExpr # bitExprShift
    | bitExpr op = BITWISE_AND_OPERATOR bitExpr # bitExprAnd
    | bitExpr op = BITWISE_OR_OPERATOR bitExpr # bitExprOr
    ;

simpleExpr returns [SQLType type]
    : columnRef jsonOperator? # simpleExprColumnRef
    | runtimeFunctionCall # simpleExprRuntimeFunction
    | functionCall # simpleExprFunction
    | simpleExpr COLLATE_SYMBOL textOrIdentifier # simpleExprCollate
    | literalOrNull # simpleExprLiteral
    | PARAM_MARKER # simpleExprParamMarker
    | rvalueSystemOrUserVariable # simpleExpressionRValue
    | inExpressionUserVariableAssignment # simpleExprUserVariableAssignment
    | sumExpr # simpleExprSum
    | groupingOperation # simpleExprGroupingOperation
    | windowFunctionCall # simpleExprWindowingFunction
    | simpleExpr CONCAT_PIPES_SYMBOL simpleExpr # simpleExprConcat
    | op = (PLUS_OPERATOR | MINUS_OPERATOR | BITWISE_NOT_OPERATOR) simpleExpr # simpleExprUnary
    | not2Rule simpleExpr # simpleExprNot
    | ROW_SYMBOL? OPEN_PAR_SYMBOL exprList CLOSE_PAR_SYMBOL # simpleExprList
    | EXISTS_SYMBOL? subquery # simpleExprSubQuery
    | OPEN_CURLY_SYMBOL identifier expr CLOSE_CURLY_SYMBOL # simpleExprOdbc
    | MATCH_SYMBOL identListArg AGAINST_SYMBOL OPEN_PAR_SYMBOL bitExpr fulltextOptions? CLOSE_PAR_SYMBOL # simpleExprMatch
    | BINARY_SYMBOL simpleExpr # simpleExprBinary
    | CAST_SYMBOL OPEN_PAR_SYMBOL expr (AT_SYMBOL LOCAL_SYMBOL)? AS_SYMBOL castType arrayCast? CLOSE_PAR_SYMBOL # simpleExprCast
    | CAST_SYMBOL OPEN_PAR_SYMBOL expr AT_SYMBOL TIME_SYMBOL ZONE_SYMBOL INTERVAL_SYMBOL? textStringLiteral AS_SYMBOL DATETIME_SYMBOL typeDatetimePrecision CLOSE_PAR_SYMBOL # simpleExprCastTime
    | CASE_SYMBOL expr? (whenExpression thenExpression)+ elseExpression? END_SYMBOL # simpleExprCase
    | CONVERT_SYMBOL OPEN_PAR_SYMBOL expr COMMA_SYMBOL castType CLOSE_PAR_SYMBOL # simpleExprConvert
    | CONVERT_SYMBOL OPEN_PAR_SYMBOL expr USING_SYMBOL charsetName CLOSE_PAR_SYMBOL # simpleExprConvertUsing
    | DEFAULT_SYMBOL OPEN_PAR_SYMBOL simpleIdentifier CLOSE_PAR_SYMBOL # simpleExprDefault
    | VALUES_SYMBOL OPEN_PAR_SYMBOL simpleIdentifier CLOSE_PAR_SYMBOL # simpleExprValues
    | INTERVAL_SYMBOL expr interval PLUS_OPERATOR expr # simpleExprInterval
    ;

// Type annotations for literals
literalOrNull returns [SQLType type]
    : NUMERIC_LITERAL # literalNumeric
    | STRING_LITERAL # literalString
    | NULL_SYMBOL # literalNull
    | TRUE_SYMBOL # literalTrue
    | FALSE_SYMBOL # literalFalse
    ;

// Type annotations for functions
functionCall returns [SQLType type]
    : functionName OPEN_PAR_SYMBOL (functionArgs? | MULT_OPERATOR) CLOSE_PAR_SYMBOL
    ;

// Type annotations for operators
compOp returns [SQLType type]
    : EQUAL_OPERATOR # compOpEqual
    | NULL_SAFE_EQUAL_OPERATOR # compOpNullSafeEqual
    | GREATER_OR_EQUAL_OPERATOR # compOpGreaterEqual
    | GREATER_THAN_OPERATOR # compOpGreater
    | LESS_OR_EQUAL_OPERATOR # compOpLessEqual
    | LESS_THAN_OPERATOR # compOpLess
    | NOT_EQUAL_OPERATOR # compOpNotEqual
    ;

// Type annotations for casts
castType returns [SQLType type]
    : BINARY_SYMBOL fieldLength? # castTypeBinary
    | CHAR_SYMBOL fieldLength? charsetWithOptBinary? # castTypeChar
    | nchar fieldLength? # castTypeNChar
    | SIGNED_SYMBOL INT_SYMBOL? # castTypeSigned
    | UNSIGNED_SYMBOL INT_SYMBOL? # castTypeUnsigned
    | DATE_SYMBOL # castTypeDate
    | TIME_SYMBOL typeDatetimePrecision? # castTypeTime
    | DATETIME_SYMBOL typeDatetimePrecision? # castTypeDateTime
    | DECIMAL_SYMBOL floatOptions? # castTypeDecimal
    | JSON_SYMBOL # castTypeJson
    | realType # castTypeReal
    | FLOAT_SYMBOL standardFloatOptions? # castTypeFloat
    | spatialType # castTypeSpatial
    ; 