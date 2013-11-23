package mware_lib;

import java.util.Arrays;
import java.util.List;

public class TestFooServer {
    public static void main(String[] args) {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();

        System.out.println("new object");
        Object foo = new Object();

        System.out.println("sending bind");
        ns.rebind(foo, "foo");
    }
}
