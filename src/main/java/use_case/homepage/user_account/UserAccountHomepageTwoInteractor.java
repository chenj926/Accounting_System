package use_case.homepage.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import use_case.homepage.HomepageTwoInteractor;

/**
 * Interactor class for handling the logic of the second homepage view use case specifically for user accounts.
 * <p>
 * This class extends {@link HomepageTwoInteractor} and implements the {@link UserAccountHomepageTwoInputBoundary}
 * interface to process input data, interact with the data access layer, and prepare the output view for user accounts.
 * </p>
 *
 * @see HomepageTwoInteractor
 * @see UserAccountHomepageTwoInputBoundary
 * @see UserAccountHomepageTwoOutputBoundary
 * @see UserAccountHomepageTwoOutputData
 *
 * @author Eric
 */
public class UserAccountHomepageTwoInteractor extends HomepageTwoInteractor<
        UserAccountDataAccessInterface,
        UserAccountHomepageTwoOutputBoundary,
        UserAccountHomepageTwoInputData,
        UserAccountHomepageTwoOutputData> implements UserAccountHomepageTwoInputBoundary {

    /**
     * Constructs a new {@code UserAccountHomepageTwoInteractor}.
     * <p>
     * This constructor initializes the interactor with the necessary components for accessing user account data
     * and preparing the output view.
     * </p>
     *
     * @param userDataAccessObject the data access object used to retrieve user account data
     * @param presenter the presenter responsible for preparing the output view
     */
    public UserAccountHomepageTwoInteractor(UserAccountDataAccessInterface userDataAccessObject,
                                            UserAccountHomepageTwoOutputBoundary presenter) {
        super(userDataAccessObject, presenter);
    }

    /**
     * Executes the homepage use case logic for user accounts with the provided input data.
     * <p>
     * This method retrieves the user account based on the provided ID, gathers relevant user information,
     * and prepares the output data for presentation.
     * </p>
     *
     * @param inputData the input data required for the user account homepage use case
     */
    @Override
    public void execute(UserAccountHomepageTwoInputData inputData) {
        String id = inputData.getId();
        UserAccount user = this.userDataAccessObject.getById(id);
        String username = user.getUsername();
        String totalIncome = String.valueOf(user.getTotalIncome());
        String totalOutflow = String.valueOf(user.getTotalOutflow());
        String totalBalance = String.valueOf(user.getTotalBalance());
        String sharedIds = user.getSharedAccounts().toString();

        String[] basicUserInfo = {username, totalIncome, totalOutflow, totalBalance, sharedIds};
        UserAccountHomepageTwoOutputData outputData = new UserAccountHomepageTwoOutputData(basicUserInfo);
        this.presenter.prepareSuccessView(outputData);
    }
}
