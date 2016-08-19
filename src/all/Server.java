package all;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;

public class Server {

    int port;
    JLabel label;
    GUI gui;

    public Server(int port, JLabel label, GUI gui) {
        this.port = port;
        this.label = label;
        this.gui = gui;
    }

    public void startListening() {
        new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (true) {
                        Socket connectionSocket = serverSocket.accept();
                        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        int sensorReading = Integer.parseInt(inFromClient.readLine()) / 224;
                        label.setText(Integer.toString(sensorReading));
                        gui.drawVehicle(sensorReading);
                    }
                } catch (IOException ex) {
                    System.out.println("Couldn't Listen to the client");
                }
            }
        }.start();

    }

}
