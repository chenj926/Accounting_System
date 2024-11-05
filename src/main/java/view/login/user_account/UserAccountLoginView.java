package view.login.user_account;

import interface_adaptors.*;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginState;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code UserAccountLoginView} class represents the graphical user interface for the user account login
 * screen. It extends {@link JFrame} and implements {@link PropertyChangeListener} to respond to changes
 * in the {@link UserAccountLoginViewModel}.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation
 * of concerns. It interacts with the {@link UserAccountLoginViewModel} to reflect the state of the application
 * and with the {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountLoginView extends JFrame implements PropertyChangeListener {
    private UserAccountLoginViewModel viewModel;
    private UserAccountLoginPanel userAccountLoginPanel;

    /**
     * Constructs a {@code UserAccountLoginView} with the specified view model, login controller, and view manager.
     * This constructor sets up the UI components for the login view and initializes the window.
     *
     * @param viewModel       the view model for the login view, providing data and state information
     * @param loginController the controller handling login actions and business logic
     * @param viewManager     the view manager for managing view transitions within the application
     */
    public UserAccountLoginView(UserAccountLoginViewModel viewModel, UserAccountLoginController loginController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        userAccountLoginPanel = new UserAccountLoginPanel(viewModel, loginController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the {@link UserAccountLoginPanel} to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(userAccountLoginPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the login view based on state changes in the view model.
     * Displays a message dialog based on the success or error message in the {@link UserAccountLoginState}.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountLoginState state = (UserAccountLoginState) evt.getNewValue();
        if (state.getSuccessMsg() == null) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    /**
     * Sets the visibility of the login view. Clears the fields in the login panel when the view becomes visible.
     *
     * @param visible true to make the view visible, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            userAccountLoginPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
