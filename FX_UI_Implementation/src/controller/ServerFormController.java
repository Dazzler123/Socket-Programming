package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea txtTextArea;
    public TextField txtMessageHere;

    final int PORT = 5000;
    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(PORT);
                    System.out.println("Server Started");
                    accept = serverSocket.accept();
                    System.out.println("Client Connected");

                    dataOutputStream = new DataOutputStream(accept.getOutputStream());
                    dataInputStream = new DataInputStream(accept.getInputStream());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void btnSendMessage(ActionEvent actionEvent) {
    }
}
