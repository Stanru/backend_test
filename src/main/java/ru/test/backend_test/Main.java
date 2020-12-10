package ru.test.backend_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static ru.test.backend_test.Parser.EMAIL_SEPARATOR;
import static ru.test.backend_test.Parser.SEPARATOR;

public class Main {
    public static void main(String[] args) throws ParseException {
        try (var asd = new BufferedReader(new InputStreamReader(System.in))) {
            var graph = new Graph<String>();
            var users = new LinkedList<User>();

            for (var line = asd.readLine(); line != null && !line.isEmpty(); line = asd.readLine()) {
                users.add(Parser.parseLine(line));

                String preMail = null;

                for (var email : users.getLast().getEmails()) {
                    if (preMail != null) {
                        graph.addEdge(preMail, email);
                    }
                    preMail = email;
                }
            }

            for (var user : users) {
                var firstEmail = user.getEmails().getFirst();
                var result = graph.search(firstEmail).stream().reduce((a, b) -> a + EMAIL_SEPARATOR + b);
                result.ifPresent(s -> System.out.println(user.getUsername() + SEPARATOR + s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
