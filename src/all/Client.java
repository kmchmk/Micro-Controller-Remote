package all;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket clientSocket;
    DataOutputStream outToServer;
    String ip;
    int port;
    GUI gui;

    public Client(String ip, int port, GUI gui) {
        this.ip = ip;
        this.port = port;
        this.gui = gui;
    }

    boolean connectToServer() {
        try {
            clientSocket = new Socket(ip, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            return true;
        } catch (IOException ex) {
            System.out.println("Couldn't connect to server");
            try {
                clientSocket.close();
            } catch (Exception e) {
            }

            return false;
        }
    }

    void write(String move) {
        try {
            outToServer.writeBytes(move);
        } catch (Exception ex) {
            System.out.println("You should first connetct to the vehicle.");
            gui.connectToClientAndListenToServer();
        }
    }

    void closeConnection() {
        try {
            clientSocket.close();
        } catch (IOException ex) {
        }
    }

}
