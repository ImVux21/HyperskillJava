package client;

import action.Action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Main2 {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public static void main(String[] args) {

        while (true) {

            try (
                    Socket socket = new Socket(ADDRESS, PORT);
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream())

            ) {

                Client c1 = new Client();
                Action action = c1.askUserAction();

                // create the client request
                String clientRequest;
                String serverResponse;
                if (action != null) {
                    switch (action) {
                        case GET -> {
                            clientRequest = c1.createGetRequest();
                            // send the request string
                            c1.sendClientRequest(clientRequest, output);
                            c1.recieveAndDisplayServerGetResponse(input);

                        }
                        case PUT -> {
                            clientRequest = c1.createSaveRequest();
                            c1.sendClientPutRequest(clientRequest, output);
                            serverResponse = c1.receiveServerResponse(input);
                            c1.parseAndDisplayServerResponse(serverResponse, action);
                        }
                        case DELETE -> {
                            clientRequest = c1.createDeleteRequest();
                            c1.sendClientRequest(clientRequest, output);
                            serverResponse = c1.receiveServerResponse(input);
                            c1.parseAndDisplayServerResponse(serverResponse, action);
                        }
                        case EXIT -> System.exit(1);
                    }

                    System.out.println();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}