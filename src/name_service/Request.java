package name_service;

import java.util.Arrays;
import java.util.List;

class Request {

    private final String command;
    private final String handle;
    private final String host;
    private final int port;

    private final List<String> validCommands = Arrays.asList(new String[]{"rebind", "resolve"});

    Request(String requestString) {
        if (requestString.matches("^[a-zA-z]+![a-zA-z]+$")) {
            String[] split = requestString.split("!");
            this.command = split[0];
            this.handle = split[1];
            this.host = null;
            this.port = 0;
        } else if (requestString.matches("^[a-zA-z]+![a-zA-z]+:[a-zA-z]+:[1-9][0-9]+$")) {
            String[] split = requestString.split("!");
            String[] arguments = split[1].split(":");
            this.command = split[0];
            this.handle = arguments[0];
            this.host = arguments[1];
            this.port = Integer.parseInt(arguments[2]);
        } else {
            this.command = null;
            this.handle = null;
            this.host = null;
            this.port = 0;
        }
    }

    boolean isValid() {
        return this.command != null
                && this.handle != null
                && this.validCommands.contains(this.command);
    }

    String getErrorMessage() {
        if (this.command == null) return "invalid_syntax";
        if (!this.validCommands.contains(this.command)) return "invalid_command";
        return null;
    }

    String getCommand() {
        return command;
    }

    String getHandle() {
        return handle;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getCommand(), this.getHandle());
    }
}
