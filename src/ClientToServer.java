import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientToServer {
    ClientToServer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Sending a Request....");
        try {
            int port = scan.nextInt();
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected Successfully....");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            while (true){
                System.out.println("Input the Data....");
                String str = bufferedReader.readLine();
                printStream.println(str);

                if (str.contains("exit")){
                    System.exit(1);
                }
                System.out.println("Data Returned that sent to server");
                System.out.println(str);
            }
        }catch (UnknownHostException ue){
            System.out.println("Something Wrong with IP...!!!" + ue);
        }
        catch (IOException e) {
            System.out.println("Not found data for the socket..." + e);
        }
    }

}
