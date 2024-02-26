import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

            // Get readers and printers in place to communicate with the server
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverInput = new PrintWriter(socket.getOutputStream(),true);

            // Print whatever the server sends you
            System.out.println(serverOutput.readLine());

            // Give your userID and send it to the server
            String userId = userInput.readLine();
            serverInput.println(userId);

            while (true) {
                // Reading message sent from clientHandler
                System.out.println(serverOutput.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

