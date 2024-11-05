package interface_adaptors.homepage.user_account;

import interface_adaptors.homepage.HomepageTwoState;

/**
 * The {@code UserAccountHomepageTwoState} class represents the state of the homepage for a user account.
 * It extends the {@link HomepageTwoState} class by adding a field for the username.
 *
 * <p>This class is used to manage the state related to a user's account homepage,
 * including the username, total income, total outflow, total balance, and other basic user information.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class UserAccountHomepageTwoState extends HomepageTwoState {
    /**
     * The username associated with the user's account.
     */
    private String username;

    // getters
    /**
     * Gets the username associated with the user's account.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    // setters
    /**
     * Sets the username associated with the user's account.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}

