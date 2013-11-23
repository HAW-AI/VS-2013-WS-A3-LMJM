package mware_lib;
/**
 * core of the middleware:
 * Maintains a Reference to the NameService
 * Singleton
 */
public class ObjectBroker {

    private final NameServiceImpl nameService;

    /**
     * @return an Implementation for a local NameService
     */
    public NameService getNameService() {
        return this.nameService;
    }

    /**
     * shuts down the process, the OjectBroker is running in
     * terminates process
     */
    public void shutdown() {
        this.nameService.shutDown();
    }

    private ObjectBroker(String host, int port) {
        this.nameService = new NameServiceImpl(host, port);
    }

    /**
     * Initializes the ObjectBroker / creates the local NameService
     *
     * @param host hostname or IP of Nameservice
     * @param port        port NameService is listening at
     * @return an ObjectBroker Interface to Nameservice
     */
    public static ObjectBroker init(String host, int port) {
        return new ObjectBroker(host, port);
    }
}
