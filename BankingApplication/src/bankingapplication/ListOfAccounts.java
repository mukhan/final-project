package bankingapplication;

import java.util.*;

public class ListOfAccounts extends ArrayList<Account> {

    public ArrayList<Account> accountList;

    public ListOfAccounts() {
        
        this.accountList = new ArrayList<Account>();
        
        CheckingAccount acc1 = new CheckingAccount("Harry Potter", 2212604.18, "10-12-1996");
        SavingsAccount acc2 = new SavingsAccount("Hermione Granger", 236645.41, "07-10-2008");
        CheckingAccount acc3 = new CheckingAccount("Hermione Granger", 44697.41, "07-10-2008");
        SavingsAccount acc4 = new SavingsAccount("Ronald Weasley", 100000, "01-05-2013");
        CheckingAccount acc5 = new CheckingAccount("Ginny Weasley", 64536.65);

        accountList.add(acc1);
        accountList.add(acc2);
        accountList.add(acc3);
        accountList.add(acc4);
        accountList.add(acc5);
        
    }

    public void create(String name, double balance, int accType) {
        if (accType == Account.CheckingAccount) {
            CheckingAccount newAcc = new CheckingAccount(name, balance);
            accountList.add(newAcc);
        } else if (accType == Account.SavingsAccount) {
            SavingsAccount newAcc = new SavingsAccount(name, balance);
            accountList.add(newAcc);
        }
    }

    public boolean delete(String name, int accType) {
        for (int i = 0; i < accountList.size(); i++) {
            if (name.equalsIgnoreCase(accountList.get(i).getName())
                    && accType == accountList.get(i).getAccType()) {
                accountList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Account getAccount(String name, int accType) {
        for (int i = 0; i < accountList.size(); i++) {
            if (name.equalsIgnoreCase(accountList.get(i).getName())
                    && accType == accountList.get(i).getAccType()) {
                return accountList.get(i);
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Account s : accountList) {
            sb.append(s.toString());
            sb.append("\n");
        }
        return sb.toString();

    }
}