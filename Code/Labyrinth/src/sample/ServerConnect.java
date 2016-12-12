package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnect implements  Strategy {
    Socket socket = null;
    ServerSocket server;
    public Socket connect() throws IOException {
        ServerSocket server = new ServerSocket(8030);
        socket = server.accept();
        return socket;

    }
}