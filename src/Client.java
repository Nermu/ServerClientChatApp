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

    Socket newSocket = null;
    PrintStream newPrintStream = null;
    Scanner scan = new Scanner(System.in);
    String data = " ";
    String input = " ";

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

                if (input.contains("exit")){
                    newSocket = new Socket(hostName,portNumber);
                    System.out.println("Connected Successfully....");
                }
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

                System.out.print("User <" + user + "> : ");

                data = bufferedReader.readLine();
                printStream.println("\nUser <" + user + "> : " + data);



                    if (data.contains("exit")) {
                        System.out.println("Please enter new username");
                        printStream = new PrintStream(newSocket.getOutputStream());
                        String newUser = scan.next();
                        while (true){
                            System.out.print("User <" + newUser + "> :  ");
                            String newData = bufferedReader.readLine();
                            newPrintStream.print( "\n<" + newUser + "> : " + newData);

                        }


                    }else if (data.contains("bye")){
                        System.out.println("Connection ended by client");
                    }


            }
                //System.out.print("User <" + user + "> :  ");

        } catch (IOException e) {
            System.out.println("Failed to sent message");
            e.printStackTrace();
        }
    }
}