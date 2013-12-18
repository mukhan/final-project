package bankingapplication;

import static bankingapplication.Account.CheckingAccount;

public class CheckingAccount extends Account {

    private double transFee = 0.10;

    public CheckingAccount(String name, double balance) {
        super(name, balance);
        this.accountType = CheckingAccount;
    }

    public CheckingAccount(String name, double balance, String dateOpened) {
        super(name, balance, dateOpened);
        this.accountType = CheckingAccount;
    }

    @Override
    public double debit(double amount) {
        super.debit(amount);
        double newBal = this.getBalance();
        newBal -= transFee;
        this.setBalance(newBal);
        return newBal;
    }
}