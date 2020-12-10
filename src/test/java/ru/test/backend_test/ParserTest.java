package ru.test.backend_test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseLine() throws ParseException {
        var username = "user1";
        var emails = new ArrayList<String>();
        emails.add("xxx@ya.ru");
        emails.add("foo@gmail.com");
        emails.add("lol@mail.ru");

        //user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru
        var line = username + Parser.SEPARATOR + String.join(Parser.EMAIL_SEPARATOR, emails);
        var user = Parser.parseLine(line);

        assertEquals(user.getUsername(), username);
        assertIterableEquals(user.getEmails(), emails);
    }

    @Test
    void parseLineUnparseable() throws ParseException {
        var line = "user1 - xxx@ya.ru, foo@gmail.com, lol@mail.ru";
        assertThrows(ParseException.class, () -> Parser.parseLine(line));
    }

    @Test
    void parseLineUsernameMissing() throws ParseException {
        var line = " -> xxx@ya.ru, foo@gmail.com, lol@mail.ru";
        assertThrows(ParseException.class, () -> Parser.parseLine(line));
    }

    @Test
    void parseLineWithoutEmails() throws ParseException {
        var line = "user1 -> ";
        assertThrows(ParseException.class, () -> Parser.parseLine(line));
    }
}