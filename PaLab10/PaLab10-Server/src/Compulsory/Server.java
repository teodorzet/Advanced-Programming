package Compulsory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    static Server instance = null;
    static final int port = 5000;
    static ServerSocket serverSocket;

    static public Server getInstance() throws IOException {
        if (instance == null)
            instance = new Server();
        return instance;
    }
    static public void closeServer() throws IOException {
            serverSocket.close();
            instance = null;
    }

    private Server() throws IOException {
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("Waiting for a client.");
                Socket socket = serverSocket.accept();
                System.out.println("Found a client.");
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            serverSocket.close();
        }
    }
}
