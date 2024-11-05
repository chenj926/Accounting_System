package view.signup.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupState;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code UserAccountSignupView} class represents the graphical user interface (GUI) for user account signup.
 * It extends {@link JFrame} and manages the signup panel, allowing users to create a new account.
 * <p>
 * This class is part of the view layer in the Clean Architecture, interacting with the {@link UserAccountSignupViewModel}
 * to display data and handle user interactions. It also listens to property changes to respond to state updates.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Jessica Chen, Xile Chen</p>
 */
public class UserAccountSignupView extends JFrame implements PropertyChangeListener {
    protected final UserAccountSignupViewModel viewModel;
    protected final UserAccountSignupController userAccountSignupController;
    protected final ViewManagerModel viewManager;
    private final UserAccountSignupPanel userAccountSignupPanel;

    /**
     * Constructs a {@code UserAccountSignupView} object with the specified view model, controller, and view manager.
     *
     * @param viewModel                  the view model for the signup view, providing the necessary data and state
     * @param userAccountSignupController the controller for handling signup actions and business logic
     * @param viewManager                the view manager for managing view transitions within the application
     */
    public UserAccountSignupView(UserAccountSignupViewModel viewModel, UserAccountSignupController userAccountSignupController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.userAccountSignupController = userAccountSignupController;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);

        this.userAccountSignupPanel = new UserAccountSignupPanel(viewModel, userAccountSignupController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the UI components for the signup view.
     * This method adds the {@link UserAccountSignupPanel} to the frame's content pane.
     */
    protected void setupUI() {
        this.getContentPane().add(userAccountSignupPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the signup view based on the {@link UserAccountSignupState}.
     * If the state indicates an error, it shows an error message. Otherwise, it shows a success message.
     *
     * @param evt the property change event that contains the new state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountSignupState state = (UserAccountSignupState) evt.getNewValue();

        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            String successMsg = state.getSuccessMsg();
            JOptionPane.showMessageDialog(this, successMsg);
        }
    }

    /**
     * Overrides the {@code setVisible} method to clear the input fields when the view becomes visible.
     *
     * @param visible {@code true} to make the view visible, {@code false} to make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            userAccountSignupPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}

