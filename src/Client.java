import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client implements Runnable {
    private String hostName;
    private int portNumber;
    private Socket socket = null;
    BufferedReader bufferedReader = null;
    PrintStream printStream;

    Client(int portNumber, String hostName) {
        this.portNumber = portNumber;
        this.hostName = hostName;
    }

    @Override
    public void run() {

        connectServer();
        sentMsgToServer();

    }

    public void connectServer(){
        while (true) {
            try {
                socket = new Socket(hostName, portNumber);
                System.out.println("Connected Successfully....");

                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                printStream = new PrintStream(socket.getOutputStream());
                break;
            } catch (IOException e) {
                System.out.println("Failed to connect, try again");
            }
        }
    }

    public void sentMsgToServer(){
        try {
            while (true) {
                System.out.print("Client : ");
                String data = bufferedReader.readLine();
                printStream.println(data);

                if (data.contains("exit")) {
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to sent message");
            e.printStackTrace();
        }
    }
}