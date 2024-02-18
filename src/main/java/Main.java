import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        try {
            // Create the message
            String message = "Hey Server!";
            byte[] buf = message.getBytes();

            // Get the ip address
            InetAddress ip = InetAddress.getByName("127.0.0.1");

            // Create the datagram
            DatagramPacket p = new DatagramPacket(buf, 0, buf.length, ip,4000);

            // Create a socket and send the datagrams
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
