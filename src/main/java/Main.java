import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",4000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
