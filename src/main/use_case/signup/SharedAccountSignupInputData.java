package use_case.signup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * SharedAccountSignupInputData is a data structure for storing input data needed for shared account signup.
 * This includes the shared account ID, shared account password, and user identifications for initial users.
 * Users can add additional users dynamically.
 *
 * @version 1.0
 * @since 2024-08-03
 *
 */
public class SharedAccountSignupInputData {
    private String sharedAccountId;
    private String sharedPassword;
//    private String user1Id;
//    private String user2Id;
    private Set<String> userIds;

    /**
     * Constructs a SharedAccountSignupInputData object with the specified initial data.
     *
     * @param sharedAccountId the identification for the shared account
     * @param sharedPassword  the password for accessing the shared account
//     * @param user1Id         the identification for the first user
//     * @param user2Id         the identification for the second user
     */
    public SharedAccountSignupInputData(String sharedAccountId, String sharedPassword, Set<String> userIds) {
        this.sharedAccountId = sharedAccountId;
        this.sharedPassword = sharedPassword;
//        this.user1Id = user1Id;
//        this.user2Id = user2Id;
        this.userIds = userIds;
    }


    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID
     */
    public String getSharedAccountId() {
        return sharedAccountId;
    }

    /**
     * Sets the shared account ID.
     *
     * @param sharedAccountId the new shared account ID
     */
    public void setSharedAccountId(String sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the shared account password.
     *
     * @return the shared account password
     */
    public String getSharedPassword() {
        return sharedPassword;
    }

    /**
     * Sets the shared account password.
     *
     * @param sharedPassword the new shared account password
     */
    public void setSharedPassword(String sharedPassword) {
        this.sharedPassword = sharedPassword;
    }

    /**
     * Gets the ID of the first user.
     *
     * @return the ID of the first user
     */
//    public String getUser1Id() {
//        return user1Id;
//    }

    /**
     * Sets the ID of the first user.
     *
     * @param user1Id the new ID of the first user
     */
//    public void setUser1Id(String user1Id) {
//        this.user1Id = user1Id;
//    }

    /**
     * Gets the ID of the second user.
     *
     * @return the ID of the second user
     */
//    public String getUser2Id() {
//        return user2Id;
//    }

    /**
     * Sets the ID of the second user.
     *
     * @param user2Id the new ID of the second user
     */
//    public void setUser2Id(String user2Id) {
//        this.user2Id = user2Id;
//    }

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

    /**
     * Sets the additional user IDs for the shared account.
     *
     * @param userIds the new set of additional user IDs
     */
    public void setUserIds(Set<String> userIds) {
        this.userIds = userIds;
    }
}


