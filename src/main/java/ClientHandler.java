import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket clientSocket;


    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Send message to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome to the server");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
