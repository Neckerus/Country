package com.example.springboot.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Graph {

    private final Map<Country, List<Country>> borders;

    public Graph(Map<Country, List<Country>> borders) {
        this.borders = borders;
    }

    public Map<Country, List<Country>> getBorders() {
        return borders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(borders, graph.borders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borders);
    }
}
