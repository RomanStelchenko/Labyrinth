package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
import java.io.IOException;
import java.net.Socket;

public class ClientConnect implements Strategy {
    Socket socket = null;

    public Socket connect() {
        try {
            socket = new Socket("GREEN-PC", 8030);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
