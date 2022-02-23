import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private String hostName;
    private int portNumber;
    private Socket socket = null;
    BufferedReader bufferedReader = null;
    PrintStream printStream;
    Scanner scan = new Scanner(System.in);

    Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        connectServer();
        sentMsgToServer();
    }

    public void connectServer() {
        while (true) {
            try {
                socket = new Socket(hostName, portNumber);
                System.out.println("Connected Successfully....");
                System.out.println("Please enter username : ");
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                printStream = new PrintStream(socket.getOutputStream());
                break;
            } catch (IOException e) {
                System.out.println("Failed to connect, try again");
            }
        }
    }

    public void sentMsgToServer() {
        try {
            String user = scan.next();
            System.out.println("User <" + user + "> :  ");
            while (true) {
                String data = bufferedReader.readLine();
                printStream.println(data);
                if (data.contains("exit")) {
                    System.out.println("Please enter username");
                    String newUser = scan.next();
                    System.out.println("User <" + newUser + "> :  ");

                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to sent message");
            e.printStackTrace();
        }
    }
}