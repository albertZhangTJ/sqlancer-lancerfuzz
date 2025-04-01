// SQL Type Inference Rules
// This file contains rules for inferring types in SQL expressions

// Type inference for arithmetic operations
rule arithmeticTypeInference {
    // Numeric type promotion rules
    TINYINT + TINYINT -> SMALLINT
    SMALLINT + SMALLINT -> INT
    INT + INT -> BIGINT
    BIGINT + BIGINT -> DECIMAL(20,0)
    FLOAT + FLOAT -> DOUBLE
    DOUBLE + DOUBLE -> DOUBLE
    DECIMAL(p1,s1) + DECIMAL(p2,s2) -> DECIMAL(max(p1-s1,p2-s2)+max(s1,s2), max(s1,s2))
    
    // Same rules apply for subtraction
    TINYINT - TINYINT -> SMALLINT
    SMALLINT - SMALLINT -> INT
    INT - INT -> BIGINT
    BIGINT - BIGINT -> DECIMAL(20,0)
    FLOAT - FLOAT -> DOUBLE
    DOUBLE - DOUBLE -> DOUBLE
    DECIMAL(p1,s1) - DECIMAL(p2,s2) -> DECIMAL(max(p1-s1,p2-s2)+max(s1,s2), max(s1,s2))
    
    // Multiplication rules
    TINYINT * TINYINT -> INT
    SMALLINT * SMALLINT -> INT
    INT * INT -> BIGINT
    BIGINT * BIGINT -> DECIMAL(20,0)
    FLOAT * FLOAT -> DOUBLE
    DOUBLE * DOUBLE -> DOUBLE
    DECIMAL(p1,s1) * DECIMAL(p2,s2) -> DECIMAL(p1+p2, s1+s2)
    
    // Division rules
    TINYINT / TINYINT -> DECIMAL(10,4)
    SMALLINT / SMALLINT -> DECIMAL(10,4)
    INT / INT -> DECIMAL(10,4)
    BIGINT / BIGINT -> DECIMAL(20,4)
    FLOAT / FLOAT -> DOUBLE
    DOUBLE / DOUBLE -> DOUBLE
    DECIMAL(p1,s1) / DECIMAL(p2,s2) -> DECIMAL(p1+s2+4, s1+4)
}

// Type inference for comparison operations
rule comparisonTypeInference {
    // Numeric comparisons
    NUMERIC_TYPE = NUMERIC_TYPE -> BOOLEAN
    NUMERIC_TYPE < NUMERIC_TYPE -> BOOLEAN
    NUMERIC_TYPE > NUMERIC_TYPE -> BOOLEAN
    NUMERIC_TYPE <= NUMERIC_TYPE -> BOOLEAN
    NUMERIC_TYPE >= NUMERIC_TYPE -> BOOLEAN
    NUMERIC_TYPE != NUMERIC_TYPE -> BOOLEAN
    
    // String comparisons
    STRING_TYPE = STRING_TYPE -> BOOLEAN
    STRING_TYPE < STRING_TYPE -> BOOLEAN
    STRING_TYPE > STRING_TYPE -> BOOLEAN
    STRING_TYPE <= STRING_TYPE -> BOOLEAN
    STRING_TYPE >= STRING_TYPE -> BOOLEAN
    STRING_TYPE != STRING_TYPE -> BOOLEAN
    
    // Date/Time comparisons
    DATETIME_TYPE = DATETIME_TYPE -> BOOLEAN
    DATETIME_TYPE < DATETIME_TYPE -> BOOLEAN
    DATETIME_TYPE > DATETIME_TYPE -> BOOLEAN
    DATETIME_TYPE <= DATETIME_TYPE -> BOOLEAN
    DATETIME_TYPE >= DATETIME_TYPE -> BOOLEAN
    DATETIME_TYPE != DATETIME_TYPE -> BOOLEAN
}

// Type inference for logical operations
rule logicalTypeInference {
    BOOLEAN_TYPE AND BOOLEAN_TYPE -> BOOLEAN
    BOOLEAN_TYPE OR BOOLEAN_TYPE -> BOOLEAN
    BOOLEAN_TYPE XOR BOOLEAN_TYPE -> BOOLEAN
    NOT BOOLEAN_TYPE -> BOOLEAN
}

// Type inference for string operations
rule stringTypeInference {
    STRING_TYPE || STRING_TYPE -> VARCHAR(max_length)
    CONCAT(STRING_TYPE, STRING_TYPE) -> VARCHAR(sum_length)
    SUBSTRING(STRING_TYPE, INT, INT) -> VARCHAR
    LENGTH(STRING_TYPE) -> INT
    UPPER(STRING_TYPE) -> STRING_TYPE
    LOWER(STRING_TYPE) -> STRING_TYPE
}

// Type inference for date/time operations
rule datetimeTypeInference {
    DATE + INTERVAL -> DATE
    TIME + INTERVAL -> TIME
    DATETIME + INTERVAL -> DATETIME
    TIMESTAMP + INTERVAL -> TIMESTAMP
    DATEDIFF(DATETIME_TYPE, DATETIME_TYPE) -> INT
    DATE_FORMAT(DATETIME_TYPE, STRING_TYPE) -> VARCHAR(255)
}

// Type inference for aggregation functions
rule aggregationTypeInference {
    COUNT(*) -> BIGINT
    COUNT(ANY_TYPE) -> BIGINT
    SUM(NUMERIC_TYPE) -> NUMERIC_TYPE
    AVG(NUMERIC_TYPE) -> DECIMAL(20,4)
    MIN(ANY_TYPE) -> ANY_TYPE
    MAX(ANY_TYPE) -> ANY_TYPE
    GROUP_CONCAT(STRING_TYPE) -> VARCHAR(1024)
}

// Type inference for CASE expressions
rule caseTypeInference {
    CASE WHEN BOOLEAN_TYPE THEN ANY_TYPE ELSE ANY_TYPE END -> ANY_TYPE
    CASE ANY_TYPE WHEN ANY_TYPE THEN ANY_TYPE ELSE ANY_TYPE END -> ANY_TYPE
}

// Type inference for CAST operations
rule castTypeInference {
    CAST(ANY_TYPE AS NUMERIC_TYPE) -> NUMERIC_TYPE
    CAST(ANY_TYPE AS STRING_TYPE) -> STRING_TYPE
    CAST(ANY_TYPE AS DATETIME_TYPE) -> DATETIME_TYPE
    CAST(ANY_TYPE AS BINARY_TYPE) -> BINARY_TYPE
    CAST(ANY_TYPE AS JSON_TYPE) -> JSON_TYPE
    CAST(ANY_TYPE AS BOOLEAN_TYPE) -> BOOLEAN_TYPE
    CAST(ANY_TYPE AS SPATIAL_TYPE) -> SPATIAL_TYPE
} 