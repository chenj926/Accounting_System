package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.AccountFactory;
import entity.Transaction;
import entity.UserAccount;

// import java.time.LocalDateTime;

public class OneTimeTransactionInteractor implements OneTimeTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final OneTimeTransactionOutputBoundary presenter;
    final AccountFactory accountFactory;

    public OneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                        OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                        AccountFactory accountFactory) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = oneTimeTransactionOutputBoundary;
        this.accountFactory = accountFactory;
    }

    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        String identification = oneTimeTransactionInputData.getIdentification();
        float transactionAmount = oneTimeTransactionInputData.getTransactionAmount();
        String transactionDate = oneTimeTransactionInputData.getTransactionDate();
        String transactionDescription = oneTimeTransactionInputData.getTransactionDescription();
        String transactionCategory = oneTimeTransactionInputData.getTransactionCategory();
        String transactionNotes = oneTimeTransactionInputData.getTransactionNotes();
        // Assuming user is already logged in
        boolean userExists = userDataAccessObject.existById(identification);

//        if (!userExists) {
//            // Handle case where user does not exist
//            presenter.UserNotFound(); // Example method in presenter to handle this case
//            return;
//        }


        // Fetch user account based on identification
        UserAccount userAccount = userDataAccessObject.getById(identification);
        // Record transaction in user account
        userAccount.getIdentification();
        userAccount.getUsername();
        userAccount.getTotalBalance();
        userAccount.getTotalOutflow();
        userAccount.getTotalIncome();


        // Update the user account in the data store
        userDataAccessObject.updateBalance(userAccount);
        userDataAccessObject.updateOutflow(userAccount);
        userDataAccessObject.updateInflow(userAccount);
        userDataAccessObject.updateId(userAccount);

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(
                transactionAmount, transactionDate, transactionDescription, transactionCategory, transactionNotes);
        presenter.prepareSuccessView(outputData);
    }
}