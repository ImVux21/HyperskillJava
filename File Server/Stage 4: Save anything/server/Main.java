package server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final String DATA_SAVED = "D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\map.txt";
    private static final int PORT = 23456;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            ExecutorService executor = Executors.newFixedThreadPool(10);

            LinkedHashMap<Integer, String> mapFiles = parseMapFiles();

            while (true) {
                Socket socket = server.accept();
                Session session = new Session(socket, executor);

                Session.setMapFiles(mapFiles);

                executor.submit(session);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static LinkedHashMap<Integer, String> parseMapFiles() throws IOException {
        Scanner sc = new Scanner(Paths.get(DATA_SAVED));
        LinkedHashMap<Integer, String> mapFiles = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String element = sc.nextLine();
            if (!element.isEmpty()) {
                String[] parts = element.split(":");
                mapFiles.put(Integer.parseInt(parts[0]), parts[1].trim());
            }
        }

        return mapFiles;
    }

}
