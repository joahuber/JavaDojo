package com.bbv.javadojo.optional;

import java.util.Map;
public class ArgumentParser {
    public ArgumentParser(final String schema, final String[] args) {
    }

    public boolean getBoolean(final char arg) {
        return false;
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
