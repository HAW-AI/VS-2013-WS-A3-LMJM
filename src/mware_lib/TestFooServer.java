package mware_lib;

public class TestFooServer {
    public static void main(String[] args) {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();

        System.out.println("new object");
        Object foo = new Object();

        System.out.println("sending bind");
        ns.rebind(foo, "foo");

        Object result = ns.resolve("foo");
        System.out.println("foo = " + result);
    }
}
