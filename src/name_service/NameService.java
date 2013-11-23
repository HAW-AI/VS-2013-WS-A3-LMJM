package name_service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NameService {

    private Map<String, Reference> registry = new HashMap<String, Reference>();

    public NameService(int port) {
        new NameServiceThread(port).start();
    }

    synchronized void rebind(String key, Reference value) {
        registry.put(key, value);
    }

    synchronized Reference resolve(String key) {
        return registry.get(key);
    }

    private class NameServiceThread extends Thread {
        private final int port;

        NameServiceThread(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(this.port);
                System.out.format("global NameService listening on %s", serverSocket.getLocalSocketAddress());
                while (true) {
                    Socket socket = serverSocket.accept();
                    new RequestHandler(NameService.this, socket).start();
                }
            } catch (IOException e) {
                System.err.println("global NameService: Error starting server socket.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NameService(9876);
    }
}
