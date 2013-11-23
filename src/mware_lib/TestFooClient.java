package mware_lib;

public class TestFooClient {
    public static void main(String[] args) {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();


        Object foo = ns.resolve("foo");
        System.out.println("foo = " + foo);
    }
}
