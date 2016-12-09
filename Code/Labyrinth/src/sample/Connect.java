package sample;

import java.io.*;
import java.net.Socket;

/**
 * Created by GREEN on 13.11.2016.
 */
public class Connect {
    private WriteThread writeThread;
    private ReadThread readThread;
    private ConnectThread connectThread;
    private String readLine;
    private String writeLine;
    private Socket socket = null;
    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private BufferedReader keyboard;

    private Strategy strategy;
    private boolean readReady;
    Connect() {
        readReady = false;
    }
    Connect(Strategy strategy) {
        readReady = false;
        this.strategy = strategy;
    }
    public void setAlgorithm(Strategy strategy) {
        this.strategy = strategy;
    }
    public void connect() throws IOException {
        connectThread = new ConnectThread();
        connectThread.start();

    }
    public boolean getReadReady(){
        return readReady;
    }
    public void clearReadReady(){
        readReady = false;
    }
    public String getReadLine() {
        return readLine;
    }
    public void setWriteLine(String lineValue) {
        writeLine = new String(lineValue);
    }
    class WriteThread extends Thread {
        public void run()    //Этот метод будет выполнен в побочном потоке
        {
            try {
                while (true) {
                    while (writeLine == null) {
                        Thread.sleep(500);
                    }
                    out.writeUTF(writeLine); // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    writeLine = null;
                    Thread.sleep(500);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    class ReadThread extends Thread {
        public void run() {
            try {
                while (true) {
                    readLine = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    readReady = true;
                    Thread.sleep(500);
                    while (readReady == true) {
                        Thread.sleep(500);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class ConnectThread extends Thread {
        public void run() {
            try {
                socket = strategy.connect();
                sin = socket.getInputStream();
                sout = socket.getOutputStream();
                in = new DataInputStream(sin);
                out = new DataOutputStream(sout);
                keyboard = new BufferedReader(new InputStreamReader(System.in));
                writeThread = new WriteThread();
                writeThread.start();
                readThread = new ReadThread();
                readThread.start();
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
