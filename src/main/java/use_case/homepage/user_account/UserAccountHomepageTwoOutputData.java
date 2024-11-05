package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoOutputData;

/**
 * Data class for outputting information on the second homepage view specifically for user accounts.
 * <p>
 * This class extends {@link HomepageTwoOutputData} and is used to pass the output data required
 * for presenting the homepage view related to user accounts.
 * </p>
 *
 * @see HomepageTwoOutputData
 * @see UserAccountHomepageTwoOutputBoundary
 *
 * @author Eric
 */
public class UserAccountHomepageTwoOutputData extends HomepageTwoOutputData {

    /**
     * Constructs a new {@code UserAccountHomepageTwoOutputData} with the specified basic user information.
     *
     * @param basicUserInfo an array of strings representing the basic user information
     */
    public UserAccountHomepageTwoOutputData(String[] basicUserInfo) {
        super(basicUserInfo);
    }
}
