package use_case.homepage;

import data_access.account.AccountDataAccessInterface;

/**
 * Abstract interactor class for handling the logic of the second homepage view use case.
 * <p>
 * This class defines the core structure for interacting with account data and preparing the output view.
 * It follows the input/output boundary pattern and is intended to be extended by specific implementations
 * for different types of accounts or homepage views.
 * </p>
 *
 * @param <DAO> the type of the data access object used to retrieve account data
 * @param <PRE> the type of the output boundary for preparing the view
 * @param <I> the type of the input data required for the homepage use case
 * @param <O> the type of the output data for the homepage view
 *
 * @see HomepageTwoInputBoundary
 * @see HomepageTwoOutputBoundary
 * @see HomepageTwoOutputData
 *
 * @author Eric
 */
public abstract class HomepageTwoInteractor<
        DAO extends AccountDataAccessInterface,
        PRE extends HomepageTwoOutputBoundary<O>,
        I extends HomepageTwoInputData,
        O extends HomepageTwoOutputData> implements HomepageTwoInputBoundary<I> {

    protected final DAO userDataAccessObject;
    protected final PRE presenter;

    /**
     * Constructs a new {@code HomepageTwoInteractor}.
     * <p>
     * This constructor initializes the interactor with the necessary components for accessing account data
     * and preparing the output view.
     * </p>
     *
     * @param userDataAccessObject the data access object used to retrieve account data
     * @param presenter the presenter responsible for preparing the output view
     */
    public HomepageTwoInteractor(DAO userDataAccessObject,
                                            PRE presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    /**
     * Executes the homepage use case logic with the provided input data.
     * <p>
     * This method is abstract and must be implemented by subclasses to define how the input data is processed.
     * </p>
     *
     * @param inputData the input data required for the homepage use case
     */
    @Override
    public abstract void execute(I inputData);
}
