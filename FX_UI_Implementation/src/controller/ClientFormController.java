package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFormController {
    public TextArea txtTextArea;
    public TextField txtMessageHere;

    final int PORT = 5000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    txtTextArea.appendText("\nServer : " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        ;
    }

    public void btnSendMessage(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMessageHere.getText().trim());
        dataOutputStream.flush();
    }
}
