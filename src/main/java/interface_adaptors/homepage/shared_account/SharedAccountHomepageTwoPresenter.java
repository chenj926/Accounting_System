package interface_adaptors.homepage.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoPresenter;
import use_case.homepage.shared_account.SharedAccountHomepageTwoOutputBoundary;
import use_case.homepage.shared_account.SharedAccountHomepageTwoOutputData;

/**
 * The {@code SharedAccountHomepageTwoPresenter} class is responsible for handling the output data
 * from the use case and updating the view model and view manager accordingly for the shared account
 * homepage view. This class implements the {@link SharedAccountHomepageTwoOutputBoundary} interface
 * and extends {@link HomepageTwoPresenter}.
 *
 * <p>This presenter acts as a bridge between the use case layer and the user interface layer, ensuring
 * that the data returned from the use case is properly formatted and passed to the view model. It also
 * manages any state changes and notifies the view of these changes to update the user interface.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountHomepageTwoPresenter extends HomepageTwoPresenter<
        SharedAccountHomepageTwoOutputData,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoState> implements SharedAccountHomepageTwoOutputBoundary {

    /**
     * Constructs a new {@code SharedAccountHomepageTwoPresenter} with the specified view model and view manager.
     *
     * @param viewModel the view model that manages the state and data for the shared account homepage view
     * @param viewManager the view manager that handles view transitions and user interface updates
     */
    public SharedAccountHomepageTwoPresenter(SharedAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager){
        super(viewModel, viewManager);
    }
}
