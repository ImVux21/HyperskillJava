package client;

import java.net.*;
import java.io.*;

public class MyClient {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public static void main(String[] args) throws IOException {
        System.out.println("Client started!");

        Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String message = "Give me everything you have!";
//        String message = reader.readLine(); // enter request
        System.out.println("Sent: " + message);
        outputStream.writeUTF(message); // send data to server

        System.out.println("Received: " + inputStream.readUTF()); // print received data from server

        outputStream.close();
        socket.close();
    }
}
