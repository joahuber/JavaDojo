package com.bbv.javadojo.optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgumentParserTest {

	@Test
	public void testCreateWithNoSchemaOrArguments() throws Exception {
		final ArgumentParser argumentParser = new ArgumentParser("", new String[0]);
		assertNotNull(argumentParser);
	}

	@Test
	public void parse_booleanSchemaBooleanPresent() {
		final ArgumentParser args = new ArgumentParser("x", new String[] { "-x" });
		assertTrue(args.getBoolean("x"));
	}

	@Test
	public void parse_booleanSchemaBooleanMissing() {
		final ArgumentParser args = new ArgumentParser("x", new String[0]);
		assertFalse(args.getBoolean("x"));
	}

	@Test
	public void parse_stringSchemaStringPresent() {
		final ArgumentParser args = new ArgumentParser("x*", new String[] { "-x te" });
		assertEquals("te", args.getString("x"));
	}

	@Test
	public void parse_intSchemaIntegerPresent() {
		final ArgumentParser args = new ArgumentParser("x#", new String[] { "-x 88" });
		assertEquals(88, args.getInt("x"));
	}

	@Test
	public void parse_doubleSchema_DoublePresent() {
		final ArgumentParser args = new ArgumentParser("d##", new String[] { "-d 88.5" });
		assertThat(args.getDouble("d"), equalTo(88.5));
	}

	@Test
	public void parse_complexSchema_DoubleIntStringPresent() {
		final ArgumentParser args = new ArgumentParser("s*, x#, d##", new String[] { "-s test -x 42 -d 88.5" });
		assertThat(args.getDouble("d"), equalTo(88.5));
		assertThat(args.getString("s"), equalTo("test"));
		assertThat(args.getInt("x"), equalTo(42));
	}

	@Test(expected = IllegalArgumentException.class)
	public void parse_invalidTag() {
		new ArgumentParser("x#", new String[] { "-d 88" });
	}
}