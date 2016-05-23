package all;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket clientSocket;
    DataOutputStream outToServer;
    String ip;
    int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
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

    void write(String move) throws IOException {
        outToServer.writeBytes(move);
    }

    void closeConnection() {

        try {
            clientSocket.close();
        } catch (IOException ex) {

        }
    }

}
