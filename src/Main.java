import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String connect;
        BufferedReader bufferedReader ;

        //to check singleton server
        Server server = Server.getInstance("FOO");
        System.out.println(server.value);

        Server anotherServer = Server.getInstance("from another server");
        System.out.println(anotherServer.value);

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("Please enter conn ");
            connect = bufferedReader.readLine();
            if (connect.contains("conn")){
                System.out.println("Please enter server hostName and its port number: ");
                String serverHost = scan.next();
                int serverPort = scan.nextInt();
                System.out.println("conn "  + serverHost + " " + serverPort);
                server = new Server(serverHost , serverPort );

                System.out.println("Please enter client hostName and its port number: ");
                String clientHost = scan.next();
                int clientPort = scan.nextInt();
                System.out.println("conn " + clientHost + " " + clientPort);
                Client client = new Client(clientHost , clientPort);

                Thread s = new Thread(server);
                s.start();

                Thread c = new Thread(client);
                c.start();
                break;
            }

        }









    }
}

