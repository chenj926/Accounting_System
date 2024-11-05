package use_case.homepage;

/**
 * Data class for inputting information into the second homepage view use case.
 * <p>
 * This class stores the necessary input data, specifically the ID, for processing the homepage use case.
 * It provides methods to get this information.
 * </p>
 *
 * @see HomepageTwoInputBoundary
 * @see HomepageTwoInteractor
 *
 * @author Eric
 */
public class HomepageTwoInputData {
    protected String id;

    /**
     * Constructs a new {@code HomepageTwoInputData} with the specified ID.
     *
     * @param id the ID required for processing the homepage use case
     */
    public HomepageTwoInputData(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID associated with this input data.
     *
     * @return the ID as a string
     */
    public String getId() {
        return this.id;
    }
}
