import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.time.LocalTime;

public class Server {
    ServerSocket serverSocket;

    public Server(InetSocketAddress socketAddress) {
        try {
            this.serverSocket = new ServerSocket(); // Create a ServerSocket
            this.serverSocket.bind(socketAddress);  // Bind the given address to the current ServerSocket
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method that opens a server on port 4000
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(4000);
        Server server = new Server(address);
        server.startServer();
    }

    public void startServer() {
        try {
            System.out.println("Starting the server on address: " + this.serverSocket.getLocalSocketAddress());
            while (!this.serverSocket.isClosed() && this.serverSocket.isBound()) {
                this.serverSocket.accept();
                System.out.println("Successfully connected to client");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopServer() {
        try {
            System.out.println("Closing the server on address: " + this.serverSocket.getLocalSocketAddress());
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
