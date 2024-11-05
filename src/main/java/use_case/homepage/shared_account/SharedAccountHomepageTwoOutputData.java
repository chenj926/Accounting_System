package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoOutputData;

/**
 * Data class for outputting information on the second homepage view specifically for shared accounts.
 * <p>
 * This class extends {@link HomepageTwoOutputData} and is used to pass the output data required
 * for presenting the homepage view related to shared accounts.
 * </p>
 *
 * @see HomepageTwoOutputData
 * @see SharedAccountHomepageTwoOutputBoundary
 *
 * @author Eric
 */
public class SharedAccountHomepageTwoOutputData extends HomepageTwoOutputData {
    /**
     * Constructs a new {@code SharedAccountHomepageTwoOutputData} with the specified basic user information.
     *
     * @param basicUserInfo an array of strings representing the basic user information
     */
    public SharedAccountHomepageTwoOutputData(String[] basicUserInfo) {
        super(basicUserInfo);
    }
}
