import auth.AccountSerializer;
import org.server.LoginTask;
import org.server.MultiplayerDataExchangeTask;
import org.server.TCPServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient {


    public static void main(String[] args) throws IOException {

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();

        try (Socket clientSocket = new Socket()) {
            clientSocket.connect(new InetSocketAddress("localhost", 7870), 1000);
            new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(new LoginTask("mail","name","pw"));
            new ObjectOutputStream(clientSocket.getOutputStream()).writeObject(new MultiplayerDataExchangeTask());


        }


    }
}
