package use_case.signup.shared_account;

import use_case.signup.SignupInputData;

import java.util.Set;

/**
 * SharedAccountSignupInputData is a data structure for storing input data needed for shared account signup.
 * This includes the shared account ID, shared account password, and user identifications for initial users.
 * Users can add additional users dynamically.
 *
 * @author Xile
 * @author Eric
 */
public class SharedAccountSignupInputData extends SignupInputData {
    private Set<String> userIds;

    /**
     * Constructs a SharedAccountSignupInputData object with the specified initial data.
     *
     * @param sharedAccountId the identification for the shared account
     * @param sharedPassword  the password for accessing the shared account
     * @param userIds         the identifications for the users
     */
    public SharedAccountSignupInputData(String sharedAccountId, String sharedPassword, Set<String> userIds) {
        super(sharedPassword, sharedAccountId);
        this.userIds = userIds;
    }

    /**
     * Adds a new user ID to the set of additional user IDs.
     *
     * @param userId the user ID to add
     */
    public void addUserId(String userId) {
        this.userIds.add(userId);
    }

    /**
     * Removes a user ID from the set of additional user IDs.
     *
     * @param userId the user ID to remove
     */
    public void removeUserId(String userId) {
        this.userIds.remove(userId);
    }

    /**
     * Gets the set of additional user IDs.
     *
     * @return the set of additional user IDs
     */
    public Set<String> getUserIds() {
        return this.userIds;
    }

}


