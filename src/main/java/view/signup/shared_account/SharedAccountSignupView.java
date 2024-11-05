package view.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SharedAccountSignupView} class represents the graphical user interface (GUI) for signing up shared accounts.
 * This class extends {@link JFrame} and is responsible for setting up and managing the shared account signup panel.
 * <p>
 * In the context of Clean Architecture, this class belongs to the view layer, interacting with the {@link SharedAccountSignupViewModel}
 * to display relevant data and manage user inputs. It also interacts with the {@link SharedAccountSignupController} to handle
 * the business logic related to shared account signup.
 * </p>
 *
 * <p><b>Authors:</b> Xile Chen, Eric Chen</p>
 */
public class SharedAccountSignupView extends JFrame{

    private final SharedAccountSignupViewModel sharedViewModel; // Specific view model for shared accounts
    private final SharedAccountSignupPanel sharedAccountSignupPanel;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a {@code SharedAccountSignupView} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the shared account signup view with the provided view model, controller, and view manager,
     * and sets up the user interface components. It also sets the default close operation and the initial size of the window.
     * </p>
     *
     * @param viewModel        the view model for the shared account signup view, providing data and state management
     * @param sharedAccountSignupController the controller for handling the signup actions and business logic
     * @param viewManager      the view manager for managing transitions between different views in the application
     */
    public SharedAccountSignupView(SharedAccountSignupViewModel viewModel,
                                   SharedAccountSignupController sharedAccountSignupController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.sharedViewModel = viewModel;
        this.viewManager = viewManager;
        this.sharedAccountSignupPanel = new SharedAccountSignupPanel(
                sharedViewModel,
                sharedAccountSignupController,
                this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the shared account signup panel to the content pane.
     * <p>
     * This method is responsible for adding the {@link SharedAccountSignupPanel} to the {@link JFrame} content pane
     * using a {@link BorderLayout} layout manager.
     * </p>
     */
    protected void setupUI() {
         this.getContentPane().add(sharedAccountSignupPanel, BorderLayout.CENTER); // Add panel
    }

    /**
     * Overrides the {@code setVisible} method to clear the input fields in the panel when the view becomes visible.
     * <p>
     * This ensures that the fields are reset and ready for new input each time the view is shown.
     * </p>
     *
     * @param visible true to make the view visible, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountSignupPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
