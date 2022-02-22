import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private int portNumber;
    BufferedReader bufferedReader = null;

    Server(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        establishServer();
        readMsgClient();

    }

    public void establishServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            System.out.println("Request Accepted...");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Could not listen on port");
            e.printStackTrace();
        }
    }

    public void readMsgClient(){
        while (true) {
            try {
                String str = bufferedReader.readLine();
                System.out.println("Message from Client : " + str);
                if (str.equals("exit")) {
                    break;
                }
            } catch (IOException ex) {
                System.out.println("Couldn't read message");
                ex.printStackTrace();
            }

        }
    }



}
