package interface_adaptors.homepage.shared_account;

import interface_adaptors.homepage.HomepageTwoController;
import use_case.homepage.shared_account.SharedAccountHomepageTwoInputBoundary;
import use_case.homepage.shared_account.SharedAccountHomepageTwoInputData;

/**
 * The {@code SharedAccountHomepageTwoController} class is responsible for handling user input and interactions
 * for the shared account homepage view. It extends {@link HomepageTwoController} and connects the input boundary
 * with the view model to manage the flow of data and operations specific to the shared account homepage.
 *
 * <p>This class follows the Clean Architecture principles, ensuring that the controller logic is separated from
 * the view and use case logic. It facilitates the creation of input data objects that are used to trigger
 * the appropriate use case operations.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountHomepageTwoController extends HomepageTwoController<
        SharedAccountHomepageTwoInputBoundary,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoInputData,
        SharedAccountHomepageTwoState> {

    /**
     * Constructs a {@code SharedAccountHomepageTwoController} with the specified input boundary and view model.
     *
     * @param inputBoundary the input boundary that handles the use case logic for the shared account homepage
     * @param viewModel the view model that manages the state and data for the shared account homepage view
     */
    public SharedAccountHomepageTwoController(SharedAccountHomepageTwoInputBoundary inputBoundary,
                                              SharedAccountHomepageTwoViewModel viewModel) {
        super(inputBoundary, viewModel);
    }

    /**
     * Creates and returns an instance of {@link SharedAccountHomepageTwoInputData} using the provided account ID.
     *
     * @param id the account ID used to create the input data
     * @return a {@code SharedAccountHomepageTwoInputData} object containing the input data
     */
    @Override
    protected SharedAccountHomepageTwoInputData createInputData(String id) {
        return new SharedAccountHomepageTwoInputData(id);
    }
}
