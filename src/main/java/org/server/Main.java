package org.server;

public class Main {
    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();
        System.out.println("Hello world!");
    }
}