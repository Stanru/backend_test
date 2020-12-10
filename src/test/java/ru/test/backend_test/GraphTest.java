package ru.test.backend_test;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
    Graph<String> graph = new Graph<>();

    @Test
    void addVertex() {
        var vertex = "Test@mail.com";
        graph.addVertex(vertex);
        assertTrue(graph.getVertices().containsKey(vertex));
    }

    @Test
    void addEdge() {
        var vertex1 = "some@g.ru";
        var vertex2 = "qqq@g.ru";
        graph.addEdge(vertex1, vertex2);

        assertTrue(graph.getVertices().get(vertex1).contains(vertex2));
        assertTrue(graph.getVertices().get(vertex2).contains(vertex1));
    }

    @Test
    void search() {
        var root = "xxx@ya.ru";
        var emails = new HashSet<String>();
        emails.add(root);
        emails.add("foo@gmail.com");
        emails.add("lol@mail.ru");
        emails.add("ups@pisem.net");

        String preMail = null;

        for (var email : emails) {
            if (preMail != null) {
                graph.addEdge(preMail, email);
            }
            preMail = email;
        }

        assertTrue(emails.containsAll(graph.search(root)));
    }
}