package view.home_page.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoController;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoState;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code UserAccountHomepageTwoView} class represents the graphical user interface for the "Homepage Two"
 * section of the user account module in the application. It extends {@link JFrame} and implements
 * {@link PropertyChangeListener} to respond to changes in the {@link UserAccountHomepageTwoViewModel}.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation of
 * concerns. It interacts with the {@link UserAccountHomepageTwoViewModel} to reflect the state of the application
 * and with the {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class UserAccountHomepageTwoView extends JFrame implements PropertyChangeListener {

    private final UserAccountHomepageTwoViewModel viewModel;
    private final UserAccountHomepageTwoPanel userAccountHomepageTwoPanel;
    private UserAccountHomepageTwoController controller;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a {@code UserAccountHomepageTwoView} with the specified view model, view manager, and controller.
     * This constructor sets up the "Homepage Two" UI components and initializes the window.
     *
     * @param viewModel   the view model for the homepage view, providing data and state information
     * @param viewManager the view manager for handling view transitions within the application
     * @param userAccountHomepageTwoController the controller responsible for handling user actions and executing
     *                                         business logic related to "Homepage Two"
     */
    public UserAccountHomepageTwoView(UserAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager,
                                      UserAccountHomepageTwoController userAccountHomepageTwoController) {
        super("Homepage Two");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewManager = viewManager;
        this.controller = userAccountHomepageTwoController;

        this.userAccountHomepageTwoPanel = new UserAccountHomepageTwoPanel(this.viewModel, this.viewManager, this.controller);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the {@link UserAccountHomepageTwoPanel} to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(userAccountHomepageTwoPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events from the view model. Currently, this method does not perform any actions but
     * can be customized to handle changes in the view model's properties.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
        UserAccountHomepageTwoState state = (UserAccountHomepageTwoState) evt.getNewValue();
    }

    /**
     * Overrides the setVisible method to execute the controller's logic when the view becomes visible.
     * This ensures that the necessary data is loaded and the UI is updated accordingly.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            String id = this.viewManager.getUserId();
            this.controller.execute(id);
        }
    }

}
