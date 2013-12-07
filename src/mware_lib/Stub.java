package mware_lib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Stub {

    private final String name;
    private final String host;
    private final int port;

    Stub(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return String.format("Stub %s@%s:%d", this.name, this.host, this.port);
    }

    public MethodResponse sendMethodRequest(String method, Class[] classes, Object[] arguments) {
        MethodRequest request = new MethodRequest(this.name, method, classes, arguments);
        MethodResponse result = null;

        try {
            Socket socket = new Socket(this.host, this.port);
            writeToSocket(socket, request);
            result = readFromSocket(socket);
        } catch (IOException e) {
            System.err.println("Unable to connect to remote object host.");
            e.printStackTrace();
        }

        return result;
    }

    private static MethodResponse readFromSocket(Socket socket) {
        MethodResponse response = null;

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            response = (MethodResponse) in.readObject();
        } catch (IOException e) {
            System.err.println("Error reading from socket.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error retrieving MethodResponse.");
            e.printStackTrace();
        }

        return response;
    }

    private static void writeToSocket(Socket socket, MethodRequest request) {
        try {
            ObjectOutput out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(request);
        } catch (IOException e) {
            System.err.println("Error writing to socket.");
            e.printStackTrace();
        }
    }
}
