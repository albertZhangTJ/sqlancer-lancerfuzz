// SQL Type Definitions
// This file contains the type definitions used in the grammar annotations

// Base SQL Type interface
interface SQLType {
    boolean isNullable();
    int getPrecision();
    int getScale();
    String getCharset();
    String getCollation();
}

// Numeric Types
class NumericType implements SQLType {
    enum Kind {
        TINYINT,
        SMALLINT,
        MEDIUMINT,
        INT,
        BIGINT,
        FLOAT,
        DOUBLE,
        DECIMAL
    }
    
    Kind kind;
    boolean unsigned;
    int precision;
    int scale;
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return precision; }
    
    @Override
    public int getScale() { return scale; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// String Types
class StringType implements SQLType {
    enum Kind {
        CHAR,
        VARCHAR,
        TEXT,
        ENUM,
        SET
    }
    
    Kind kind;
    int length;
    String charset;
    String collation;
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return length; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return charset; }
    
    @Override
    public String getCollation() { return collation; }
}

// Date/Time Types
class DateTimeType implements SQLType {
    enum Kind {
        DATE,
        TIME,
        DATETIME,
        TIMESTAMP,
        YEAR
    }
    
    Kind kind;
    int precision;
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return precision; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// Binary Types
class BinaryType implements SQLType {
    enum Kind {
        BINARY,
        VARBINARY,
        BLOB
    }
    
    Kind kind;
    int length;
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return length; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// JSON Type
class JsonType implements SQLType {
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return 0; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// Boolean Type
class BooleanType implements SQLType {
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return 0; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// Spatial Types
class SpatialType implements SQLType {
    enum Kind {
        GEOMETRY,
        POINT,
        LINESTRING,
        POLYGON,
        MULTIPOINT,
        MULTILINESTRING,
        MULTIPOLYGON,
        GEOMETRYCOLLECTION
    }
    
    Kind kind;
    boolean nullable;
    
    @Override
    public boolean isNullable() { return nullable; }
    
    @Override
    public int getPrecision() { return 0; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// NULL Type
class NullType implements SQLType {
    @Override
    public boolean isNullable() { return true; }
    
    @Override
    public int getPrecision() { return 0; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
}

// Any Type (for expressions that can return any type)
class AnyType implements SQLType {
    @Override
    public boolean isNullable() { return true; }
    
    @Override
    public int getPrecision() { return 0; }
    
    @Override
    public int getScale() { return 0; }
    
    @Override
    public String getCharset() { return null; }
    
    @Override
    public String getCollation() { return null; }
} 