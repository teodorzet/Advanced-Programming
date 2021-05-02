package Compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String serverAddress;
    private String command;
    private int port;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String readCommand() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String command = reader.readLine();
        return command;
    }

    public void startClient(){
        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {

            while(true) {
                command = readCommand();

                if (command.equals("exit")) {
                    System.out.println("Client closed.");
                    out.println(command);
                    break;
                }
                out.println(command);

                String response = in.readLine();
                System.out.println(response);
                if (response.equals("Server stopped.")){
                    System.out.println("Client was closed beacouse the server was closed.");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("No server listening... " + e);
        }
    }

}
