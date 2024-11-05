package data_access.authentication.user_account;

import data_access.account.user_account.CSVUserAccountDataAccessObject;
import entity.account.user_account.UserAccount;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 * A CSV-based implementation of data access for user login and logout operations.
 * <p>
 * This class extends {@link CSVUserAccountDataAccessObject} and implements both {@link UserAccountLogoutDataAccessInterface}
 * and {@link UserAccountLoginDataAccessInterface}. It provides methods to check user login status and handle user logout,
 * utilizing a CSV file for data storage.
 * </p>
 * <p>
 * This class also includes login functionality for shared accounts.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
@Repository
public class CSVUserLoginDataAccessObject extends CSVUserAccountDataAccessObject implements UserAccountLoginDataAccessInterface {
    /**
     * Constructs a new instance of {@code CSVUserLoginoutDataAccessObject}.
     * Initializes the CSV-based data access object for user login and logout operations.
     */
    public CSVUserLoginDataAccessObject() {
        super();
    }

    /**
     * Logs in a user by verifying their identification and password against the CSV data.
     * <p>
     * This method reads user data from a CSV file and checks if the provided user account's identification
     * and password match any entry in the file.
     * </p>
     *
     * @param userAccount the {@link UserAccount} object containing the user's identification and password
     * @return {@code true} if the user is successfully logged in; {@code false} otherwise
     */
    @Override
    public boolean login(UserAccount userAccount) {

        try (BufferedReader bin = Files.newBufferedReader(this.accountCsvPath)) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");

                // Check if the ID and password match
                if (values[0].equals(userAccount.getIdentification()) && values[2].equals(userAccount.getPassword())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }

        // If no match is found, return false
        return false;
    }
}
