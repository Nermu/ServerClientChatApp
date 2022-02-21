import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientToServer implements Runnable{
    private String hostName = "localhost";
    private int portNumber;
    private Socket socket = null;

    ClientToServer(Socket client){
        this.socket = client;
        System.out.println("Sending a Request....");
    }
    ClientToServer(int portNumber, String hostName){
        this.portNumber = portNumber;
        this.hostName = hostName;
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sending a Request....");
        try {
            int port = scan.nextInt();
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected Successfully....");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            while (true){
                System.out.print("Client : ");
                String data = bufferedReader.readLine();
                printStream.println(data);

                String msg = bufferedReader.readLine();
                System.out.println("Message from server : " + msg);

                if (data.contains("exit")){
                    System.exit(1);
                }

                }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
