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
    PrintStream printStream = null;

    String newData = " ";
    Scanner scan = new Scanner(System.in);
    String data = " ";


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
                break;

            } catch (IOException e) {
                System.out.println("Failed to connect, try again");
            }
        }
    }

    public void sentMsgToServer() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printStream = new PrintStream(socket.getOutputStream());

            String user = scan.next();
            while (true) {

                System.out.print("User <[" + user + "]> : ");
                data = bufferedReader.readLine();
                printStream.println("\nMessage<[" + data + "]>");
                printStream.println("[" + user + "] : [" + data +"]");

                if (data.contains("exit")) {
                    System.out.println("\nUser <[" + user + "]> : has exit from chat");
                    System.out.println("Please enter new username");
                    String newUser = scan.next();
                    while (true) {
                        System.out.print("User <[" + newUser + "]> :  ");
                        data = bufferedReader.readLine();
                        printStream.println("Message<[ " + data + "]>");
                        printStream.println("[" + newUser + "] : [" + data + "]");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to sent message");
            e.printStackTrace();
        }
    }
}