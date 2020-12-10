package ru.test.backend_test;

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException() {
        super("Unparseable string");
    }
}
