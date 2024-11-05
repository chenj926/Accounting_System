package use_case.signup.shared_account;

import data_access.account.user_account.CSVUserAccountDataAccessObject;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.account.AccountFactory;
import use_case.signup.SignupInteractor;

import java.time.LocalDate;
import java.util.Set;

/**
 * The SharedAccountSignupInteractor class handles the signup process for shared accounts.
 * It validates input data, interacts with the data access layer, and uses the presenter to prepare output views.
 * <p>
 * This class assumes that it will receive a SharedAccountSignupInputData type as input.
 * </p>
 *
 * @author Xile
 * @author Eric
 * @author Jessica
 */
public class SharedAccountSignupInteractor extends SignupInteractor<
        SharedAccountSignupDataAccessInterface,
        SharedAccountSignupInputData> implements SharedAccountSignupInputBoundary {
    private final SharedAccountSignupOutputBoundary presenter;
    private final SharedAccountDataAccessInterface sharedDataAccessObject;

    /**
     * Constructs a SharedAccountSignupInteractor object with the specified data access interfaces,
     * output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param sharedDataAccessObject        the data access interface for shared accounts
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SharedAccountSignupInteractor(SharedAccountSignupDataAccessInterface userSignupDataAccessInterface,
                                         SharedAccountDataAccessInterface sharedDataAccessObject,
                                         SharedAccountSignupOutputBoundary signupOutputBoundary,
                                         AccountFactory accountFactory) {
        super(userSignupDataAccessInterface, accountFactory);
        this.sharedDataAccessObject = sharedDataAccessObject;
        this.presenter = signupOutputBoundary;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param sharedSignupData the input data required for the signup process
     */
    @Override
    public void execute(SharedAccountSignupInputData sharedSignupData) {
        Set<String> userIds = sharedSignupData.getUserIds();
        String shareAccountId = sharedSignupData.getId();

        boolean validSharedAccountId = checkIdentification(shareAccountId);
        boolean validSharedPassword = checkPassword(sharedSignupData.getPassword());
        boolean validUserIds = this.checkUserIds(userIds);

        if (!validSharedAccountId || !validSharedPassword || !validUserIds) {
            presenter.prepareFailView("All fields must be filled out!");
            return;
        }

        // Check if the shared account already exists
        if (sharedDataAccessObject.existById(shareAccountId)) {
            presenter.prepareFailView("A shared account with this ID already exists!");
            return;
        }

        // Verify that user accounts exist
        boolean userIdsExist = this.checkUserIdsExist(userIds);
        if (!userIdsExist) {
            presenter.prepareFailView("All user accounts must exist before creating a shared account!");
            return;
        }

        // Create a new shared account
        SharedAccount newSharedAccount = accountFactory.createSharedAccount(
                shareAccountId,
                userIds,
                sharedSignupData.getPassword()
        );
        // update the user account ids into newSharedAccount
        this.addIdToShareAccount(newSharedAccount, userIds);
        newSharedAccount.setLastLoginDate(LocalDate.now());
        // Save the new shared account
        sharedDataAccessObject.save(newSharedAccount);

        // Prepare success view for shared account creation
        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData(
                shareAccountId,
                sharedSignupData.getUserIds());

        presenter.prepareSuccessView(outputData);
    }

    /**
     * Checks if all user IDs in the given set are valid.
     *
     * @param userIds the set of user IDs to check
     * @return true if all user IDs are valid, false otherwise
     */
    private boolean checkUserIds(Set<String> userIds) {
        for (String userId : userIds) {
            if (!this.checkIdentification(userId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all user IDs in the given set exist in the data source.
     *
     * @param userIds the set of user IDs to check
     * @return true if all user IDs exist in the data source, false otherwise
     */
    private boolean checkUserIdsExist(Set<String> userIds) {
        CSVUserAccountDataAccessObject dao = new CSVUserAccountDataAccessObject();
        for (String userId : userIds) {
            if (!dao.existById(userId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds user IDs to the specified shared account.
     *
     * @param sharedAccount the shared account to which user IDs will be added
     * @param userIds the set of user IDs to add
     */
    private void addIdToShareAccount(SharedAccount sharedAccount, Set<String> userIds) {
        for (String userId : userIds) {
            sharedAccount.addUserIdentification(userId);
        }
    }
}

