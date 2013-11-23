package Test;

import bank_access.ManagerImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;

public class TestFooClient {
    public static void main(String[] args) {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();

        Object result = ns.resolve("foo");
        System.out.println("foo = " + result);

        ManagerImplBase manager = ManagerImplBase.narrowCast(result);
        System.out.println(manager.createAccount("foo", "bar"));

        ob.shutdown();
    }
}
