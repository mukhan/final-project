package bankingapplication;

public class BankingApplication {

    public static void main(String[] args) {
        
//         Some stock accounts are initialized in the constructor of the 
//         ListOfAccounts class, because initializing them and adding it to
//         ListOfAccounts object HERE would, for some reason, not add them to
//         the list. I leave that code commented here so you can see the proble.
//         for yourself. Please comment out the accounts initialized in the 
//         constructor of the ListOfAccounts class.
        
//        ListOfAccounts accountList = new ListOfAccounts();
//        
//        CheckingAccount acc1 = new CheckingAccount("Harry Potter", 2212604.18, "10-12-1996");
//        SavingsAccount acc2 = new SavingsAccount("Hermione Granger", 236645.41, "07-10-2008");
//        CheckingAccount acc3 = new CheckingAccount("Hermione Granger", 44697.41, "07-10-2008");
//        SavingsAccount acc4 = new SavingsAccount("Ronald Weasley", 100000, "01-05-2013");
//        CheckingAccount acc5 = new CheckingAccount("Ginny Weasley", 64536.65);
//
//        accountList.add(acc1);
//        accountList.add(acc2);
//        accountList.add(acc3);
//        accountList.add(acc4);
//        accountList.add(acc5);
//        
//        new BankGUI(accountList).run();
        
        new BankGUI().run();
    }
}
