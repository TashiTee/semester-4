package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.print("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        PrintWriter printWriter = new PrintWriter(getSocket().getOutputStream(), true);
        BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter Text: ");
            String readerInput = bufferedReader.readLine();
            printWriter.println(name + ": " + readerInput);
        }
    }

    public static Socket getSocket() throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8000);
        return socket;
    }
}
