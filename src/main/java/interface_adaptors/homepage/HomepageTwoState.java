package interface_adaptors.homepage;

/**
 * The {@code HomepageTwoState} class represents the state of the homepage view in the application.
 * It holds essential user information such as total income, total outflow, and total balance,
 * as well as an array of basic user information and any potential error messages.
 *
 * <p>This class is used to maintain and update the state of the homepage view, ensuring that
 * the view can reflect accurate and up-to-date information for the user.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class HomepageTwoState {
    protected String id;
    protected String totalIncome;
    protected String totalOutflow;
    protected String totalBalance;
    protected String[] basicUserInfo;
    protected String err;

    // getters
    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getId() {
        return this.id;
    }
    /**
     * Gets the total income of the user.
     *
     * @return the total income
     */
    public String getTotalIncome() {
        return this.totalIncome;
    }
    /**
     * Gets the total outflow of the user.
     *
     * @return the total outflow
     */
    public String getTotalOutflow() {
        return this.totalOutflow;
    }
    /**
     * Gets the total balance of the user.
     *
     * @return the total balance
     */
    public String getTotalBalance() {
        return this.totalBalance;
    }
    /**
     * Gets the basic user information array.
     *
     * @return an array containing the basic user information
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }
    /**
     * Gets the error message, if any.
     *
     * @return the error message
     */
    public String getErr() {
        return this.err;
    }

    // setters
    /**
     * Sets the user ID.
     *
     * @param id the new user ID
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Sets the total income of the user.
     *
     * @param totalIncome the new total income
     */
    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
    /**
     * Sets the total outflow of the user.
     *
     * @param totalOutflow the new total outflow
     */
    public void setTotalOutflow(String totalOutflow) {
        this.totalOutflow = totalOutflow;
    }
    /**
     * Sets the total balance of the user.
     *
     * @param totalBalance the new total balance
     */
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    /**
     * Sets the basic user information array.
     *
     * @param basicUserInfo the new basic user information array
     */
    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }
    /**
     * Sets the error message.
     *
     * @param err the new error message
     */
    public void setErr(String err) {
        this.err = err;
    }
}
