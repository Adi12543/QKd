package com.example.networksimulation;


import java.util.ArrayList;
import java.util.List;

public class NetworkSimulation {
    private List<Node> nodes;
    private CommunicationChannel channel;

    public NetworkSimulation() {
        this.nodes = new ArrayList<>();
        this.channel = new CommunicationChannel();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void simulateQKD() {
        for (Node nodeA : nodes) {
            for (Node nodeB : nodeA.getConnectedNodes()) {
                QKDProtocol qkd = new QKDProtocol(nodeA, nodeB);
                qkd.executeProtocol();
            }
        }
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public void sendMessage(Node sender, Node receiver, String message) {
        channel.transmitMessage(sender, receiver, message);
    }

    public void evaluate() {
        long startTime = System.nanoTime();
        simulateQKD();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("QKD Duration: " + duration + " ms");
    }

    public static void main(String[] args) {
        NetworkSimulation simulation = new NetworkSimulation();

        Node node1 = new Node("Node1");
        Node node2 = new Node("Node2");
        Node node3 = new Node("Node3");

        node1.addConnectedNode(node2);
        node2.addConnectedNode(node1);
        node2.addConnectedNode(node3);
        node3.addConnectedNode(node2);

        simulation.addNode(node1);
        simulation.addNode(node2);
        simulation.addNode(node3);

        simulation.simulateQKD();
        simulation.sendMessage(node1, node2, "Hello Node2");
        simulation.sendMessage(node2, node3, "Hello Node3");

        simulation.evaluate();
    }
}
