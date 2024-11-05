package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoInputData;

/**
 * Data class for inputting information into the second homepage view use case specifically for user accounts.
 * <p>
 * This class extends {@link HomepageTwoInputData} and is used to pass the necessary input data, specifically the ID,
 * for processing the homepage use case related to user accounts.
 * </p>
 *
 * @see HomepageTwoInputData
 * @see UserAccountHomepageTwoInputBoundary
 *
 * @author Eric
 */
public class UserAccountHomepageTwoInputData extends HomepageTwoInputData {
    /**
     * Constructs a new {@code UserAccountHomepageTwoInputData} with the specified ID.
     *
     * @param id the ID required for processing the user account homepage use case
     */
    public UserAccountHomepageTwoInputData(String id) {
        super(id);
    }
}
