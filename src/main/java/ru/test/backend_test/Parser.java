package ru.test.backend_test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Parser {
    public static final String EMAIL_SEPARATOR = ", ";
    public static final String SEPARATOR = " -> ";

    public static User parseLine(String line) throws ParseException {
        var split = line.split(SEPARATOR);

        if (split.length == 2) {
            var username = split[0].trim();

            if (username.isEmpty()) {
                throw new ParseException("Username is missing");
            }

            var inputEmails = explodeEmails(split[1]);

            if (inputEmails.isEmpty()) {
                throw new ParseException("User has no email");
            }

            return new User(username, inputEmails);
        } else {
            throw new ParseException();
        }
    }

    private static LinkedList<String> explodeEmails(String emailsLine) {
        return Arrays.stream(emailsLine.split(EMAIL_SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
