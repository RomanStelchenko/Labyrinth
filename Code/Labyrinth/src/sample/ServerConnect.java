package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnect implements  Strategy{
    public void connect() throws IOException {
        Socket s = null;
        try {
            ServerSocket server = new ServerSocket(8030);
            s = server.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());
            Scanner in = new Scanner(System.in);
            for(int i=0;i<10;i++) {
                String num;
                num = in.nextLine();
                System.out.println(num);
                ps.println(num);
                ps.flush();
            }
            ps.close();
        } catch (IOException e) {
            System.err.println("error: " + e);
        } finally {
            if (s != null)
                s.close();
        }
    }
}
