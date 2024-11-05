package data_access.iterator;

import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * TransactionIterator provides a way to iterate over transactions stored in a CSV file.
 * Implements the Iterator interface for Transaction objects.
 *
 * @author Jessica
 */
public class TransactionIterator implements Iterator<Transaction>, AutoCloseable {
    private BufferedReader reader;
    private String currentLine;

    /**
     * Constructs a TransactionIterator to iterate over the specified CSV file.
     *
     * @param transactionCsvPath the path to the transactions CSV file
     * @throws IOException if an I/O error occurs
     */
    public TransactionIterator(Path transactionCsvPath) throws IOException {
        this.reader = Files.newBufferedReader(transactionCsvPath);
        this.reader.readLine(); // Skip header line
        this.currentLine = reader.readLine();
    }

    /**
     * Checks if there are more transactions to iterate over.
     *
     * @return true if there are more transactions, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentLine != null;
    }

    /**
     * Returns the next transaction in the iteration.
     *
     * @return the next Transaction object
     * @throws NoSuchElementException if there are no more elements
     */
    @Override
    public Transaction next() {
        if (currentLine == null) {
            throw new NoSuchElementException();
        }

        String[] values = currentLine.split(",");
        Transaction transaction = getTransaction(values);

        try {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            currentLine = null;
        }

        return transaction;
    }

    /**
     * Creates a Transaction object from CSV values.
     *
     * @param values the CSV values
     * @return the Transaction object
     */
    private Transaction getTransaction(String[] values) {
        Transaction transaction;
        if (values.length <= 5) {
            String id = values[0];
            float amount = Float.parseFloat(values[1]);
            LocalDate date = LocalDate.parse(values[2]);
            String description = values[3];
            String category = values[4];

            transaction = new OneTimeTransaction(id, amount, date, description, category);
        } else {
            String id = values[0];
            float amount = Float.parseFloat(values[1]);
            LocalDate date = LocalDate.parse(values[2]);
            String description = values[3];
            String category = values[4];
            LocalDate startDate = LocalDate.parse(values[5]);
            String period = values[6];
            LocalDate endDate = LocalDate.parse(values[7]);

            transaction = new PeriodicTransaction(id, amount, startDate, description, endDate, period, category);
            transaction.setDate(date);
        }
        return transaction;
    }

    /**
     * Closes the underlying BufferedReader.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
