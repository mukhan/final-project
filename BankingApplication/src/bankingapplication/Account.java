package bankingapplication;

import org.joda.time.*;
import org.joda.time.format.*;

public class Account {

    private String name;
    private double balance;
    public int accountType;
    public DateTime dateOpened;
    protected static DateTime dateToday = DateTime.now();
    protected static int CheckingAccount = 0;
    protected static int SavingsAccount = 1;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.dateOpened = dateToday;
    }

    public Account(String name, double balance, String dateOpened) {
        this.name = name;
        this.balance = balance;
        
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy");
        DateTime date = fmt.parseDateTime(dateOpened);
        this.dateOpened = date;
    }

    public double credit(double amount) {
        balance += amount;
        return balance;
    }

    public double debit(double amount) {
        double newBal = this.getBalance();
        newBal -= amount;
        this.setBalance(newBal);
        return newBal;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double newBal) {
        this.balance = newBal;
    }
    
    public DateTime getDateOpened() {
        return dateOpened;
    }
    
    public void setDateOpened(String date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy");
        DateTime dateFormatted = fmt.parseDateTime(date);
        this.dateOpened = dateFormatted;
    }
    
    public int getAccType() {
        return accountType;
    }
    
    public void setAccType(int accType) {
        this.accountType = accType;
    }

    public String toString() {
        String accType;
        if (accountType == CheckingAccount) {
            accType = "Checking Account";
        } else accType = "Savings Account";
        StringBuilder sb = new StringBuilder();
        sb.append("Account Name: " + name + "\n");
        sb.append("Account Type: " + accType + "\n");
        sb.append("Current Balance: " + Util.formatMoney(balance) + "\n");
        sb.append("Date Opened: " + dateOpened.toString("MM-dd-yyyy") + "\n");
        return sb.toString();
    }
}