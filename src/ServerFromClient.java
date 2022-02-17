import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerFromClient {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int port = scan.nextInt();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Request...");

            Socket socket = serverSocket.accept();
            System.out.println("Request Accepted...");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String str = bufferedReader.readLine();
                if (str.equals("exit") == true){
                    System.out.println("Connection Lost.....");
                    System.exit(1);
                }
                System.out.println("Message from Client : " + str);
            }
        } catch (IOException e) {
            System.out.println("Not found data for the socket.." + e);
        }

    }
}
