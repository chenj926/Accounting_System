package interface_adaptors.homepage.user_account;

import interface_adaptors.homepage.HomepageTwoViewModel;

/**
 * The {@code UserAccountHomepageTwoViewModel} class is responsible for managing the state
 * and labels of the second homepage view for user accounts. This class extends
 * {@link HomepageTwoViewModel} and provides additional functionality specific to
 * user accounts, such as managing related shared account IDs.
 *
 * <p>This class notifies listeners of any changes in the state and updates the
 * basic user information accordingly.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class UserAccountHomepageTwoViewModel extends HomepageTwoViewModel<UserAccountHomepageTwoState> {
    // labels
    private final String TITLE_LABEL = "User Account";
    private final String SHAREDIDS_LABEL = "Related Shared Account Ids: ";

    /**
     * Constructs a {@code UserAccountHomepageTwoViewModel} object with the view name set to "Homepage Two".
     * Initializes the state to a new {@link UserAccountHomepageTwoState}.
     */
    public UserAccountHomepageTwoViewModel() {
        super("Homepage Two");
        this.state = new UserAccountHomepageTwoState();
    }

    /**
     * Gets the title label for the homepage view.
     *
     * @return the title label
     */
    public String getTITLE_LABEL() {
        return this.TITLE_LABEL;
    }
    /**
     * Gets the label for the shared account IDs.
     *
     * @return the shared account IDs label
     */
    public String getSHAREDIDS_LABEL() {
        return this.SHAREDIDS_LABEL;
    }

    /**
     * Gets the current state of the homepage.
     *
     * @return the current state
     */
    @Override
    public UserAccountHomepageTwoState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the homepage and updates the basic user information.
     *
     * @param state the new state
     */
    @Override
    public void setState(UserAccountHomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
    }

    /**
     * Resets the state to a new default state.
     */
    @Override
    public void resetState() {
        UserAccountHomepageTwoState newState = new UserAccountHomepageTwoState();
        setState(newState);
    }

}
