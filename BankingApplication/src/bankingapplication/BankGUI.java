package bankingapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankGUI implements Runnable {

    static JFrame frame;
    static JPanel displayPanel;
    static JTextArea textArea;
    static JMenuBar menuBar;
    static ListOfAccounts listOfAccs;

    public BankGUI() {
        this.listOfAccs = new ListOfAccounts();
        createGUI();
    }
    
    // optional constructor that takes in a ListOfAccounts object initialized in the
    // main() method of BankingApplication class.
    public BankGUI(ListOfAccounts list) {
        this.listOfAccs = list;
        createGUI();
    }

    private void createGUI() {
        createManageAccountsMenu();
        createDepWithMenu();
        makeDisplay();
        updateDisplay();
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(displayPanel, BorderLayout.CENTER);
        textArea.setEditable(false);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void createManageAccountsMenu() {

        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Manage Accounts");
        menuBar.add(menu);

        JMenuItem createAccount = new JMenuItem("Create New Account");
        createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JLabel instructionLabel = new JLabel("Please enter name, starting balance, and account type of the account you want to create.");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField nameField = new JTextField(15);
                JLabel balLabel = new JLabel("Starting Balance");
                balLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField balField = new JTextField(8);
                JLabel accLabel = new JLabel("Account Type");
                accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                DefaultComboBoxModel accType = new DefaultComboBoxModel();
                accType.addElement("Checking Account");
                accType.addElement("Savings Account");
                JComboBox accTypeCombo = new JComboBox(accType);
                accTypeCombo.setSelectedIndex(0);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.add(instructionLabel);
                infoPanel.add(nameLabel);
                infoPanel.add(nameField);
                infoPanel.add(balLabel);
                infoPanel.add(balField);
                infoPanel.add(accLabel);
                infoPanel.add(accTypeCombo);

                int inputBox = JOptionPane.showConfirmDialog(frame, infoPanel,
                        "Account Creation",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (inputBox == JOptionPane.OK_OPTION) {

                    JPanel dateQueryPanel = new JPanel();
                    dateQueryPanel.setLayout(new BoxLayout(dateQueryPanel, BoxLayout.Y_AXIS));
                    dateQueryPanel.add(new JLabel("Would you like to set the starting date of the account?"));
                    dateQueryPanel.add(new JLabel("If you select No, the date will be set to today's date."));

                    int dateBox = JOptionPane.showConfirmDialog(frame, dateQueryPanel,
                            "Set the opening date of account", JOptionPane.YES_NO_OPTION);

                    listOfAccs.create(nameField.getText(), Double.parseDouble(balField.getText()),
                            accTypeCombo.getSelectedIndex());

                    if (dateBox == JOptionPane.YES_OPTION) {

                        JLabel dateLabel = new JLabel("Please enter new opening date of account.");
                        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        JTextField dateField = new JTextField("mm-dd-yyyy");
                        JPanel datePanel = new JPanel();
                        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
                        datePanel.add(dateLabel);
                        datePanel.add(dateField);
                        int setDateBox = JOptionPane.showConfirmDialog(frame, datePanel, "Set Opening Date",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (setDateBox == JOptionPane.OK_OPTION) {
                            listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()).setDateOpened(dateField.getText());
                            JOptionPane.showMessageDialog(frame, "Set the opening date to " + dateField.getText() + " successfully.");
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Account has been created!");

                }
                updateDisplay();
            }
        });
        menu.add(createAccount);

        JMenuItem deleteAccount = new JMenuItem("Delete An Account");
        deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JLabel instructionLabel = new JLabel("Please enter name and account type of the account you want to delete.");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField nameField = new JTextField(15);
                JLabel accLabel = new JLabel("Account Type");
                accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                DefaultComboBoxModel accType = new DefaultComboBoxModel();
                accType.addElement("Checking Account");
                accType.addElement("Savings Account");
                JComboBox accTypeCombo = new JComboBox(accType);
                accTypeCombo.setSelectedIndex(0);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.add(instructionLabel);
                infoPanel.add(nameLabel);
                infoPanel.add(nameField);
                infoPanel.add(accLabel);
                infoPanel.add(accTypeCombo);

                int inputBox = JOptionPane.showConfirmDialog(frame, infoPanel,
                        "Account Deletion",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (inputBox == JOptionPane.OK_OPTION) {
                    if (listOfAccs.delete(nameField.getText(), accTypeCombo.getSelectedIndex())) {
                        JOptionPane.showMessageDialog(frame, "Account has been deleted!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Deletion failed! Check credentials again.");
                    }
                }
                updateDisplay();
            }
        });
        menu.add(deleteAccount);

        JMenuItem setDate = new JMenuItem("Set Opening Date Of An Account");
        setDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JLabel instructionLabel = new JLabel("Please enter name and account type of the account you want to change.");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField nameField = new JTextField(15);
                JLabel accLabel = new JLabel("Account Type");
                accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                DefaultComboBoxModel accType = new DefaultComboBoxModel();
                accType.addElement("Checking Account");
                accType.addElement("Savings Account");
                JComboBox accTypeCombo = new JComboBox(accType);
                accTypeCombo.setSelectedIndex(0);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.add(instructionLabel);
                infoPanel.add(nameLabel);
                infoPanel.add(nameField);
                infoPanel.add(accLabel);
                infoPanel.add(accTypeCombo);

                int inputBox = JOptionPane.showConfirmDialog(frame, infoPanel,
                        "Debiting an account",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (inputBox == JOptionPane.OK_OPTION) {
                    if (listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()) == null) {
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Account not found, please check credentials.");
                        errorPanel.add(errorLabel);
                        JOptionPane.showMessageDialog(frame, errorPanel, "ERROR", JOptionPane.OK_OPTION);

                    } else {

                        JLabel dateLabel = new JLabel("Please enter new opening date of account.");
                        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        JTextField dateField = new JTextField("mm-dd-yyyy");
                        JPanel datePanel = new JPanel();
                        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
                        datePanel.add(dateLabel);
                        datePanel.add(dateField);
                        int dateBox = JOptionPane.showConfirmDialog(frame, datePanel, "Debit Account",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (dateBox == JOptionPane.OK_OPTION) {
                            listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()).setDateOpened(dateField.getText());
                            JOptionPane.showMessageDialog(frame, "Set the opening date to " + dateField.getText() + " successfully.");
                        }
                    }
                }
                updateDisplay();
            }
        });
        menu.add(setDate);
    }

    private void createDepWithMenu() {

        JMenu menu = new JMenu("Deposit/Withdraw");
        menuBar.add(menu);

        JMenuItem debitAccount = new JMenuItem("Withdraw Money");
        debitAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JLabel instructionLabel = new JLabel("Please enter name and account type of the account you want to debit.");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField nameField = new JTextField(15);
                JLabel accLabel = new JLabel("Account Type");
                accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                DefaultComboBoxModel accType = new DefaultComboBoxModel();
                accType.addElement("Checking Account");
                accType.addElement("Savings Account");
                JComboBox accTypeCombo = new JComboBox(accType);
                accTypeCombo.setSelectedIndex(0);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.add(instructionLabel);
                infoPanel.add(nameLabel);
                infoPanel.add(nameField);
                infoPanel.add(accLabel);
                infoPanel.add(accTypeCombo);

                int inputBox = JOptionPane.showConfirmDialog(frame, infoPanel,
                        "Debiting an account",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (inputBox == JOptionPane.OK_OPTION) {
                    if (listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()) == null) {
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Account not found, please check credentials.");
                        errorPanel.add(errorLabel);
                        JOptionPane.showMessageDialog(frame, errorPanel, "ERROR", JOptionPane.OK_OPTION);

                    } else {

                        JLabel debitLabel = new JLabel("Please enter amount to withdraw from account");
                        debitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        JTextField debitField = new JTextField(8);
                        JPanel debitPanel = new JPanel();
                        debitPanel.setLayout(new BoxLayout(debitPanel, BoxLayout.Y_AXIS));
                        debitPanel.add(debitLabel);
                        debitPanel.add(debitField);
                        int debitBox = JOptionPane.showConfirmDialog(frame, debitPanel, "Debit Account",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (debitBox == JOptionPane.OK_OPTION) {
                            listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()).debit(Double.parseDouble(debitField.getText()));
                            JOptionPane.showMessageDialog(frame, "Debited $" + debitField.getText() + " successfully.");
                        }
                    }
                }
                updateDisplay();
            }
        });
        menu.add(debitAccount);

        JMenuItem creditAccount = new JMenuItem("Deposit Money");
        creditAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JLabel instructionLabel = new JLabel("Please enter name and account type of the account you want to deposit to.");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField nameField = new JTextField(15);
                JLabel accLabel = new JLabel("Account Type");
                accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                DefaultComboBoxModel accType = new DefaultComboBoxModel();
                accType.addElement("Checking Account");
                accType.addElement("Savings Account");
                JComboBox accTypeCombo = new JComboBox(accType);
                accTypeCombo.setSelectedIndex(0);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.add(instructionLabel);
                infoPanel.add(nameLabel);
                infoPanel.add(nameField);
                infoPanel.add(accLabel);
                infoPanel.add(accTypeCombo);

                int inputBox = JOptionPane.showConfirmDialog(frame, infoPanel,
                        "Account Deletion",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (inputBox == JOptionPane.OK_OPTION) {
                    if (listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()) == null) {
                        JPanel errorPanel = new JPanel();
                        JLabel errorLabel = new JLabel("Account not found, please check credentials.");
                        errorPanel.add(errorLabel);
                        JOptionPane.showMessageDialog(frame, errorPanel, "ERROR", JOptionPane.OK_OPTION);

                    } else {

                        JLabel debitLabel = new JLabel("Please enter amount to deposit to account");
                        debitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        JTextField debitField = new JTextField(8);
                        JPanel debitPanel = new JPanel();
                        debitPanel.setLayout(new BoxLayout(debitPanel, BoxLayout.Y_AXIS));
                        debitPanel.add(debitLabel);
                        debitPanel.add(debitField);
                        int debitBox = JOptionPane.showConfirmDialog(frame, debitPanel, "Credit Account",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (debitBox == JOptionPane.OK_OPTION) {
                            listOfAccs.getAccount(nameField.getText(), accTypeCombo.getSelectedIndex()).debit(Double.parseDouble(debitField.getText()));
                            JOptionPane.showMessageDialog(frame, "Credited $" + debitField.getText() + " successfully.");
                        }
                    }
                }
                updateDisplay();
            }
        });
        menu.add(creditAccount);
    }

    private void makeDisplay() {
        displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        textArea = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void updateDisplay() {
        String message = "Welcome to Mushfiq's Bank Account Application!";
        message += "\nPlease select the options from the menu to proceed.\n\n";
        message += listOfAccs.toString();
        textArea.setText(message);
    }

    public void run() {
    }
}