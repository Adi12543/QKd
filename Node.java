package com.example.networksimulation;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<String> sharedKeys;
    private List<Node> connectedNodes;

    public Node(String name) {
        this.name = name;
        this.sharedKeys = new ArrayList<>();
        this.connectedNodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addConnectedNode(Node node) {
        connectedNodes.add(node);
    }

    public List<Node> getConnectedNodes() {
        return connectedNodes;
    }

    public void addSharedKey(String key) {
        sharedKeys.add(key);
    }

    public List<String> getSharedKeys() {
        return sharedKeys;
    }
}

