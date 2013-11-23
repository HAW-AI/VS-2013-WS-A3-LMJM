package name_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

class RequestHandler extends Thread {

    private final Socket socket;
    private final GlobalNameService globalNameService;

    RequestHandler(GlobalNameService globalNameService, Socket socket) {
        this.globalNameService = globalNameService;
        this.socket = socket;
    }

    @Override
    public void run() {
        String input = readFromSocket();
        Request request = new Request(input);

        System.out.println(String.format("incoming \"%s\"", input));

        if (request.isValid()) {
            respondToRequest(request);
        } else {
            writeToSocket(String.format("error!%s", request.getErrorMessage()));
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            System.err.println("global GlobalNameService: Error closing socket.");
        }
    }

    private void respondToRequest(Request request) {
        if (request.getCommand().equals("rebind")) {
            System.out.println(String.format("handling request %s from %s:%d", request, request.getHost(), request.getPort()));
            Reference reference = new Reference(request.getHost(), request.getPort());
            this.globalNameService.rebind(request.getHandle(), reference);
            writeToSocket(String.format("success!%s", request.getHandle()));
        }

        if (request.getCommand().equals("resolve")) {
            System.out.println(String.format("handling request %s", request));
            Reference reference = this.globalNameService.resolve(request.getHandle());
            if (reference != null)
                writeToSocket(String.format("success!%s", reference.toString()));
            else
                writeToSocket("error!handle_not_found");
        }
    }

    private String readFromSocket() {
        String result = "";

        try {
            InputStreamReader in = new InputStreamReader(this.socket.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            result = buffer.readLine();
        } catch (IOException e) {
            System.err.println("Error reading from socket.");
            e.printStackTrace();
        }

        return result;
    }

    private void writeToSocket(String s) {
        try {
            OutputStream out = this.socket.getOutputStream();
            out.write((s + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to socket.");
            e.printStackTrace();
        }
    }
}
