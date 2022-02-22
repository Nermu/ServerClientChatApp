import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("enter server port number: ");
        int serverPort = scan.nextInt();
        Server server = new Server(serverPort);

        System.out.println("enter client port number: ");
        int clientPort = scan.nextInt();
        Client client = new Client(clientPort, "localhost");

        Thread s = new Thread(server);
        s.start();

        Thread c = new Thread(client);
        c.start();


    }
}

