import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        try {
            // Create socket for UDP at port 4000
            DatagramSocket datagramSocket = new DatagramSocket(4000);

            // Create a buffer for the DatagramPacket to store the message
            byte[] buf = new byte[1024];

            // Create the datagramPacket and assign the size
            DatagramPacket p = new DatagramPacket(buf, buf.length);

            // Receive a message and put it in the DatagramPacket
            datagramSocket.receive(p);

            // Convert the bytes to string
            String str = new String(p.getData(), 0, p.getLength());

            // Print the message
            System.out.println("Received packet: " + str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
