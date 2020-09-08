package main.java;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader consoleReader;
    private static BufferedReader socketReader;
    private static BufferedWriter socketWriter;

    public static void main(String[] args) {
        try {
            String clientMessage;
            try {
                clientSocket = new Socket("localhost", 4004);
                System.out.println("Connected");
                do {

                    socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



                    consoleReader = new BufferedReader(new InputStreamReader(System.in));
                    socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    clientMessage = consoleReader.readLine();

                    socketWriter.write(clientMessage + "\n");
                    socketWriter.flush();

                    System.out.println(socketReader.readLine());
                } while (!clientMessage.equals("exit"));
            } finally {
                System.out.println("Client was closed");
                clientSocket.close();
                socketWriter.close();
                socketWriter.close();
            }

        } catch (IOException e) {
          e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Server Not Found");
        }
    }
}
