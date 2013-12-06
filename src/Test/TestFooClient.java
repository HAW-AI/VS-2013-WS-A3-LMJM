package Test;

import bank_access.ManagerImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;

public class TestFooClient {
    public static void main(String[] args) {
        new TestFooClient();
    }

    TestFooClient() {
        ObjectBroker ob = ObjectBroker.init("localhost", 9876);
        NameService ns = ob.getNameService();

        new FooThread(ns, "thread1").run();
        new FooThread(ns, "thread2").run();

        ob.shutDown();
    }

    class FooThread extends Thread {
        private final NameService ns;
        private final String name;

        FooThread(NameService ns, String name) {
            this.ns = ns;
            this.name = name;
        }

        @Override
        public void run() {
            Object gor = this.ns.resolve("foo");
            ManagerImplBase manager = ManagerImplBase.narrowCast(gor);
            System.out.println(manager.createAccount(this.name, "bar"));
        }
    }
}
