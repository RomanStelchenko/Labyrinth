package sample;

/**
 * Created by GREEN on 14.11.2016.
 */
public class TcpIpCommunicationSingleton {
    private static TcpIpCommunicationSingleton ourInstance = new TcpIpCommunicationSingleton();

    public static TcpIpCommunicationSingleton getInstance() {
        return ourInstance;
    }

    private TcpIpCommunicationSingleton() {
    }
}
