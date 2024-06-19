import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton, depositButton, checkBalanceButton;

    public ATM(BankAccount account) {
        this.account = account;
        createGUI();
    }

    private void createGUI() {
        setTitle("ATM Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);

        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);

        amountField = new JTextField();
        amountField.setHorizontalAlignment(JTextField.CENTER);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    updateBalance();
                    JOptionPane.showMessageDialog(ATM.this, "Withdrawal successful!");
                } else {
                    JOptionPane.showMessageDialog(ATM.this, "Insufficient balance.");
                }
            }
        });

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                updateBalance();
                JOptionPane.showMessageDialog(ATM.this, "Deposit successful!");
            }
        });

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalance();
            }
        });

        add(new JLabel("Welcome to the ATM!"));
        add(balanceLabel);
        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(checkBalanceButton);
        add(new JLabel());

        setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
    }
}
