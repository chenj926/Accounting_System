package entity.account;

import entity.transaction.Transaction;
import entity.transaction.TransactionComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Account interface provides a blueprint for an account entity.
 * It includes methods to get and set account details, and to manage user accounts.
 *
 * @author Jessica
 * @author Eric
 */
public abstract class Account {
    protected String username;
    protected String password;
    protected String identification;

    protected List<Transaction> transactions;

    protected float totalIncome;
    protected float totalOutflow;
    protected float totalBalance;

    protected LocalDate lastLoginDate;

    /**
     * Constructs a Account object with the specified username, password, and identification.
     * Initializes the transaction list and sets the income, outflow, and balance to zero.
     *
     * @param username       the username of the user account
     * @param password       the password of the user account
     * @param identification the identification of the user account
     */
    public Account(String username, String password, String identification) {
        this.username = username;
        this.password = password;
        this.identification = identification;

        this.transactions = new ArrayList<>();

        this.totalIncome = 0.0f;
        this.totalOutflow = 0.0f;
        this.totalBalance = 0.0f;
    }

    /**
     * Constructs aN Account object with the specified username, password, identification,
     * total income, total outflow, and total balance. Initializes the transaction list.
     *
     * @param username       the username of the user account
     * @param password       the password of the user account
     * @param identification the identification of the user account
     * @param totalIncome    the total cumulated income of the user account
     * @param totalOutflow   the total cumulated outflow of the user account
     * @param totalBalance   the total cumulated balance of the user account
     */
    public Account(String username, String password, String identification,
                       float totalIncome, float totalOutflow, float totalBalance) {
        this.username = username;
        this.password = password;
        this.identification = identification;

        this.transactions = new ArrayList<>();

        this.totalIncome = totalIncome;
        this.totalOutflow = totalOutflow;
        this.totalBalance = totalBalance;
    }

    /**
     * Gets the identification of the user account.
     *
     * @return the identification of the user account
     */
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Gets the username of the user account.
     *
     * @return the username of the user account
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of the user account.
     *
     * @return the password of the user account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the total income of the user account.
     *
     * @return the total income of the user account
     */
    public float getTotalIncome() {
        return this.totalIncome;
    }

    /**
     * Gets the total outflow of the user account.
     *
     * @return the total outflow of the user account
     */
    public float getTotalOutflow() {
        return this.totalOutflow;
    }

    /**
     * Gets the total balance of the user account.
     *
     * @return the total balance of the user account
     */
    public float getTotalBalance() {
        return this.totalBalance;
    }

    /**
     * Gets the list of transactions of the user account.
     *
     * @return an ArrayList containing the transactions of the user account
     */
    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    /**
     * Gets the last login date of the account.
     *
     * @return the last login date
     */
    public LocalDate getLastLoginDate(){return this.lastLoginDate;}

    /**
     * Sets the username of the user account.
     *
     * @param username the new username of the user account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the identification of the user account.
     *
     * @param identification the new identification of the user account
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the password of the user account.
     *
     * @param password the new password of the user account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the total income of the user account.
     *
     * @param totalIncome the new total income of the user account
     */
    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     * Sets the total outflow of the user account.
     *
     * @param totalOutflow the new total outflow of the user account
     */
    public void setTotalOutflow(float totalOutflow) {
        this.totalOutflow = totalOutflow;
    }

    /**
     * Sets the total balance of the user account.
     *
     * @param totalBalance the new total balance of the user account
     */
    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * Sets the list of transactions of the user account.
     *
     * @param transactions the new list of transactions of the user account
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Sets the last login date.
     *
     * @param lastLoginDate the last login date of the account
     */
    public void setLastLoginDate(LocalDate lastLoginDate){
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * Adds a transaction to the user account.
     * Updates the total income, total outflow, and total balance accordingly.
     *
     * @param transaction the transaction to be added to the user account
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateTotals(transaction);
        transactions.sort(new TransactionComparator());
    }

    /**
     * Updates the total income, total outflow, and total balance based on the given transaction.
     *
     * @param transaction the transaction to update the totals with
     */
    private void updateTotals(Transaction transaction) {
        float amount = transaction.getAmount();
        if (amount > 0) {
            totalIncome += amount;
        } else {
            totalOutflow += amount;
        }
        totalBalance += amount;
    }

}

