package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoInputData;

/**
 * Data class for inputting information into the second homepage view use case specifically for shared accounts.
 * <p>
 * This class extends {@link HomepageTwoInputData} and is used to pass the necessary input data, specifically the ID,
 * for processing the homepage use case related to shared accounts.
 * </p>
 *
 * @see HomepageTwoInputData
 * @see SharedAccountHomepageTwoInputBoundary
 *
 * @author Eric
 */
public class SharedAccountHomepageTwoInputData extends HomepageTwoInputData {

    /**
     * Constructs a new {@code SharedAccountHomepageTwoInputData} with the specified ID.
     *
     * @param id the ID required for processing the shared account homepage use case
     */
    public SharedAccountHomepageTwoInputData(String id) {
        super(id);
    }
}
