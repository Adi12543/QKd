package com.example.networksimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkSimulationGUI extends JFrame {
    private NetworkSimulation simulation;
    private JTextField nodeNameField;
    private JTextField connectNode1Field;
    private JTextField connectNode2Field;
    private JTextArea logArea;

    public NetworkSimulationGUI() {
        simulation = new NetworkSimulation();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Quantum Network Simulation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        nodeNameField = new JTextField();
        connectNode1Field = new JTextField();
        connectNode2Field = new JTextField();

        inputPanel.add(new JLabel("Node Name:"));
        inputPanel.add(nodeNameField);
        inputPanel.add(new JLabel("Connect Node1:"));
        inputPanel.add(connectNode1Field);
        inputPanel.add(new JLabel("Connect Node2:"));
        inputPanel.add(connectNode2Field);

        JButton addNodeButton = new JButton("Add Node");
        JButton connectNodesButton = new JButton("Connect Nodes");
        JButton runSimulationButton = new JButton("Run Simulation");

        addNodeButton.addActionListener(new AddNodeAction());
        connectNodesButton.addActionListener(new ConnectNodesAction());
        runSimulationButton.addActionListener(new RunSimulationAction());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addNodeButton);
        buttonPanel.add(connectNodesButton);
        buttonPanel.add(runSimulationButton);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private class AddNodeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nodeName = nodeNameField.getText();
            if (!nodeName.isEmpty()) {
                Node node = new Node(nodeName);
                simulation.addNode(node);
                logArea.append("Added node: " + nodeName + "\n");
            }
        }
    }

    private class ConnectNodesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String node1Name = connectNode1Field.getText();
            String node2Name = connectNode2Field.getText();
            Node node1 = findNodeByName(node1Name);
            Node node2 = findNodeByName(node2Name);
            if (node1 != null && node2 != null) {
                node1.addConnectedNode(node2);
                node2.addConnectedNode(node1);
                logArea.append("Connected nodes: " + node1Name + " and " + node2Name + "\n");
            }
        }

        private Node findNodeByName(String name) {
            for (Node node : simulation.getNodes()) {
                if (node.getName().equals(name)) {
                    return node;
                }
            }
            logArea.append("Node not found: " + name + "\n");
            return null;
        }
    }

    private class RunSimulationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            simulation.simulateQKD();
            logArea.append("QKD Simulation completed.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NetworkSimulationGUI gui = new NetworkSimulationGUI();
            gui.setVisible(true);
        });
    }
}
