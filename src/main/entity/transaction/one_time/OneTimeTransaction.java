package entity.transaction.one_time;

import entity.transaction.Transaction;

import java.time.LocalDate;

/**
 * The OneTimeTransaction abstract class implements the Transaction interface.
 * It represents a single, non-recurring transaction with additional details such as category and inflow status.
 *
 * @author Xile
 * @author Chi Fong
 * @author Jessica
 * @author Eric
 */
public abstract class OneTimeTransaction extends Transaction {
//    private String transactionCategory;
    private LocalDate date;

    /**
     * Constructs a OneTimeTransaction object with the specified details.
     *
     * @param identification the identification of the transaction
     * @param amount         the amount of the transaction
     * @param date           the date of the transaction
     * @param description    the description of the transaction
     * @param category       the category of the transaction
     */
    public OneTimeTransaction(String identification, float amount, LocalDate date, String description, String category) {
        this.identification = identification;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.transactionCategory = category;
        this.inflow = (amount>=0);
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
