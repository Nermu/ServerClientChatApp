import java.io.IOException;
import java.util.Scanner;

public class Starter {
    public static void main(String[] args) throws IOException {

        ServerFromClient server = new ServerFromClient();
        ClientToServer client = new ClientToServer(8080, "localhost");

        Thread s = new Thread(server);
        s.start();

        Thread c = new Thread(client);
        c.start();

        }
    }

