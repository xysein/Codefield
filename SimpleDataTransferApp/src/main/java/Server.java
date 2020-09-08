package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    private static List<String> socketList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);

                System.out.println("Server has been started");

                while (true) {
                    clientSocket = server.accept();
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("New member has entered.");

                    out.write("Enter user name: ");
                    out.flush();
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    new Server().run();
                }

            } finally {
                System.out.println("Server has been stopped");
                server.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String clientsMessage = "";
        try {
            do {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                try {
                    clientsMessage = in.readLine();
                    System.out.println(clientsMessage);
                } catch (SocketException e) {

                }

                out.write("UserName: " + clientsMessage + "\n");
                out.flush();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
