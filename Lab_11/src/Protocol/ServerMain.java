package Protocol;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException {
        ProtocolServer server = new ProtocolServer(3000);
        server.start();
        server.join();
    }
}
