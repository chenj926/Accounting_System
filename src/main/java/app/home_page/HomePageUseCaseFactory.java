package app.home_page;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.HomePageViewModel;
import view.home_page.HomePageView;

/**
 * Factory class for creating the Home Page use case components.
 * <p>
 * This factory class is responsible for creating and assembling the components required
 * for the Home Page use case, including the view and view model.
 * </p>
 * @author Dana
 * @author Zella
 * @author Eric
 */
public class HomePageUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private HomePageUseCaseFactory() {}

    /**
     * Creates and returns the Home Page view.
     * <p>
     * This method creates the Home Page view, sets up listeners for property changes,
     * and manages view transitions based on user interactions.
     * </p>
     *
     * @param viewManagerModel  the view manager model used to manage view transitions
     * @param homePageViewModel the view model to update the Home Page state
     * @return the assembled Home Page view
     */
    public static HomePageView create(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel) {
        HomePageView homePageView = new HomePageView(homePageViewModel, viewManagerModel);

        // Listen for property changes to handle view transitions
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("sign up"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("shared account log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("shared account sign up"));

        return homePageView;
    }
}

