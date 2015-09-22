package com.bbv.javadojo.optional;

import org.junit.Test;
public class ArgumentParserTest {
    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        final ArgumentParser args = new ArgumentParser("", new String[0]);
    }
}