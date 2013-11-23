package name_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

class RequestHandler extends Thread {

    private final Socket socket;
    private final NameService nameService;

    RequestHandler(NameService nameService, Socket socket) {
        this.nameService = nameService;
        this.socket = socket;
    }

    @Override
    public void run() {
        String input = readFromSocket();
        Request request = new Request(input);

        if (request.isValid()) {
            respondToRequest(request);
        } else {
            writeToSocket(String.format("error!%s", request.getErrorMessage()));
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            System.err.println("global NameService: Error closing socket.");
        }
    }

    private void respondToRequest(Request request) {
        System.out.format("handling request %s from %s", request, this.socket.getLocalSocketAddress());
        if (request.getCommand().equals("rebind")) {
            Reference reference = new Reference(request.getHost(), request.getPort());
            this.nameService.rebind(request.getHandle(), reference);
            writeToSocket(String.format("success!%s", request.getHandle()));
        }

        if (request.getCommand().equals("resolve")) {
            Reference reference = this.nameService.resolve(request.getHandle());
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
            System.err.println("global NameService: Error reading from incoming socket.");
            e.printStackTrace();
        }

        return result;
    }

    private void writeToSocket(String s) {
        try {
            OutputStream out = this.socket.getOutputStream();
            out.write(s.getBytes());
        } catch (IOException e) {
            System.err.println("global NameService: Error writing to incoming socket.");
            e.printStackTrace();
        }
    }
}
