package interface_adaptors.homepage.shared_account;

import interface_adaptors.homepage.HomepageTwoViewModel;

/**
 * The {@code SharedAccountHomepageTwoViewModel} class represents the view model for the shared account homepage view.
 * It manages the state and labels specific to the shared account homepage and is responsible for notifying
 * listeners of any state changes.
 *
 * <p>This class extends {@link HomepageTwoViewModel} with a state specific to shared accounts,
 * following the principles of Clean Architecture.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountHomepageTwoViewModel extends HomepageTwoViewModel<SharedAccountHomepageTwoState> {
    private final String TITLE_LABEL = "Shared Account";

    /**
     * Constructs a {@code SharedAccountHomepageTwoViewModel} object with the view name set to "Shared Account Homepage Two".
     */
    public SharedAccountHomepageTwoViewModel() {
        super("Shared Account Homepage Two");
        this.state = new SharedAccountHomepageTwoState();
    }

    /**
     * Gets the title label for the shared account homepage.
     *
     * @return the title label
     */
    public String getTITLE_LABEL() {
        return this.TITLE_LABEL;
    }

    /**
     * Gets the current state of the shared account homepage.
     *
     * @return the current state
     */
    @Override
    public SharedAccountHomepageTwoState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the shared account homepage and updates the basic user information.
     *
     * @param state the new state
     */
    @Override
    public void setState(SharedAccountHomepageTwoState state) {
        this.state = state;
        this.basicUserInfo = state.getBasicUserInfo();
    }

    /**
     * Resets the state to a new default state.
     */
    @Override
    public void resetState() {
        SharedAccountHomepageTwoState newState = new SharedAccountHomepageTwoState();
        setState(newState);
    }

}
