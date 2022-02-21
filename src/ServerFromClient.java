import java.io.*;
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
        BufferedReader bufferedReader = null;

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Waiting for Request...");

            Socket socket = serverSocket.accept();
            System.out.println("Request Accepted...");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            while (true){
                try {
                    String str = bufferedReader.readLine();
                    System.out.println("Message from Client : " + str);
                    if (str.equals("exit") == true){
                        System.out.println("Connection Lost.....");
                        System.exit(1);
                    }
                    System.out.print("Server : ");
                } catch (IOException ex) {
                    System.out.println("Read failed");
                }
                if (data.equals("exit") == true)
                {
                    System.out.println("Connection Lost.....");
                    System.exit(1);
                }
            }

        }catch (IOException e) {
            System.out.println("Could not listen on port");
            e.printStackTrace();
        }


        }

    }

