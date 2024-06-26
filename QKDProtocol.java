package com.example.networksimulation;

import java.util.Random;

public class QKDProtocol {
    private Node nodeA;
    private Node nodeB;
    private Random random;

    public QKDProtocol(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.random = new Random();
    }

    public void executeProtocol() {
        String key = generateKey();
        nodeA.addSharedKey(key);
        nodeB.addSharedKey(key);
    }

    private String generateKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            key.append(random.nextInt(2));
        }
        return key.toString();
    }
}
