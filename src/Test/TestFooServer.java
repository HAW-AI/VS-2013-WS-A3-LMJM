package Test;

import mware_lib.NameService;
import mware_lib.ObjectBroker;

public class TestFooServer {
    public static void main(String[] args) {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();

        Object foo = new FancyAccount();
        ns.rebind(foo, "foo");
    }
}
