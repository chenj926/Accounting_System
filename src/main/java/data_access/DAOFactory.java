package data_access;

import data_access.account.shared_account.CSVSharedAccountDataAccessObject;
import data_access.account.shared_account.InMemorySharedAccountDataAccessObject;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.account.user_account.CSVUserAccountDataAccessObject;
import data_access.account.user_account.InMemoryUserAccountDataAccessObject;
import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.shared_account.CSVSharedAccountLoginDataAccessObject;
import data_access.authentication.shared_account.InMemorySharedAccountLoginDataAccessObject;
import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import data_access.authentication.user_account.CSVUserLoginDataAccessObject;
import data_access.authentication.user_account.InMemoryLoginoutDataAccessObject;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import data_access.transaction.*;

/**
 * Factory class to provide access to different types of data access objects (DAOs).
 * <p>
 * This class supports both in-memory and CSV-based implementations of DAOs. It is responsible for creating
 * and returning the appropriate DAO instance based on the specified configuration.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class DAOFactory {
    private static CSVUserAccountDataAccessObject csvUserAccountDAO;
    private static CSVSharedAccountDataAccessObject csvSharedAccountDAO;

    private static CSVUserLoginDataAccessObject csvUserLoginDAO;
    private static CSVSharedAccountLoginDataAccessObject csvSharedAccountUserLoginDAO;

    private static InMemoryUserAccountDataAccessObject inMemoryUserAccountDAO;
    private static InMemorySharedAccountDataAccessObject inMemorySharedAccountDAO;

    private static InMemoryLoginoutDataAccessObject inMemoryLoginoutDAO;
    private static InMemorySharedAccountLoginDataAccessObject inMemorySharedAccountLoginDataAccessObject;

    private static InMemoryOneTimeDataAccessObject inMemoryOneTimeDataAccessObject;
    private static InMemoryPeriodicDataAccessObject inMemoryPeriodicDataAccessObject;

    private static boolean useInMemory = false; // Flag to determine which DAO to use

    /**
     * Sets the flag to determine whether to use in-memory DAOs or CSV-based DAOs.
     *
     * @param useInMemory {@code true} to use in-memory DAOs; {@code false} to use CSV-based DAOs
     */
    public static void setUseInMemory(boolean useInMemory) {
        DAOFactory.useInMemory = useInMemory;
    }

    /**
     * Returns a singleton instance of {@link UserSignupDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryUserAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserSignupDataAccessInterface} instance
     */
    public static synchronized UserSignupDataAccessInterface getUserSignupDataAccessObject() {
        if (useInMemory) {
            if (inMemoryUserAccountDAO == null) {
                inMemoryUserAccountDAO = new InMemoryUserAccountDataAccessObject();
            }
            return inMemoryUserAccountDAO;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountSignupDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemorySharedAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVSharedAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountSignupDataAccessInterface} instance
     */
    public static synchronized SharedAccountSignupDataAccessInterface getSharedAccountSignupDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountDAO == null) {
                inMemorySharedAccountDAO = new InMemorySharedAccountDataAccessObject();
            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemorySharedAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVSharedAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountDataAccessInterface} instance
     */
    public static synchronized SharedAccountDataAccessInterface getShareAccountDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountDAO == null) {
                inMemorySharedAccountDAO = new InMemorySharedAccountDataAccessObject();
            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryUserAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance
     */
    public static synchronized UserAccountDataAccessInterface getUserAccountDataAccessObject() {
        if (useInMemory) {
            if (inMemoryUserAccountDAO == null) {
                inMemoryUserAccountDAO = new InMemoryUserAccountDataAccessObject();
            }
            return inMemoryUserAccountDAO;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountLoginDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryLoginoutDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserLoginDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountLoginDataAccessInterface} instance
     */
    public static synchronized UserAccountLoginDataAccessInterface getLoginDataAccessObject() {
        if (useInMemory) {
            if (inMemoryLoginoutDAO == null) {
                inMemoryLoginoutDAO = new InMemoryLoginoutDataAccessObject();
            }
            return inMemoryLoginoutDAO;
        } else {
            if (csvUserLoginDAO == null) {
                csvUserLoginDAO = new CSVUserLoginDataAccessObject();
            }
            return csvUserLoginDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountLoginDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemorySharedAccountLoginDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVSharedAccountLoginDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountLoginDataAccessInterface} instance
     */
    public static synchronized SharedAccountLoginDataAccessInterface getSharedAccountLoginDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountLoginDataAccessObject == null) {
                inMemorySharedAccountLoginDataAccessObject = new InMemorySharedAccountLoginDataAccessObject();
            }
            return inMemorySharedAccountLoginDataAccessObject;
        } else {
            if (csvSharedAccountUserLoginDAO == null) {
                csvSharedAccountUserLoginDAO = new CSVSharedAccountLoginDataAccessObject();
            }
            return csvSharedAccountUserLoginDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for one-time transactions.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryOneTimeDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance
     */
    public static synchronized UserAccountDataAccessInterface getOneTimeTransactionDAO() {
        if (useInMemory) {
            if (inMemoryOneTimeDataAccessObject == null) {
                inMemoryOneTimeDataAccessObject = new InMemoryOneTimeDataAccessObject();
            }
            return inMemoryOneTimeDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for periodic transactions.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryPeriodicDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance
     */
    public static synchronized UserAccountDataAccessInterface getPeriodicTransactionDAO() {
        if (useInMemory) {
            if (inMemoryPeriodicDataAccessObject == null) {
                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
            }
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for generating financial reports.
     * <p>
     * If {@code useInMemory} is {@code true}, returns the appropriate in-memory DAO.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance for financial reports
     */
    public static synchronized UserAccountDataAccessInterface getFinancialReportDAO() {
        if (useInMemory) {
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountDataAccessInterface} for generating shared account financial reports.
     * <p>
     * If {@code useInMemory} is {@code true}, returns the appropriate in-memory DAO.
     * Otherwise, returns an instance of {@link CSVSharedAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountDataAccessInterface} instance for shared account financial reports
     */
    public static synchronized SharedAccountDataAccessInterface getSharedAccountFinancialReportDAO() {
        if (useInMemory) {
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for the homepage view (option two).
     * <p>
     * If {@code useInMemory} is {@code true}, returns the appropriate in-memory DAO.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance for the homepage view (option two)
     */
    public static synchronized UserAccountDataAccessInterface getHomepageTwoDAO() {
        if (useInMemory) {
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountDataAccessInterface} for the shared account homepage view (option two).
     * <p>
     * If {@code useInMemory} is {@code true}, returns the appropriate in-memory DAO.
     * Otherwise, returns an instance of {@link CSVSharedAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountDataAccessInterface} instance for the shared account homepage view (option two)
     */
    public static synchronized SharedAccountDataAccessInterface getSharedAccountHomepageTwoDAO() {
        if (useInMemory) {
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }
}
