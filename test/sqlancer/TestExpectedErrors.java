package sqlancer;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sqlancer.common.query.ExpectedErrors;

public class TestExpectedErrors {

    @Test
    public void testEmpty() {
        ExpectedErrors errors = new ExpectedErrors();
        assertFalse(errors.errorIsExpected("a"));
    }

    @Test
    public void testStringSimple() {
        ExpectedErrors errors = new ExpectedErrors();
        errors.add("a");
        errors.add("b");
        errors.add("c");
        assertTrue(errors.errorIsExpected("a"));
        assertTrue(errors.errorIsExpected("b"));
        assertTrue(errors.errorIsExpected("c"));
        assertTrue(errors.errorIsExpected("aa"));
        assertFalse(errors.errorIsExpected("d"));

    }

    @Test
    public void testRegexSimple() {
        ExpectedErrors errors = new ExpectedErrors();
        errors.addRegex(Pattern.compile("a\\d"));
        errors.addRegex(Pattern.compile("b\\D"));
        errors.add("c");
        assertTrue(errors.errorIsExpected("a0"));
        assertTrue(errors.errorIsExpected("bb"));
        assertTrue(errors.errorIsExpected("c"));
        assertFalse(errors.errorIsExpected("aa"));

    }

    @Test
    public void testStringRealistic() {
        ExpectedErrors errors = new ExpectedErrors();
        errors.add("violated");
        assertTrue(errors.errorIsExpected("UNIQUE constraint was violated!"));
        assertTrue(errors.errorIsExpected("PRIMARY KEY constraint was violated!"));
    }

    @Test
    public void testRegexRealistic() {
        ExpectedErrors errors = new ExpectedErrors();
        errors.addRegex(Pattern.compile(".violated."));
        assertTrue(errors.errorIsExpected("UNIQUE constraint was violated!"));
        assertTrue(errors.errorIsExpected("PRIMARY KEY constraint was violated!"));

        errors.addRegex(Pattern.compile("unknown signature: (min|max)\\(.*\\)"));
        assertTrue(errors.errorIsExpected("unknown signature: max(unknown[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(unknown[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(interval[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(interval[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(string[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(string[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(decimal[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(decimal[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(varbit[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(varbit[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(int[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(int[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(bool[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(bool[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(timestamp[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(timestamp[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(timestamptz[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(timestamptz[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(timetz[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(timetz[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(time[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(time[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(int2[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(int2[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(int4[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(int4[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(bytes[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(bytes[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(bit[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(bit[])"));
        assertTrue(errors.errorIsExpected("unknown signature: min(float[])"));
        assertTrue(errors.errorIsExpected("unknown signature: max(float[])"));
    }

}
