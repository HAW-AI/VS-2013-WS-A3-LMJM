package mware_lib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

class MethodRequestHandler extends Thread {
    private final Socket socket;
    private final NameServiceImpl nameService;

    public MethodRequestHandler(Socket socket, NameServiceImpl nameService) {
        this.socket = socket;
        this.nameService = nameService;
    }

    public void run() {
        MethodResponse response;

        try {
            MethodRequest request = readFromSocket(this.socket);
            Object callee = this.nameService.retrieve(request.getTarget());

            Method method = callee.getClass().getMethod(request.getMethod(), request.getTypes());
            method.setAccessible(true);
            Object result = method.invoke(callee, request.getArguments());

            response = new MethodResponse(result, null);
        } catch (InvocationTargetException e) {
            response = new MethodResponse(null, e.getTargetException());
        } catch (Exception e) {
            response = new MethodResponse(null, e);
        }

        writeToSocket(socket, response);

        try {
            this.socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket.");
            e.printStackTrace();
        }
    }

    public static MethodRequest readFromSocket(Socket socket) {
        MethodRequest result = null;

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            result = (MethodRequest) in.readObject();
        } catch (IOException e) {
            System.err.println("Error reading from socket.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error retrieving MethodResponse.");
            e.printStackTrace();
        }

        return result;
    }

    public static void writeToSocket(Socket socket, Object o) {
        try {
            ObjectOutput out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(o);
        } catch (IOException e) {
            System.err.println("Error writing to socket.");
            e.printStackTrace();
        }
    }
}
