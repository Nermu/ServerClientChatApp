import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable {
    public static Socket socket;
    public static Server instanceServer;
    public static String serverIP;
    public static int serverPort;
    public String value;
    BufferedReader bufferedReader = null;
    BufferedReader input;
    PrintStream printStream;
    Scanner scan = new Scanner(System.in);

    public Server(String serverHost, int portNumber) {
        serverIP = serverHost;
        serverPort = portNumber;
    }

    private Server(String value) {
        this.value = value;
    }

    public static Server getInstance(String value) {
        if (instanceServer == null) {
           instanceServer = new Server(value);
        }
        return instanceServer;
    }

    @Override
    public void run() {
        establishServer();
        readMsgClient();
    }

    public void establishServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            socket = serverSocket.accept();
            System.out.println("Request Accepted...");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            input = new BufferedReader(new InputStreamReader(System.in));
            printStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Could not listen on port");
            e.printStackTrace();
        }
    }

    public void readMsgClient() {
        while (true) {
            try {
                String client = bufferedReader.readLine();
                String message = bufferedReader.readLine();

                System.out.println(" < " + client + " > : " + message );

                if (message.equals("exit")) {
                    continue;
                }

            } catch (IOException ex) {
                System.out.println("Couldn't read message");
                ex.printStackTrace();
            }
        }
    }
}