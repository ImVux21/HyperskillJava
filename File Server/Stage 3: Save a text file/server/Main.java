package server;

import action.Action;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int port = 23456;

    private static final String DATA_STORED_DIR = "D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\data\\";

    public static void main(String[] args) {
        boolean isStopped = true;

        try (
                ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(ADDRESS));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            while (isStopped) {
                String clientRequest = input.readUTF();
                Action action = parseActionFromRequest(clientRequest);

                String[] parts = clientRequest.split("\\s");
                String pathOfFile = null;
                if (parts.length > 1) {
                    pathOfFile = DATA_STORED_DIR + parts[1];
                }

                // create server response based on the client request
                String serverResponse = null;
                switch (action) {
                    case GET:
                        serverResponse = handleGetRequest(pathOfFile);
                        break;
                    case PUT:
                        serverResponse = handlePutRequest(pathOfFile, parts[2]);
                        break;
                    case DELETE:
                        serverResponse = handleDeleteRequest(pathOfFile);
                        break;
                    case EXIT:
                        // shut down the server
                        isStopped = false;
                        System.exit(0);

                }
                // send the server response back to client
                sendServerResponse(serverResponse, output);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Action parseActionFromRequest(String clientRequest) {
        String[] parts = clientRequest.split("\\s+");

        if (parts[0].equals("GET")) {
            return Action.GET;
        } else if (parts[0].equals("PUT")) {
            return Action.PUT;
        } else if (parts[0].equals("DELETE")) {
            return Action.DELETE;
        } else if (parts[0].equals("exit")) {
            return Action.EXIT;
        }
        return null;
    }

    private static String handleGetRequest(String path) {
        if (isFileExists(path)) {
            String content = getContentFromFile(path);
            return "200 " + content;
        }

        return "404";
    }

    private static String getContentFromFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String handlePutRequest(String pathOfFile, String content) {
            File file = new File(pathOfFile);

            try {
                if (file.createNewFile()) {
                    writeContentToFile(file, content);
                    return "200";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "403";
    }

    private static void writeContentToFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String handleDeleteRequest(String pathOfFile) {
        try {
            if (Files.deleteIfExists(Paths.get(pathOfFile))) {
                return "200";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "404";
    }

    private static void sendServerResponse(String serverResponse, DataOutputStream output) {
        try {
            output.writeUTF(serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFileExists(String path) {
        Path of = Paths.get(path);
        return Files.exists(of) && !Files.isDirectory(of);
    }
}
