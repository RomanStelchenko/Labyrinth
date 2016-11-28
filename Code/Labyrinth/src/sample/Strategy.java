package sample;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by GREEN on 13.11.2016.
 */
public interface Strategy {
    public Socket connect() throws IOException;
}
