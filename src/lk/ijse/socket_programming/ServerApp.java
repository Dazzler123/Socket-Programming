package lk.ijse.socket_programming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        final int PORT = 8000;
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Server is running in port : " + PORT);

        Socket localSocket = serverSocket.accept();
        System.out.println("Client Accepted");

        DataOutputStream dataOutputStream = new DataOutputStream(localSocket.getOutputStream());

        DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String message = "", reply = "";


    }
}
