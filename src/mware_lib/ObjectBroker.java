package mware_lib;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * core of the middleware:
 * Maintains a Reference to the NameService
 * Singleton
 */
public class ObjectBroker {

    private final NameServiceImpl nameService;

    private ServerSocket serverSocket = null;
    private boolean running = true;

    /**
     * @return an Implementation for a local NameService
     */
    public NameService getNameService() {
        return this.nameService;
    }

    /**
     * shuts down the process, the OjectBroker is running in
     * terminates process
     */
    public void shutdown() {
        this.running = false;
    }

    private ObjectBroker(String nsHost, int nsPort) {


        try {
            this.serverSocket = new ServerSocket(0);
            System.out.println(String.format("local NameService listening on %s", serverSocket.getLocalSocketAddress()));
            new ObjectBrokerThread().start();
        } catch (IOException e) {
            System.err.println("Error starting server socket.");
            e.printStackTrace();
        }

        this.nameService = new NameServiceImpl(nsHost, nsPort, this.serverSocket.getInetAddress().getHostAddress(), this.serverSocket.getLocalPort());

    }

    /**
     * Initializes the ObjectBroker / creates the local NameService
     *
     * @param host hostname or IP of Nameservice
     * @param port port NameService is listening at
     * @return an ObjectBroker Interface to Nameservice
     */
    public static ObjectBroker init(String host, int port) {
        return new ObjectBroker(host, port);
    }

    private class ObjectBrokerThread extends Thread {
        @Override
        public void run() {
            try {
                while (running) {
                    Socket socket = serverSocket.accept();
                    new MethodRequestHandler(socket, nameService).start();
                }
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Error accepting connections on server socket.");
                e.printStackTrace();
            }
        }
    }
}
