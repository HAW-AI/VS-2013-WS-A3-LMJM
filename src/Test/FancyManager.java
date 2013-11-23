package Test;

import bank_access.ManagerImplBase;

public class FancyManager extends ManagerImplBase {

    @Override
    public String createAccount(String owner, String branch) {
        return String.format("fuck! %s, %s", owner, branch);
    }

}
