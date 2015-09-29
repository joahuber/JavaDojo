package com.bbv.javadojo.optional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SchemaParser {
	private final Map<String, Function<String, Supplier<?>>> schemaParserDispatcher = new HashMap<>();

	public SchemaParser() {
		initParserDispatcher();
	}

	private void initParserDispatcher() {
		schemaParserDispatcher.put("", this::consumeBooleanArgument);
		schemaParserDispatcher.put("*", this::consumeStringArgument);
		schemaParserDispatcher.put("#", this::consumeIntArgument);
		schemaParserDispatcher.put("##", this::consumeDoubleArgument);
	}

	Map<String, Function<String, Supplier<?>>> parseSchema(final String schema) {
		return Arrays.stream(schema.split(",")).map(s -> s.trim()).filter(s -> s != null && !s.isEmpty())
				.collect(Collectors.toMap(this::extractTag, this::identifyParser));

	}

	private Function<String, Supplier<?>> identifyParser(String schemaElement) {
		return Optional.ofNullable(schemaParserDispatcher.get(extractTailCharacter(schemaElement)))
				.orElseThrow(() -> new IllegalArgumentException(
						"Invalid character in schema definition: " + extractTailCharacter(schemaElement)));
	}

	private String extractTailCharacter(String schemaElement) {
		return schemaElement.substring(1);
	}

	private String extractTag(String schemaElement) {
		return schemaElement.substring(0, 1);
	}

	private Supplier<Boolean> consumeBooleanArgument(String argument) {
		return () -> {
			return Boolean.TRUE;
		};
	}

	private Supplier<Integer> consumeIntArgument(String argument) {
		return () -> {
			return Integer.parseInt(argument);
		};
	}

	private Supplier<String> consumeStringArgument(String argument) {
		return () -> {
			return argument;
		};
	}

	private Supplier<Double> consumeDoubleArgument(String argument) {
		return () -> {
			return Double.parseDouble(argument);
		};
	}

}
