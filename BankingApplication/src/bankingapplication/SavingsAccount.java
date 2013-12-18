package bankingapplication;

import org.joda.time.*;

public class SavingsAccount extends Account {

    private double interestRate = 0.0001;

    public SavingsAccount(String name, double balance) {
        super(name, balance);
        this.accountType = SavingsAccount;
    }

    public SavingsAccount(String name, double balance, String dateOpened) {
        super(name, balance, dateOpened);
        this.accountType = SavingsAccount;
    }

    @Override
    public double debit(double amount) {
        super.debit(amount);
        double newBal = this.getBalance() + this.calcInterest();
        this.setBalance(newBal);
        return newBal;
    }

    public double calcInterest() {
        int numberOfDays = new Period(dateOpened, dateToday).getDays(); // Period is a JodaTime object that helps calculate the difference in days.
        double interest = this.getBalance() * interestRate * numberOfDays;
        return interest;
    }
}