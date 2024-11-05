package use_case.homepage;

/**
 * Data class for outputting information on the second homepage view.
 * <p>
 * This class stores basic user information in the form of an array of strings and provides methods to get and set this information.
 * It is used as an output data structure for the second homepage use case.
 * </p>
 *
 * @see HomepageTwoOutputBoundary
 * @see HomepageTwoInteractor
 *
 * @author Eric
 */
public class HomepageTwoOutputData {
    protected String[] basicUserInfo;

    /**
     * Constructs a new {@code HomepageTwoOutputData} with the specified basic user information.
     *
     * @param basicUserInfo an array of strings representing the basic user information
     */
    public HomepageTwoOutputData(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

    /**
     * Retrieves the basic user information.
     *
     * @return an array of strings representing the basic user information
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }

    /**
     * Sets the basic user information.
     * <p>
     * This method replaces the existing basic user information with the provided array.
     * </p>
     *
     * @param basicUserInfo an array of strings representing the basic user information
     */
    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }
}
