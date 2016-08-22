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
                        String sensorReading = inFromClient.readLine();

                        String[] temp = sensorReading.split(",");
                        double gap1 = (Double.parseDouble(temp[0]) - 1550) / 58;
                        double gap2 = (Double.parseDouble(temp[1]) - 1550) / 58;

                        gui.gap1 = gap1;
                        gui.gap2 = gap2;

                        label.setText(Integer.toString((int) gap1) + " | " + Integer.toString((int) gap2));
                        gui.drawVehicle(gap1, gap2);

                    }
                } catch (IOException ex) {
                    System.out.println("Couldn't Listen to the client");
                }
            }
        }.start();
    }
}
