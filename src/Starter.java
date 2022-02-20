import java.util.Scanner;

public class Starter {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        if (scan.next().contains("server")){
            ServerFromClient server = new ServerFromClient();
        }else {
            ClientToServer client = new ClientToServer();
        }


    }
}
