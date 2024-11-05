package app.home_page;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoController;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoPresenter;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;
import use_case.homepage.user_account.UserAccountHomepageTwoInteractor;
import view.home_page.user_account.UserAccountHomepageTwoView;

import javax.swing.*;
import java.io.IOException;

/**
 * The HomepageTwoUseCaseFactory class is responsible for creating and initializing the components required
 * for the "Homepage Two" functionality in user accounts. This factory sets up the interactor, presenter,
 * controller, and view for this use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Eric
 */
public class HomepageTwoUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private HomepageTwoUseCaseFactory() {
    }

    /**
     * Creates and returns a {@link UserAccountHomepageTwoView} initialized with the required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param viewModel the view model for the homepage two functionality
     * @return a {@link UserAccountHomepageTwoView} instance if successful, or null if an IOException occurs
     */
    public static UserAccountHomepageTwoView create(ViewManagerModel viewManagerModel,
                                                    UserAccountHomepageTwoViewModel viewModel) {
        try {
            UserAccountHomepageTwoController userAccountHomepageTwoController = createHomepageTwoUseCase(viewManagerModel, viewModel);
            return new UserAccountHomepageTwoView(viewModel, viewManagerModel, userAccountHomepageTwoController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for the "Homepage Two" functionality.
     * This includes creating the data access object, presenter, interactor, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param viewModel the view model for the homepage two functionality
     * @return a {@link UserAccountHomepageTwoController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static UserAccountHomepageTwoController createHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                             UserAccountHomepageTwoViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getHomepageTwoDAO();
        UserAccountHomepageTwoPresenter presenter = new UserAccountHomepageTwoPresenter(viewModel, viewManagerModel);
//        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        UserAccountHomepageTwoInteractor interactor = new UserAccountHomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller that can handle both types of signups
        return new UserAccountHomepageTwoController(interactor, viewModel);
    }

}
