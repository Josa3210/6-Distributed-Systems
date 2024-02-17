import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Trying to connect to server");
            ServerSocket ss = new ServerSocket(4000);
            Socket socket = ss.accept();
            System.out.println("Succesfully connected: " + socket.getLocalSocketAddress().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
