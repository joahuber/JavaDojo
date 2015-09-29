package com.bbv.javadojo.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
public class ArgumentParser {
    Map<String, Optional<Boolean>> arguments = new HashMap();
    private final String[] schemaArray;

    public ArgumentParser(final String schema, final String[] args) {
        schemaArray = parseSchema(schema);
        parseArguments(args);
    }

    private void parseArguments(final String[] args) {
        for (final String string : args) {
            if (string.charAt(0) == '-') {
                if (arguments.containsKey(String.valueOf(string.charAt(1)))) {
                    arguments.put(String.valueOf(string.charAt(1)), Optional.of(Boolean.TRUE));
                }
            }
        }
    }

    private String[] parseSchema(final String schema) {
        final String[] schemaElements = schema.split(",");
        for (final String schemaElement : schemaElements) {
            if (schemaElement.length() == 1) {
                arguments.put(schemaElement, Optional.empty());
            }
        }
        return schemaElements;
    }

    public boolean getBoolean(final String arg) {
        return arguments.get(arg).orElseThrow(IllegalArgumentException::new);
    }

    public String getString(final char arg) {
        return "";
    }

    public int getInt(final char arg) {
        return 0;
    }

    public double getDouble(final char arg) {
        return 0.0;
    }

    public String[] getStringArray(final char arg) {
        return null;
    }

    public Map<String, String> getMap(final char arg) {
        return null;
    }

    public int nextArgument() {
        // TODO Auto-generated method stub
        return 0;
    }
}
