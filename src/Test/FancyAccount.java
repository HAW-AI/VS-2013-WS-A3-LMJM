package Test;

import bank_access.AccountImplBase;
import bank_access.OverdraftException;

public class FancyAccount extends AccountImplBase {

    @Override
    public void transfer(double amount) throws OverdraftException {
        throw new OverdraftException("fooo" + amount);
    }

    @Override
    public double getBalance() {
        return 0;
    }
}
