package com.bbv.javadojo.optional;

import static org.junit.Assert.*;

import org.junit.Test;
public class ArgumentParserTest {
    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        final ArgumentParser args = new ArgumentParser("", new String[0]);
    }

    @Test
        public void parse_booleanSchemaBooleanPresent() {
        final ArgumentParser args = new ArgumentParser("x", new String[] { "-x" });
        assertEquals(true, args.getBoolean("x"));
    }

    @Test
    public void parse_stringSchemaStringPresent() {
        final ArgumentParser args = new ArgumentParser("x*", new String[] { "-x te" });
        assertEquals("te", args.getString("x"));
    }
}