import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}

public class BankManagementGUI {
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private JFrame frame;
    private JTextField accountNumberField, accountHolderField, amountField;
    private JTextArea outputArea;

    public BankManagementGUI() {
        frame = new JFrame("Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField(15);

        JLabel accountHolderLabel = new JLabel("Account Holder:");
        accountHolderField = new JTextField(15);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        JButton createAccountButton = new JButton("Create Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton displayButton = new JButton("Display Account Info");

        outputArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String accountHolderName = accountHolderField.getText();
                BankAccount account = new BankAccount(accountNumber, accountHolderName);
                accounts.add(account);
                outputArea.append("Account created successfully.\n");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double depositAmount = Double.parseDouble(amountField.getText());
                BankAccount account = findAccount(accountNumber);
                if (account != null) {
                    account.deposit(depositAmount);
                    outputArea.append("Deposited $" + depositAmount + " to account " + accountNumber + "\n");
                } else {
                    outputArea.append("Account not found.\n");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double withdrawalAmount = Double.parseDouble(amountField.getText());
                BankAccount account = findAccount(accountNumber);
                if (account != null) {
                    account.withdraw(withdrawalAmount);
                    outputArea.append("Withdrawn $" + withdrawalAmount + " from account " + accountNumber + "\n");
                } else {
                    outputArea.append("Account not found or insufficient balance.\n");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                BankAccount account = findAccount(accountNumber);
                if (account != null) {
                    outputArea.append("Account Number: " + account.getAccountNumber() + "\n");
                    outputArea.append("Account Holder: " + account.getAccountHolderName() + "\n");
                    outputArea.append("Balance: $" + account.getBalance() + "\n");
                } else {
                    outputArea.append("Account not found.\n");
                }
            }
        });

        frame.add(accountNumberLabel);
        frame.add(accountNumberField);
        frame.add(accountHolderLabel);
        frame.add(accountHolderField);
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(createAccountButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(displayButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankManagementGUI();
            }
        });
    }
}