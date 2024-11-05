package interface_adaptors.signup.shared_account;

import interface_adaptors.signup.SignupState;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code SharedAccountSignupState} class manages the state information
 * during the shared account signup process. It includes fields for managing
 * user IDs associated with the shared account and provides methods to update
 * and retrieve this state information.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture,
 * ensuring that the state of the shared account signup process is maintained
 * and can be easily accessed and manipulated by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen
 * </p>
 */
public class SharedAccountSignupState extends SignupState {
    private Set<String> userIds;

    /**
     * Constructs a {@code SharedAccountSignupState} object with initial empty values.
     * Initializes the set of user IDs to an empty {@code HashSet}.
     */
    public SharedAccountSignupState() {
        super();
        this.userIds = new HashSet<>();
    }

    /**
     * Gets the set of user IDs associated with the shared account.
     *
     * @return the set of user IDs, or an empty set if none are set.
     */
    public Set<String> getUserId() {
        return this.userIds;
    }

    /**
     * Adds a new user ID to the set of user IDs for the shared account.
     *
     * @param userId the user ID to add.
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
     * Sets the additional user IDs for the shared account.
     *
     * @param userIds the new set of additional user IDs
     */
    public void setUserIds(Set<String> userIds) {
        this.userIds = new HashSet<>(userIds);
    }

    /**
     * Clears all user IDs from the state.
     */
    public void clearUserIds() {
        this.userIds.clear();
    }

    /**
     * Checks if all required fields are filled out.
     *
     * @return true if all fields are filled, false otherwise
     */
    public boolean isComplete() {
        return this.successMsg != null;
    }

    /**
     * Resets the state to its initial empty values.
     * This includes clearing all user IDs and resetting other state fields.
     */
    public void reset() {
        super.reset();
        this.userIds.clear();
    }
}

