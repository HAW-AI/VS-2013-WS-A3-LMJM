package name_service;

import java.util.Arrays;
import java.util.List;

public class TestStuff {
    public static void main(String[] args) {
        String[] strings = new String[]{
                "rebind!name:responseHostName:1651",
                "resolve!foobar",
                "resolvae!friemelkopf",
                "foo"
        };

        List<String> list = Arrays.asList(strings);

        for (String s : list) {
            Request r = new Request(s);
            System.out.println(String.format("valid: %b, command: %s, handle: %s, host: %s, port %d",
                    r.isValid(), r.getCommand(), r.getHandle(), r.getHost(), r.getPort()));
        }
    }
}
