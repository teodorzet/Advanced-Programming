package Compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket = null;

    public ClientThread (Socket socket) {
        this.socket = socket;
    }

    public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String request = null;

                while(true) {
                    request = in.readLine();

                    String requestAnswer = null;
                    if (!request.equals("stop")) {
                        requestAnswer = "Server received the request.";
                    } else {
                        requestAnswer = "Server stopped.";
                        out.println(requestAnswer);
                        out.flush();
                        Server.closeServer();
                        break;
                    }
                    System.out.println("Recieved from client: " + request);
                    if (request.equals("exit")) {
                        System.out.println("Client closed.");
                        break;
                    }
                    out.println(requestAnswer);
                    out.flush();
                }

            } catch (IOException e) {
                System.err.println("Communication error. " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) { System.err.println (e); }
            }

    }
}
