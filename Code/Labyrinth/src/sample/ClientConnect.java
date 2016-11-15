package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
import java.io.*;
import java.net.*;

public class ClientConnect implements Strategy{
    public void connect() {
        Socket socket = null;
        try {
            socket = new Socket("GREEN-PC", 8030);
            while(true) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                String msg = br.readLine();
                if(msg!=null)
                    System.out.println(msg);
            }
        } catch (IOException e) {
            System.err.println("error: " + e);
        }
    }
}
