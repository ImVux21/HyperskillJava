package server;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static String[] code = {"200", "403", "404"};
    private static final int PORT = 23456;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
        Socket socket = serverSocket.accept();

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        String message = inputStream.readUTF(); // receive data from client
        String[] l = message.split("\\s+");

        String fileName = "D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\data\\folder\\" + l[1];
        Path path = Path.of(fileName);

        switch (l[0]) {
            case "GET":
                if (Files.exists(path)) {
                    outputStream.writeUTF(code[0]);
                } else {
                    outputStream.writeUTF(code[2]);
                }
                break;

            case "DELETE":
                if (Files.deleteIfExists(path)) {
                    outputStream.writeUTF(code[0]);
                } else {
                    outputStream.writeUTF(code[2]);
                }
                break;

            case "PUT":
                if (!Files.exists(path)) {
                    File file = new File(String.valueOf(path));
                    FileWriter writer = new FileWriter(file);
                    writer.write(l[2]);
                    if (file.createNewFile()) {
                        outputStream.writeUTF(code[0]);
                    }
                } else {
                    outputStream.writeUTF(code[1]);
                }
                break;
        }


//        inputStream.close();
//        socket.close();
//        serverSocket.close();
    }
}
