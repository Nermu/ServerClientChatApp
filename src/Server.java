import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    public static Socket socket;
    public static Server instanceServer;
    public static int serverPort;
    public static String serverIP;
    public String value;
    BufferedReader bufferedReader = null;

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

        } catch (IOException e) {
            System.out.println("Could not listen on port");
            e.printStackTrace();
        }
    }

    public void readMsgClient() {
        while (true) {
            try {
                String str = bufferedReader.readLine();
                System.out.println("Message <" + str + ">");
                if (str.equals("exit")) {
                    establishServer();
                    continue;
                }
            } catch (IOException ex) {
                System.out.println("Couldn't read message");
                ex.printStackTrace();
            }

        }
    }


}