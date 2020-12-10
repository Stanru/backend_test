package ru.test.backend_test;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;

import static ru.test.backend_test.Parser.EMAIL_SEPARATOR;
import static ru.test.backend_test.Parser.SEPARATOR;

@RequiredArgsConstructor
@Getter
public class User {
    private final String username;
    private final LinkedList<String> emails;

    @Override
    public String toString() {
        return username + SEPARATOR + String.join(EMAIL_SEPARATOR, emails);
    }
}
