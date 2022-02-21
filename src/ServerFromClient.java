import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerFromClient implements Runnable{

    public ServerFromClient() {

        }

    @Override
    public void run() {
        String data = "";
        PrintWriter output = null;
        BufferedReader input = null;
        BufferedReader bufferedReader = null;

        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Waiting for Request...");

            Socket socket = serverSocket.accept();
            System.out.println("Request Accepted...");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        }catch (IOException e) {
            System.out.println("Could not listen on port");
            e.printStackTrace();
        }

        while (true){
            try {
                String str = bufferedReader.readLine();
                if (str.equals("exit") == true){
                    System.out.println("Connection Lost.....");
                    System.exit(1);
                }
                System.out.println("Message from Client : " + str);
            } catch (IOException ex) {
                System.out.println("Read failed");
            }
            System.out.println("Data: "+ data);
            if (data.equals("exit") == true){
                    System.out.println("Connection Lost.....");
                    System.exit(1);
                }
                System.out.println("Message from Client : " + data);
            }
        } /*catch (IOException e) {
            System.out.println("Not found data for the socket.." + e);
        }*/

    }

