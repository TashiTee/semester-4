package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    public static final int PORT = 8000;

    public static void main(String[] args) throws IOException {

        new Server().runServer();

    }
    public void runServer() throws IOException {
        Date date = new Date();
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("MultiThreadServer started at " + date);
        while (true) {
            Socket socket = serverSocket.accept();
            new ServerThread(socket).start();
        }
    }

    public class ServerThread  extends Thread {
        Socket socket;
        ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                String message = null;
                System.out.println("Connection from " + socket + " at " + new Date());
                BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (socket.getInputStream()));
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                }
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
