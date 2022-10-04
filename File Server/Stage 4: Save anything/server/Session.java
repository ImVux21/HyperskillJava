package server;

import action.Action;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class Session implements Runnable {
    // save new file, delete file -> update the map
    private static final String DATA_STORED_DIR = "D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\data\\";
    private static int id = 1;
    private static HashMap<Integer, String> mapFiles = new LinkedHashMap<>();
    private ExecutorService executor;

    private Socket socket;
    public Session(Socket socket, ExecutorService executor) {
        this.socket = socket;
        this.executor = executor;
    }

    public static void setMapFiles(HashMap<Integer, String> mapFiles) {
        Session.mapFiles = mapFiles;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            String clientRequest = input.readUTF();
            Action action = parseActionFromRequest(clientRequest);

            String[] parts = clientRequest.split("\\s+");
            // handle none file name from client
            String fileNameToSave = null;
            if (parts.length == 3) {
                fileNameToSave = parts[2];
            }

            // create server response based on the client request
            String serverResponse;
            switch (action) {
                case GET -> {
                    String fileName = getFileName(parts[1], parts[2]);
                    serverResponse = handleGetRequest(fileName);
                    sendServerGetResponse(String.join(" ", serverResponse), fileName, output);
                }
                case PUT -> {
                    serverResponse = handlePutRequest(parts[1], fileNameToSave, input);
                    sendServerResponse(String.join(" ", serverResponse), output);
                }

                case DELETE -> {
                    serverResponse = handleDeleteRequest(parts[1], parts[2]);
                    sendServerResponse(String.join(" ", serverResponse), output);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
        }

        return null;
    }

    private static String handleGetRequest(String fileName) {
        if (isFileExistsByName(fileName)) {
            return "200";
        }

        return "404";
    }

    private static boolean isFileExistsByName(String fileName) {
        return mapFiles.containsValue(fileName);
    }

    private static String getFileName(String optionBy, String idOrFileName) {
        int subId = 0;
        String fileName = null;
        if (optionBy.equals("BY_ID")) {
            subId = Integer.parseInt(idOrFileName);
        } else if (optionBy.equals("BY_NAME")) {
            fileName = idOrFileName;
        }

        if (mapFiles.containsKey(subId)) {
            fileName = mapFiles.get(subId);
        }

        return fileName;
    }

    private static String handlePutRequest(String fileName, String fileNameToSave, DataInputStream input) throws IOException {
        int length = input.readInt();
        byte[] bytes = new byte[length];
        input.readFully(bytes, 0, length);

        // generate random file name
        if (fileNameToSave == null) {
            fileNameToSave = generateFileName(fileName);
        }

        while (true) {
            if (mapFiles.containsKey(id)) {
                id++;
            } else {
                break;
            }
        }

        if (!isFileExists()) {
            saveFileToServer(bytes, fileNameToSave);
            return "200 " + id;
        }

        return "403";
    }

    private static void saveFileToServer(byte[] bytes, String fileNameToSave) throws IOException {
        String pathOfFile = DATA_STORED_DIR + fileNameToSave;
        mapFiles.put(id, fileNameToSave);
        loadDataToFileServer(fileNameToSave);
        Files.write(Paths.get(pathOfFile), bytes);
    }

    private static void loadDataToFileServer(String fileNameToSave) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\map.txt", true));
            String element = id + ": " + fileNameToSave + "\n";
            bw.append(element);
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateFileName(String fileName) {
        String[] parts = fileName.split("\\.");
        // random string
        UUID uuid = UUID.randomUUID();
        return uuid + "." + parts[1];
    }

    private static String handleDeleteRequest(String optionBy, String idOrFileName) {
        try {
            String fileName = getFileName(optionBy, idOrFileName);
            String pathOfFile = DATA_STORED_DIR + fileName;

            if (Files.deleteIfExists(Paths.get(pathOfFile))) {
                int id = getIdToDelete(idOrFileName, optionBy);
                mapFiles.remove(id);
                updateMapFile(id);
                return "200";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "404";
    }

    private static void updateMapFile(int id) throws IOException {
        copyMapFile();
        clearMapFile("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\map.txt");
        try (
            Scanner sc = new Scanner(Paths.get("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\temp.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\map.txt"))
        ) {

            while (sc.hasNext()) {
                String line = sc.nextLine();

                if (!line.isEmpty()) {
                    String[] parts = line.split(":");
                    if (parts[0].equals(String.valueOf(id))) {
                        line = "";
                    }
                    bw.write(line + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void copyMapFile() {
        clearMapFile("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\temp.txt");

        try (
            InputStream is = new FileInputStream("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\map.txt");
            OutputStream os = new FileOutputStream("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\temp.txt");
            ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void clearMapFile(String pathFile) {
        try (PrintWriter writer = new PrintWriter(pathFile)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getIdToDelete(String idOrFileName, String optionBy) {
        for (var entry : mapFiles.entrySet()) {
            if (optionBy.equals("BY_ID")) {
                if (Integer.parseInt(idOrFileName) == entry.getKey()) {
                    return entry.getKey();
                }

            } else if (optionBy.equals("BY_NAME")) {
                if (idOrFileName.equals(entry.getValue())) {
                    return entry.getKey();
                }

            }
        }

        return 0;
    }

    private void sendServerGetResponse(String serverResponse, String fileName, DataOutputStream output) throws IOException {
        output.writeUTF(serverResponse);

        if (serverResponse.equals("200")) {
            byte[] bytes = Files.readAllBytes(Paths.get(DATA_STORED_DIR + fileName));

            output.writeInt(bytes.length);
            output.write(bytes);
        }
    }

    private static void sendServerResponse(String serverResponse, DataOutputStream output) {
        try {
            output.writeUTF(serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFileExists() {
        return mapFiles.containsKey(id);
    }
}
