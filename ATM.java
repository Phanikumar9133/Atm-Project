import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple ATM Machine simulation.
 * Features:
 * - Balance inquiry
 * - Cash withdrawal and deposit
 * - PIN change
 * - Transaction history
 *
 * Author: [Your Name]
 * Date: [Submission Date]
 */
public class ATM {

    // Private variables for balance and PIN
    private int pin;
    private int balance;
    private List<String> transactionHistory;

    /**
     * Constructor to initialize ATM with a balance and PIN.
     * 
     * @param initialBalance Initial account balance.
     * @param pin            User's ATM PIN.
     */
    public ATM(int initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Displays the main menu of ATM operations.
     */
    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Adds money to the account and records the transaction.
     * 
     * @param amount Amount to deposit.
     */
    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: Rs." + amount);
        System.out.println("Rs." + amount + " deposited successfully.");
    }

    /**
     * Withdraws money from the account if funds are sufficient.
     * 
     * @param amount Amount to withdraw.
     */
    public void withdraw(int amount) {
        if (amount <100) {
            System.out.println("Enter a valid amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            transactionHistory.add("Failed Withdrawal Attempt: Rs." + amount);
        } else {
            balance -= amount;
            transactionHistory.add("Withdrew: Rs." + amount);
            System.out.println("Rs." + amount + " withdrawn successfully.");
        }
    }

    /**
     * Returns the current account balance.
     * 
     * @return Account balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Validates the entered PIN.
     * 
     * @param inputPin PIN entered by the user.
     * @return True if correct, false otherwise.
     */
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    /**
     * Changes the account PIN.
     * 
     * @param newPin New PIN to be set.
     */
    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    /**
     * Displays the transaction history.
     */
    public void printTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    /**
     * Main method to run the ATM simulation.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create ATM instance with initial balance and PIN
        ATM atm = new ATM(2000, 9133);

        // Allow 3 attempts to enter correct PIN
        int attempts = 3;
        boolean authenticated = false;

        while (attempts > 0) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = Integer.parseInt(scanner.nextLine());

            if (atm.validatePin(enteredPin)) {
                authenticated = true;
                break;
            } else {
                attempts--;
                System.out.println("Incorrect PIN. Attempts left: " + attempts);
            }
        }

        if (!authenticated) {
            System.out.println("Too many incorrect attempts. Card blocked.");
            scanner.close();
            return;
        }

        // User menu loop
        int option = 0;
        while (option != 6) {
            atm.displayMenu();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Your balance is: Rs." + atm.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = Integer.parseInt(scanner.nextLine());
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = Integer.parseInt(scanner.nextLine());
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = Integer.parseInt(scanner.nextLine());
                    atm.changePin(newPin);
                    break;
                case 5:
                    atm.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
