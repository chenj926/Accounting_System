package interface_adaptors.homepage;

import use_case.homepage.HomepageTwoInputBoundary;
import use_case.homepage.HomepageTwoInputData;

/**
 * The {@code HomepageTwoController} class is an abstract controller that serves as a bridge
 * between the user interface and the business logic for the homepage view. It handles the
 * communication between the input boundary and the view model, ensuring that the data flow
 * follows Clean Architecture principles.
 *
 * <p>This controller is responsible for executing operations related to the homepage, such as
 * fetching and updating user information. Concrete implementations of this class will define
 * the specific behavior for creating input data and managing different types of homepage views.</p>
 *
 * @param <IB> the type of input boundary that this controller interacts with
 * @param <V>  the type of view model that this controller updates
 * @param <I>  the type of input data that this controller processes
 * @param <S>  the type of state that this controller manages in the view model
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public abstract class HomepageTwoController<
        IB extends HomepageTwoInputBoundary<I>,
        V extends HomepageTwoViewModel<S>,
        I extends HomepageTwoInputData,
        S extends HomepageTwoState> {
    /**
     * The input boundary that this controller interacts with to perform business logic operations.
     */
    protected final IB inputBoundary;
    /**
     * The view model that this controller updates with the results of the business logic operations.
     */
    protected final V viewModel;

    /**
     * Constructs a {@code HomepageTwoController} with the specified input boundary and view model.
     *
     * @param inputBoundary the input boundary for executing use case operations
     * @param viewModel     the view model for updating the view with new data
     */
    protected HomepageTwoController(IB inputBoundary, V viewModel) {
        this.inputBoundary = inputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Executes the business logic operation by creating the appropriate input data and passing it
     * to the input boundary. This method is typically called by the user interface to trigger
     * an update or fetch operation.
     *
     * @param id the identifier used to create the input data and execute the operation
     */
    public void execute(String id) {
        I inputData = this.createInputData(id);
        this.inputBoundary.execute(inputData);
    }

    /**
     * Creates the input data object that will be passed to the input boundary for processing.
     * Concrete implementations of this class must define how the input data is created based on
     * the provided identifier.
     *
     * @param id the identifier used to create the input data
     * @return the input data object to be processed by the input boundary
     */
    protected abstract I createInputData(String id);
}
