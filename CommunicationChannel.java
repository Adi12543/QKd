package com.example.networksimulation;

public class CommunicationChannel {
    public void transmitKey(Node sender, Node receiver, String key) {
        receiver.addSharedKey(key);
    }

    public void transmitMessage(Node sender, Node receiver, String message) {
        String encryptedMessage = encryptMessage(message, sender.getSharedKeys().get(0));
        System.out.println(sender.getName() + " sent: " + encryptedMessage + " to " + receiver.getName());
    }

    private String encryptMessage(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            encryptedMessage.append((char) (message.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encryptedMessage.toString();
    }
}
