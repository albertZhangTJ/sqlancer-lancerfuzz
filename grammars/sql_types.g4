// SQL Data Types for type annotations
// These types are used to annotate grammar rules with their expected SQL data types

// Numeric Types
NUMERIC_TYPE
    : TINYINT
    | SMALLINT
    | MEDIUMINT
    | INT
    | BIGINT
    | FLOAT
    | DOUBLE
    | DECIMAL
    ;

// String Types
STRING_TYPE
    : CHAR
    | VARCHAR
    | TEXT
    | ENUM
    | SET
    ;

// Date/Time Types
DATETIME_TYPE
    : DATE
    | TIME
    | DATETIME
    | TIMESTAMP
    | YEAR
    ;

// Binary Types
BINARY_TYPE
    : BINARY
    | VARBINARY
    | BLOB
    ;

// JSON Type
JSON_TYPE
    : JSON
    ;

// Boolean Type
BOOLEAN_TYPE
    : BOOLEAN
    | BOOL
    ;

// Spatial Types
SPATIAL_TYPE
    : GEOMETRY
    | POINT
    | LINESTRING
    | POLYGON
    | MULTIPOINT
    | MULTILINESTRING
    | MULTIPOLYGON
    | GEOMETRYCOLLECTION
    ;

// NULL Type
NULL_TYPE
    : NULL
    ;

// Any Type (for expressions that can return any type)
ANY_TYPE
    : ANY
    ; 