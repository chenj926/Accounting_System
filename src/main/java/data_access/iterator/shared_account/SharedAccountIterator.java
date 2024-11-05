package data_access.iterator.shared_account;

import data_access.iterator.Iterator;
import entity.account.shared_account.SharedAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * SharedAccountIterator provides a way to iterate over shared accounts stored in a CSV file.
 * Implements the Iterator interface for SharedAccount objects.
 *
 * @author Jessica
 */
public class SharedAccountIterator implements Iterator<SharedAccount>, AutoCloseable {
    private BufferedReader reader;
    private String currentLine;

    /**
     * Constructs a SharedAccountIterator to iterate over the specified CSV file.
     *
     * @param userCsvPath the path to the shared accounts CSV file
     * @throws IOException if an I/O error occurs
     */
    public SharedAccountIterator(Path userCsvPath) throws IOException {
        this.reader = Files.newBufferedReader(userCsvPath);
        this.reader.readLine(); // Skip header line
        this.currentLine = reader.readLine();
    }

    /**
     * Checks if there are more shared accounts to iterate over.
     *
     * @return true if there are more shared accounts, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentLine != null && !currentLine.isEmpty();
    }

    /**
     * Returns the next shared account in the iteration.
     *
     * @return the next SharedAccount object
     * @throws NoSuchElementException if there are no more elements
     */
    @Override
    public SharedAccount next() {
        if (currentLine == null) {
            throw new NoSuchElementException();
        }

        String[] values = currentLine.split(",");
        String id = values[0];
        Set<String> userIds = new HashSet<>();

        // string userIds were separated by ";" instead of ","
        String[] stringUserIds = values[1].split(";");
        userIds.addAll(Arrays.asList(stringUserIds));

        String password = values[2];
        float income = Float.parseFloat(values[3]);
        float outflow = Float.parseFloat(values[4]);
        float balance = Float.parseFloat(values[5]);

        SharedAccount sharedAccount = new SharedAccount(id, userIds, password, income, outflow, balance);

        if (values.length >= 6 && values[6] != null && !values[6].isEmpty() && !values[6].equals("null")) {
            sharedAccount.setLastLoginDate(LocalDate.parse(values[6]));
        } else {
            sharedAccount.setLastLoginDate(LocalDate.now());
        }

        try {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            currentLine = null;
        }

        return sharedAccount;
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
