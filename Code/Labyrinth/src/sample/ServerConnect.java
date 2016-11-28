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
        System.out.println(22);
        ServerSocket server = new ServerSocket(8030);
        socket = server.accept();
        System.out.println(33);
        return socket;

    }

}
    /*
    class SendThread extends Thread {
        @Override
        public void run()	//Этот метод будет выполнен в побочном потоке
        {
            Socket socket = null;
            try {
                ServerSocket server = new ServerSocket(8030);
                socket = server.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);
                String read = null;
                int i=0;
                while(true) {
                    read = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                    System.out.println(read);
                    while(line == null){
                        Thread.sleep(2000);
                    }
                    System.out.println(line);
                    out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                    out.flush(); // заставляем поток закончить передачу данных.
                    line = null;
                }
            } catch (IOException e) {
                System.err.println("Ошибка: " + e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close(); // разрыв соединения
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }
 */

