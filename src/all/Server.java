package all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;

public class Server {

    int port;
    JLabel label;

    public Server(int port, JLabel label) {
        this.port = port;
        this.label = label;
    }

    void startListening() {
        new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (true) {
                        Socket connectionSocket = serverSocket.accept();
                        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        label.setText(inFromClient.readLine());
                    }
                } catch (IOException ex) {
                    System.out.println("Couldn't Listen to the client");
                }
            }
        }.start();

    }

}
