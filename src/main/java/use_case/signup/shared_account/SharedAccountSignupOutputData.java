package use_case.signup.shared_account;

import use_case.signup.SignupOutputData;

import java.util.Set;

/**
 * The SharedAccountSignupOutputData class represents the output data of a shared account signup operation.
 * It includes specific fields such as shared account ID and the user IDs associated with the shared account.
 *
 * @author Xile
 * @author Dana
 * @author Eric
 */
public class SharedAccountSignupOutputData implements SignupOutputData<Set<String>> {
    private final String sharedAccountId;
    private Set<String> userIds;

    /**
     * Constructs a SharedAccountSignupOutputData object with the specified shared account ID and user IDs.
     *
     * @param sharedAccountId the ID of the shared account
     * @param userIds the set of user IDs associated with the shared account
     */
    public SharedAccountSignupOutputData(String sharedAccountId, Set<String> userIds) {
        this.sharedAccountId = sharedAccountId;
        this.userIds = userIds;
    }

    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID, or null if not applicable
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Gets the set of user IDs associated with the shared account.
     *
     * @return the set of user IDs
     */
    public Set<String> getUserIds() {
        return this.userIds;
    }

}