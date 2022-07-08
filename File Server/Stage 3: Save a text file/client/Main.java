package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String[] code = {"200", "403", "404"};

    public static void main(String[] args) throws IOException {
        boolean stopProgram = true;
        Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
        String promt = reader.readLine();

        if (promt.equals("exit")) {
            stopProgram = false;
            socket.close();
        }

        if (stopProgram) {
            System.out.print("Enter filename: ");
            String nameFile = reader.readLine();

            String fileContent = null;
            if (promt.equals("2")) {
                System.out.print("Enter file content: ");
                fileContent = reader.readLine();
            }

            String request = null;
            if (promt.equals("2")) {
                request = PUT + nameFile + fileContent;
            } else if (promt.equals("1")) {
                request = GET + nameFile;
            } else if (promt.equals("3")) {
                request = DELETE + nameFile;
            }

            outputStream.writeUTF(request); // send data to server
            System.out.println("The request was sent.");

            String receivedCode = inputStream.readUTF();

            if (receivedCode.equals(code[0])) {
                if (promt.equals("2")) {
                    System.out.println("The response says that file was created!");
                } else if (promt.equals("1")) {
                    File f = new File("D:\\FIT_2022\\DSA_2022\\File Server\\File Server\\task\\src\\server\\data\\folder\\" + nameFile);
                    Scanner scanner = new Scanner(f);
                    System.out.println("The content of the file is: " + scanner.nextLine());
                } else if (promt.equals("3")) {
                    System.out.println("The response says that the file was successfully deleted!");
                }
            }

            if (receivedCode.equals(code[1])) {
                System.out.println("The response says that creating the file was forbidden!");
            }

            if (receivedCode.equals(code[2])) {
                System.out.println("The response says that the file was not found!");
            }

            outputStream.close();
            socket.close();
        }
    }
}
