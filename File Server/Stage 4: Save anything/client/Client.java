package client;

import action.Action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
    private static final String[] optionsBy = new String[] {"BY_NAME", "BY_ID"};
    private static final String DATA_STORED_DIR = "D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\client\\data\\";
    
    public Action askUserAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action (1 - get a file, 2 - save a file, 3 - delete a file): ");
        String userEnter = scanner.nextLine();

        switch (userEnter) {
            case "1": return Action.GET;
            case "2": return Action.PUT;
            case "3": return Action.DELETE;
            case "exit": return Action.EXIT;
        }

        return null;
    }

    public String createGetRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to get the file by name or by id (1 - name, 2 - id): ");
        String userEnter = scanner.nextLine();

        String optionBy = null;
        if (userEnter.equals("1")) {
            System.out.print("Enter name: ");
            optionBy = optionsBy[0];
        } else if (userEnter.equals("2")) {
            System.out.print("Enter id: ");
            optionBy = optionsBy[1];
        }

        String idOrFileName = scanner.nextLine();

        return String.format("%s %s %s", Action.GET,optionBy, idOrFileName);
    }

    public String createSaveRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name of the file: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter name of the file to be saved on server: ");
        String fileNameToSave = scanner.nextLine();

        return String.format("%s %s %s", Action.PUT, fileName, fileNameToSave);
    }

    public String createDeleteRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to delete the file by name or by id (1 - name, 2 - id): ");
        String option = scanner.nextLine();

        String optionBy = null;
        if (option.equals("1")) {
            System.out.print("Enter name: ");
            optionBy = optionsBy[0];
        } else if (option.equals("2")) {
            System.out.print("Enter id: ");
            optionBy = optionsBy[1];
        }

        String idOrFileName = scanner.nextLine();

        return String.format("%s %s %s", Action.DELETE, optionBy, idOrFileName);
    }

    public String createExitRequest() {
        return "exit";
    }

    public void sendClientPutRequest(String clientRequest, DataOutputStream output) {
        try {
            String[] parts = clientRequest.split("\\s+");
            byte[] bytes = Files.readAllBytes(Paths.get(DATA_STORED_DIR + parts[1]));

            output.writeUTF(clientRequest);
            output.writeInt(bytes.length);
            output.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The request was sent.");
    }

    public void sendClientRequest(String clientRequest, DataOutputStream output) {
        try {
            output.writeUTF(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The request was sent.");
    }

    public String receiveServerResponse(DataInputStream input) {
        String response = null;
        try {
            response = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void parseAndDisplayServerErrorResponse(String serverResponse) {
        if (serverResponse.equals("404")) {
            System.out.println("The response says that this file is not found!");
        } else if (serverResponse.equals("403")) {
            System.out.println("The response says that creating the file was forbidden!");
        }
    }

    private void parseAndDisplayServerSuccessResponse(String serverResponse, Action action) {
        String[] parts = serverResponse.split("\\s+"); // only PUT response

        if (serverResponse.contains("200")) {
            if (action == Action.PUT) {
                System.out.println("Response says that file is saved! ID = " + parts[1]);
            } else if (action == Action.DELETE) {
                System.out.println("The response says that this file was deleted successfully!");
            }
        }
    }

    public void parseAndDisplayServerResponse(String serverResponse, Action action) {
        parseAndDisplayServerSuccessResponse(serverResponse, action);
        parseAndDisplayServerErrorResponse(serverResponse);
    }

    public void recieveAndDisplayServerGetResponse(DataInputStream input) {
        try {
            String response = input.readUTF();

            if (response.contains("200")) {
                int length = input.readInt();
                byte[] bytes = new byte[length];
                input.readFully(bytes, 0, length);

                System.out.print("The file was downloaded! ");

                Scanner sc = new Scanner(System.in);
                System.out.print("Specify a name for it: ");
                String fileName = sc.nextLine();
                saveFileToClient(bytes, fileName);
            }
            parseAndDisplayServerErrorResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveFileToClient(byte[] bytes, String fileNameToSave) throws IOException {
        String pathOfFile = DATA_STORED_DIR + fileNameToSave;
        Files.write(Paths.get(pathOfFile), bytes);
        System.out.println("File saved on the hard drive!");
    }
}
