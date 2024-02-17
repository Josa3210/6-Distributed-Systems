import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // Create an empty socket
        Socket socket = new Socket();

        // Create an address where the socket needs to connect to
        InetSocketAddress address = new InetSocketAddress(4000);
        System.out.println("Trying to connect to: " + address);
        try {

            // Try to connect to a socket
            socket.connect(address);
            System.out.println("Successfully connected");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

