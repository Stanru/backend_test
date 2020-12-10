package ru.test.backend_test;

import java.util.*;

public class Graph<T> {
    private final Set<T> visited = new HashSet<>();
    private final Map<T, List<T>> vertices = new HashMap<>();

    public void addVertex(T vertex) {
        vertices.putIfAbsent(vertex, new LinkedList<>());
    }

    public Map<T, List<T>> getVertices() {
        return vertices;
    }

    public void addEdge(T a, T b) {
        vertices.computeIfAbsent(a, list -> new LinkedList<>()).add(b);
        vertices.computeIfAbsent(b, list -> new LinkedList<>()).add(a);
    }

    public List<T> search(T vertex) {
        var list = new LinkedList<T>();
        search(vertex, list);
        return list;
    }

    private void search(T vertex, List<T> list) {
        if (visited.contains(vertex)) {
            return;
        } else {
            visited.add(vertex);
            list.add(vertex);
        }

        for (var v : vertices.get(vertex)) {
            search(v, list);
        }
    }
}