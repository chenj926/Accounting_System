package use_case.homepage.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import use_case.homepage.HomepageTwoInteractor;

/**
 * Interactor class for handling the logic of the second homepage view use case specifically for shared accounts.
 * <p>
 * This class extends {@link HomepageTwoInteractor} and implements the {@link SharedAccountHomepageTwoInputBoundary}
 * interface to process input data, interact with the data access layer, and prepare the output view for shared accounts.
 * </p>
 *
 * @see HomepageTwoInteractor
 * @see SharedAccountHomepageTwoInputBoundary
 * @see SharedAccountHomepageTwoOutputBoundary
 * @see SharedAccountHomepageTwoOutputData
 *
 * @author Eric
 */
public class SharedAccountHomepageTwoInteractor extends HomepageTwoInteractor<
        SharedAccountDataAccessInterface,
        SharedAccountHomepageTwoOutputBoundary,
        SharedAccountHomepageTwoInputData,
        SharedAccountHomepageTwoOutputData> implements SharedAccountHomepageTwoInputBoundary{

    /**
     * Constructs a new {@code SharedAccountHomepageTwoInteractor}.
     * <p>
     * This constructor initializes the interactor with the necessary components for accessing shared account data
     * and preparing the output view.
     * </p>
     *
     * @param userDataAccessObject the data access object used to retrieve shared account data
     * @param presenter the presenter responsible for preparing the output view
     */
    public SharedAccountHomepageTwoInteractor(SharedAccountDataAccessInterface userDataAccessObject,
                                              SharedAccountHomepageTwoOutputBoundary presenter) {
        super(userDataAccessObject, presenter);
    }

    /**
     * Executes the homepage use case logic for shared accounts with the provided input data.
     * <p>
     * This method retrieves the shared account based on the provided ID, gathers relevant user information,
     * and prepares the output data for presentation.
     * </p>
     *
     * @param inputData the input data required for the shared account homepage use case
     */
    @Override
    public void execute(SharedAccountHomepageTwoInputData inputData) {
        String id = inputData.getId();
        SharedAccount user = this.userDataAccessObject.getById(id);
        String userIds = user.getIdentification();
        String totalIncome = String.valueOf(user.getTotalIncome());
        String totalOutflow = String.valueOf(user.getTotalOutflow());
        String totalBalance = String.valueOf(user.getTotalBalance());

        String[] basicUserInfo = {userIds, totalIncome, totalOutflow, totalBalance};
        SharedAccountHomepageTwoOutputData outputData = new SharedAccountHomepageTwoOutputData(basicUserInfo);
        this.presenter.prepareSuccessView(outputData);
    }
}
