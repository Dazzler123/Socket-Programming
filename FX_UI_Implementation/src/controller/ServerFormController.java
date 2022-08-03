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

    String message = "";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                txtTextArea.appendText("Server Started..");
                accept = serverSocket.accept();
                txtTextArea.appendText("\nClient Connected.");
                txtTextArea.appendText("\n============================================");

                dataOutputStream = new DataOutputStream(accept.getOutputStream());
                dataInputStream = new DataInputStream(accept.getInputStream());

                while(!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    txtTextArea.appendText("\nClient : " + message);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void btnSendMessage(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMessageHere.getText().trim());
        dataOutputStream.flush();
    }
}
