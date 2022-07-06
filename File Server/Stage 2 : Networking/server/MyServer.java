package server;

import java.io.*;
import java.net.*;

public class MyServer {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    public static void main(String[] args) throws IOException {
        System.out.println("Server started!");

        ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
        Socket socket = serverSocket.accept();

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String message = inputStream.readUTF(); // receive data from client
        System.out.println("Received: " + message);

//        String messageToSend = reader.readLine(); // receive data from
        String messageToSend = "All files were sent!";
        System.out.println("Sent: " + messageToSend);
        outputStream.writeUTF(messageToSend); // send data to client

        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
