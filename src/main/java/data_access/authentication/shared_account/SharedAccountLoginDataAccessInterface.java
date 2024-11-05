    package data_access.authentication.shared_account;

    import entity.account.shared_account.SharedAccount;

    /**
     * Interface for data access operations related to user login functionality.
     * <p>
     * This interface provides methods to check if a user account exists, retrieve a user account by its identification,
     * and perform login operations.
     * </p>
     *
     * @author Dana
     * @author Eric
     */
    public interface SharedAccountLoginDataAccessInterface{

        /**
         * Checks if a shared account exists for the given identification.
         *
         * @param identification the unique identifier for the shared account
         * @return {@code true} if a shared account exists with the specified identification; {@code false} otherwise
         */
        boolean existById(String identification);

        /**
         * Retrieves a shared account by its unique identification.
         *
         * @param identification the unique identifier for the shared account
         * @return the SharedAccount associated with the specified identification
         */
        SharedAccount getById(String identification);

        /**
         * Performs login operations for the specified shared account.
         *
         * @param sharedAccount the SharedAccount to be logged in
         * @return {@code true} if the login is successful; {@code false} otherwise
         */
        boolean login(SharedAccount sharedAccount);
    }
