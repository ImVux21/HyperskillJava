package client;

import action.Action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static action.Action.EXIT;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(ADDRESS, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())

        )  {
            Action action = askUserAction();

            // create the client request
            String clientRequest = null;
            switch (action) {
                case GET:
                    clientRequest = createGetRequest();
                    break;
                case PUT:
                    clientRequest = createCreateRequest();
                    break;
                case DELETE:
                    clientRequest = createDeleteRequest();
                    break;
                case EXIT:
                    clientRequest = createExitRequest();
                    break;
            }

            // send the request string
            sendClientRequest(clientRequest, output);

            // for the request GET, CREATE, DELETE, the server will send the response containing status code,
            if (action != EXIT) {
                // receive the server response

                String serverResponse = receiveServerResponse(input);

                // parse and display the response based on the user action
                parseAndDisplayServerResponse(serverResponse, action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Action askUserAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
        String userEnter = scanner.nextLine();

        switch (userEnter) {
            case "1": return Action.GET;
            case "2": return Action.PUT;
            case "3": return Action.DELETE;
            case "exit": return Action.EXIT;
        }

        return null;
    }

    private static String createGetRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        return String.format("%s %s", Action.GET, filename);
    }

    private static String createCreateRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        System.out.print("Enter file content: ");
        String fileContent = scanner.nextLine();

        return String.format("%s %s %s", Action.PUT, filename, fileContent);
    }

    private static String createDeleteRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        return String.format("%s %s", Action.DELETE, filename);
    }

    private static String createExitRequest() {
        return "exit";
    }

    private static void sendClientRequest(String clientRequest, DataOutputStream output) {
        try {
            output.writeUTF(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The request was sent.");
    }

    private static String receiveServerResponse(DataInputStream input) {
        String response = null;
        try {
            response = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static void parseAndDisplayServerResponse(String serverResponse, Action action) {
        String[] parts = serverResponse.split("\\s+");
        if (parts[0].equals("200")) {
            switch (action) {
                case GET:
                    String content = serverResponse.substring(3).trim();
                    System.out.printf("The content of the file is: %s\n", content);
                    break;

                case PUT:
                    System.out.println("The response says that the file was created!");
                    break;

                case DELETE:
                    System.out.println("The response says that the file was successfully deleted!");
                    break;

            }
        } else if (parts[0].equals("404")) {
            System.out.println("The response says that the file was not found!");
        } else if (parts[0].equals("403")) {
            System.out.println("The response says that creating the file was forbidden!");
        }
    }
}
