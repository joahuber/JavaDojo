package com.bbv.javadojo.optional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArgumentParser {

	SchemaParser schemaParser = new SchemaParser();
	@SuppressWarnings("rawtypes")
	private Map<String, Supplier> arguments = new HashMap<>();
	private final Map<String, Function<String, Supplier<?>>> argumentParserDispatcher;

	public ArgumentParser(final String schema, final String[] args) {

		argumentParserDispatcher = schemaParser.parseSchema(schema);
		parseArguments(args);
	}

	private void parseArguments(final String[] args) {
		final String joinedArgs = String.join(" ", args);
		arguments = Arrays.stream(joinedArgs.split("-")).filter(isNonEmptyString()).map(this::splitTupels)
				.map(this::validateArgument).collect(Collectors.toMap(this::tagExtractor, this::argumentMapper));
	}

	private Predicate<? super String> isNonEmptyString() {
		return s -> s != null && !s.isEmpty();
	}

	private String tagExtractor(final String[] string) {
		return string[0];
	}

	private Supplier<?> argumentMapper(final String[] string) {
		return argumentParserDispatcher.get(string[0]).apply(string[string.length - 1]);
	}

	private String[] validateArgument(String[] argument) {
		if (isArgumentValid(argument[0])) {
			return argument;
		} else {
			throw new IllegalArgumentException("Unknown Argument received: " + argument[0]);
		}

	}

	private boolean isArgumentValid(final String string) {
		return argumentParserDispatcher.containsKey(string);
	}

	private String[] splitTupels(String tupel) {
		return tupel.split(" ");
	}

	public boolean getBoolean(final String arg) {
		return (boolean) Optional.ofNullable(arguments.get(arg)).orElse(() -> Boolean.FALSE).get();
	}

	public String getString(final String string) {
		return (String) Optional.ofNullable(arguments.get(string)).orElse(() -> "").get();
	}

	public int getInt(final String arg) {
		return (int) Optional.ofNullable(arguments.get(arg)).orElse(() -> 0).get();
	}

	public double getDouble(final String string) {
		return (double) Optional.ofNullable(arguments.get(string)).orElse(() -> 0.0).get();
	}

	public String[] getStringArray(final char arg) {
		return null;
	}

	public Map<String, String> getMap(final char arg) {
		return null;
	}

}
