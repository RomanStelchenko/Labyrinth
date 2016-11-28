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
   /* class SendThread extends Thread {
        @Override
        public void run()    //Этот метод будет выполнен в побочном потоке
        {
            Socket socket = null;
            try {
                socket = new Socket("GREEN-PC", 8030);
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);
                // Создаем поток для чтения с клавиатуры.
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                line = null;
                int i=0;
                while (true) {
                    while(line == null){
                        Thread.sleep(1000);
                    }
                    System.out.println(line);
                    out.writeUTF(line); // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    System.out.println(line);
                    line = null;
                }
            }catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    */