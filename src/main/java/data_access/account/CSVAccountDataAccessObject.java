package data_access.account;

import entity.account.Account;
import entity.account.shared_account.SharedAccount;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;

/**
 * Abstract base class for CSV-based data access objects (DAOs) for accounts.
 * <p>
 * This class provides common functionality for reading, writing, and updating accounts and transactions in CSV files.
 * It is intended to be extended by specific implementations for different types of accounts, such as {@link UserAccount}
 * or {@link SharedAccount}.
 * </p>
 *
 * @param <A> the type of the account
 * @param <O> the type of the one-time transaction output data
 * @param <P> the type of the periodic transaction output data
 *
 * @author Eric
 * @author Jessica
 */
public abstract class CSVAccountDataAccessObject<
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData> {
    protected final Path accountCsvPath;
    protected final Path transactionCsvPath;
    private final String csvHeader;
    private final String transactionHeader;

    /**
     * Constructs a new CSVAccountDataAccessObject with the specified file paths and headers.
     *
     * @param accountCsvFilePath the file path for the accounts CSV file
     * @param transactionCsvFilePath the file path for the transactions CSV file
     * @param csvHeader the header to be used in the accounts CSV file
     * @param transactionHeader the header to be used in the transactions CSV file
     */
    public CSVAccountDataAccessObject(String accountCsvFilePath, String transactionCsvFilePath, String csvHeader, String transactionHeader) {
        String baseDir = System.getProperty("user.dir");
        this.accountCsvPath = Paths.get(baseDir, accountCsvFilePath);
        this.transactionCsvPath = Paths.get(baseDir, transactionCsvFilePath);
        this.csvHeader = csvHeader;
        this.transactionHeader = transactionHeader;

        try {
            initializeCsvFile(accountCsvPath, this.csvHeader);
            initializeTransactionFile(transactionCsvPath, this.transactionHeader);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize CSV file: " + e.getMessage());
        }
    }

    /**
     * Initializes a CSV file with the specified header if it does not already exist.
     *
     * @param csvPath the path to the CSV file
     * @param header the header to write to the CSV file
     * @throws IOException if an I/O error occurs
     */
    protected void initializeCsvFile(Path csvPath, String header) throws IOException {
        Path parentDir = csvPath.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        if (!Files.exists(csvPath)) {
            Files.createFile(csvPath);
            try (BufferedWriter bout = Files.newBufferedWriter(csvPath, StandardOpenOption.APPEND)) {
                bout.write(header);
                bout.newLine();
            }
        } else {
            try (BufferedReader bin = Files.newBufferedReader(csvPath)) {
                String firstLine = bin.readLine();
                if (firstLine == null || !firstLine.equals(header)) {
                    List<String> lines = new ArrayList<>();
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }
                    lines.add(0, header);
                    Files.write(csvPath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }
    }

    /**
     * Initializes the transaction CSV file with the specified header if it does not already exist.
     *
     * @param transactionPath the path to the transaction CSV file
     * @param header the header to write to the transaction CSV file
     * @throws IOException if an I/O error occurs
     */
    protected void initializeTransactionFile(Path transactionPath, String header) throws IOException {
        Path parentDir = transactionPath.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        if (!Files.exists(transactionPath)) {
            Files.createFile(transactionPath);
            try (BufferedWriter bout = Files.newBufferedWriter(transactionPath, StandardOpenOption.APPEND)) {
                bout.write(header);
                bout.newLine();
            }
        } else {
            try (BufferedReader bin = Files.newBufferedReader(transactionPath)) {
                String firstLine = bin.readLine();
                if (firstLine == null || !firstLine.equals(header)) {
                    List<String> lines = new ArrayList<>();
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }
                    lines.add(0, header);
                    Files.write(transactionPath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }
    }

    /**
     * Saves a transaction (either one-time or periodic) to the transactions CSV file.
     *
     * @param oneTimeOutputData the one-time transaction data to be saved
     * @param periodicOutputData the periodic transaction data to be saved
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     */
    public void saveTransaction(O oneTimeOutputData, P periodicOutputData, boolean isPeriodic) {
        if (!isPeriodic) {
            // create csv line with the user info
            String userInfo = getTransactionInfo(oneTimeOutputData,
                    null, false);
            // if csv not created, create it
            confirmCsvExistence(transactionCsvPath, userInfo);
        } else{
            // create csv line with the user info
            String userInfo = getTransactionInfo(null, periodicOutputData, true);
            // if csv not created, create it
            confirmCsvExistence(transactionCsvPath, userInfo);

        }
    }

    /**
     * Confirms the existence of the CSV file and writes the transaction data.
     *
     * @param transactionCsvPath the path to the transaction CSV file
     * @param userInfo the transaction data to be written
     */
    protected void confirmCsvExistence(Path transactionCsvPath, String userInfo) {
        try {
            Path parentDir = transactionCsvPath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (!Files.exists(transactionCsvPath)) {
                Files.createFile(transactionCsvPath);
            }
            // record the info
            try (BufferedWriter bout = Files.newBufferedWriter(transactionCsvPath, StandardOpenOption.APPEND)) {
                bout.write(userInfo);
                bout.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    /**
     * Generates a CSV-formatted string containing the information of a periodic transaction.
     *
     * @param periodicOutputData the periodic transaction data
     * @return a CSV-formatted string representing the periodic transaction
     */
    protected String getPeriodicTransactionInfo(P periodicOutputData) {
        String id = periodicOutputData.getId();
        float amount = periodicOutputData.getTransactionAmount();
        String startDate = valueOf(periodicOutputData.getTransactionStartDate());
        String endDate = valueOf(periodicOutputData.getTransactionEndDate());
        String date = valueOf(periodicOutputData.getTransactionDate());
        String description = periodicOutputData.getTransactionDescription();
        String period = periodicOutputData.getTransactionPeriod();
        String category = periodicOutputData.getTransactionCategory();
        return String.format("%s,%.2f,%s,%s,%s,%s,%s,%s", id, amount, date, description, category, startDate, period, endDate);
    }

    /**
     * Generates a CSV-formatted string containing the information of a one-time transaction.
     *
     * @param oneTimeOutputData the one-time transaction data
     * @return a CSV-formatted string representing the one-time transaction
     */
    protected String getOneTimeTransactionInfo(O oneTimeOutputData) {
        String id = oneTimeOutputData.getId();
        System.out.println("output id"+id);
        float amount = oneTimeOutputData.getTransactionAmount();
        String date = valueOf(oneTimeOutputData.getTransactionDate());
        String description = oneTimeOutputData.getTransactionDescription();
        String category = oneTimeOutputData.getTransactionCategory();
        return String.format("%s,%.2f,%s,%s,%s", id, amount, date, description, category);
    }

    /**
     * Generates a CSV-formatted string containing the information of a transaction.
     * <p>
     * This method returns a CSV-formatted string based on whether the transaction is periodic or one-time.
     * </p>
     *
     * @param oneTimeOutputData the one-time transaction data
     * @param periodicOutputData the periodic transaction data
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     * @return a CSV-formatted string representing the transaction
     */
    protected String getTransactionInfo(O oneTimeOutputData,
                                        P periodicOutputData,
                                        boolean isPeriodic) {
        if (!isPeriodic) {
            return getOneTimeTransactionInfo(oneTimeOutputData);
        } else {
            return getPeriodicTransactionInfo(periodicOutputData);
        }
    }


    /**
     * Updates an existing account in the CSV file.
     * <p>
     * This method reads the existing accounts from the CSV file, updates the account,
     * and writes all accounts back to the file.
     * </p>
     *
     * @param account the account to be updated
     */
    public void update(A account) {
        String identification = account.getIdentification();
        List<String> lines = new ArrayList<>();
        String updatedLine = null;
        try (BufferedReader bin = Files.newBufferedReader(accountCsvPath)) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    // user info

                    String password = account.getPassword();
                    float income = account.getTotalIncome();
                    float outflow = account.getTotalOutflow();
                    float balance = account.getTotalBalance();
                    LocalDate lastLoginDate = account.getLastLoginDate();
                    String lastLoginDateString = valueOf(lastLoginDate);

                    if (account instanceof SharedAccount) {
                        Set<String> userIds = ((SharedAccount) account).getSharedUserIdentifications();
                        String stringUserIds = String.join(";", userIds);;

                        updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, stringUserIds, password,
                                income, outflow, balance, lastLoginDateString);

                    }else if (account instanceof UserAccount){
                        String username = ((UserAccount)account).getUsername();
                        Set<String> sharedIds = ((UserAccount)account).getSharedAccounts();
                        System.out.println(sharedIds);
                        String stringSharedIds = String.join(";", sharedIds);
                        System.out.println(stringSharedIds);
                        updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s,%s", id, username, password,
                                income, outflow, balance, lastLoginDateString, stringSharedIds);
                    }

                    lines.add(updatedLine);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // updateLine is updated, pass in all the info back
        if (updatedLine != null) {
            // open while csv, delete every thing
            try (BufferedWriter writer = Files.newBufferedWriter(this.accountCsvPath,
                    StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Checks if a user account exists by its identification.
     *
     * @param identification the unique identifier for the user account
     * @return {@code true} if the account exists, {@code false} otherwise
     */
    public abstract boolean existById(String identification);

    /**
     * Saves a new user account to the CSV file.
     *
     * @param account the user account to be saved
     */
    public abstract void save(A account);

    /**
     * Deletes a user account from the CSV file by its identification.
     *
     * @param identification the unique identifier for the user account to be deleted
     */
    public abstract void deleteById(String identification);

    /**
     * Retrieves a user account by its identification from the CSV file.
     *
     * @param identification the unique identifier for the user account
     * @return the user account with the specified identification
     */
    public abstract A getById(String identification);

    /**
     * Reads transactions associated with a specific user ID from the CSV file.
     *
     * @param identification the unique identifier of the user whose transactions are to be retrieved
     * @return a list of transactions associated with the specified user ID
     */
    public abstract List<Transaction> readTransactions(String identification);

}

