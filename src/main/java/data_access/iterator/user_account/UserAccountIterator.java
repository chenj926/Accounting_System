package data_access.iterator.user_account;

import data_access.iterator.Iterator;
import entity.account.user_account.UserAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * UserAccountIterator provides a way to iterate over user accounts stored in a CSV file.
 * Implements the Iterator interface for UserAccount objects.
 *
 * @author Jessica
 */
public class UserAccountIterator implements Iterator<UserAccount>, AutoCloseable {
    private BufferedReader reader;
    private String currentLine;

    /**
     * Constructs a UserAccountIterator to iterate over the specified CSV file.
     *
     * @param userCsvPath the path to the user accounts CSV file
     * @throws IOException if an I/O error occurs
     */
    public UserAccountIterator(Path userCsvPath) throws IOException {
        this.reader = Files.newBufferedReader(userCsvPath);
        this.reader.readLine(); // Skip header line
        this.currentLine = reader.readLine();
    }

    /**
     * Checks if there are more user accounts to iterate over.
     *
     * @return true if there are more user accounts, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentLine != null && !currentLine.isEmpty();
    }

    /**
     * Returns the next user account in the iteration.
     *
     * @return the next UserAccount object
     * @throws NoSuchElementException if there are no more elements
     */
    @Override
    public UserAccount next() {
        if (currentLine == null) {
            throw new NoSuchElementException();
        }

        String[] values = currentLine.split(",");
        UserAccount userAccount = new UserAccount(values[1], values[2], values[0],
                Float.parseFloat(values[3]), Float.parseFloat(values[4]),
                Float.parseFloat(values[5]));

        // Handle "null" or empty last login date
        if (values.length >= 7 && values[6] != null && !values[6].isEmpty() && !"null".equals(values[6])) {
            try {
                userAccount.setLastLoginDate(LocalDate.parse(values[6]));
            } catch (DateTimeParseException e) {
                // Handle invalid date parsing if necessary, fall back to current date or other default handling
                userAccount.setLastLoginDate(LocalDate.now()); // Default fallback
            }
        } else {
            userAccount.setLastLoginDate(null); // Set to null if the value is "null" or empty
        }

        // If user has 1+ shared accounts
        if (values.length >= 8 && values[7] != null && !values[7].isEmpty()) {
            String[] sharedIds = values[7].split(";");
            for (String sharedId : sharedIds) {
                userAccount.addSharedAccount(sharedId);
            }
        }

        try {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            currentLine = null;
        }

        return userAccount;
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

