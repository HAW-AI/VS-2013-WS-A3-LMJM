package mware_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class NameServiceImpl extends NameService {

    private final String nsHost;
    private final int nsPort;

    private final String obHost;
    private final int obPort;

    private final Map<String, Object> registry = new HashMap<String, Object>();

    NameServiceImpl(String nsHost, int nsPort, String obHost, int obPort) {
        this.nsHost = nsHost;
        this.nsPort = nsPort;
        this.obHost = obHost;
        this.obPort = obPort;
    }

    @Override
    public synchronized void rebind(Object servant, String name) {
        this.registry.put(name, servant);

        try {
            Socket socket = new Socket(this.nsHost, this.nsPort);
            writeToSocket(socket, String.format("rebind!%s:%s:%d\n", name, this.obHost, this.obPort));

            String[] input = readFromSocket(socket).split("!");
            if (input[0].equals("success")) {
                System.out.println(String.format("registered name %s", name));
            } else {
                System.err.println(String.format("%s cannot be resolved.\n-> Error: %s", name, input[1]));
            }
        } catch (IOException e) {
            System.err.println("Error connecting to global NameService");
            e.printStackTrace();
        }
    }

    synchronized Object retrieve(String name) {
        return this.registry.get(name);
    }

    @Override
    public Object resolve(String name) {
        Object result = null;

        try {
            Socket socket = new Socket(this.nsHost, this.nsPort);
            writeToSocket(socket, String.format("resolve!%s", name));
            String[] input = readFromSocket(socket).split("!");

            if (input[0].equals("success")) {
                System.out.println(String.format("resolved %s %s", name, input[1]));
                String[] arguments = input[1].split(":");
                result = new Stub(name, arguments[0], Integer.parseInt(arguments[1]));
            } else {
                System.err.println(String.format("%s cannot be resolved.\n-> Error: %s", name, input[1]));
            }
        } catch (IOException e) {
            System.err.println("Error connecting to global NameService");
            e.printStackTrace();
        }

        return result;
    }

    private String readFromSocket(Socket socket) {
        String result = "";

        try {
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            result = buffer.readLine();
        } catch (IOException e) {
            System.err.println("Error reading from socket.");
            e.printStackTrace();
        }

        return result;
    }

    private void writeToSocket(Socket socket, String s) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write((s + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to socket.");
            e.printStackTrace();
        }
    }


}
