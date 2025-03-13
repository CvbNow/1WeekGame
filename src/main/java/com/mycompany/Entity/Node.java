package com.mycompany.Entity;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y;
    int g, h, f; // g = cost from start, h = heuristic, f = total cost (g + h)
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Integer.MAX_VALUE; // Start with a high cost
        this.h = 0;
        this.f = Integer.MAX_VALUE;
        this.parent = null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f); // PriorityQueue sorts by f-cost
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}