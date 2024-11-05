package view.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code UserAccountPeriodicTransactionView} class provides the graphical user interface for managing periodic transactions
 * within a user account. This class extends {@link JFrame} and listens for changes in the associated view model to update
 * the UI accordingly.
 * <p>
 * This class is part of the Clean Architecture's user interface layer, interacting with the
 * {@link UserAccountPeriodicTransactionViewModel} to display data, and the {@link UserAccountPeriodicTransactionController}
 * to handle transaction-related actions.
 * </p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountPeriodicTransactionView extends JFrame implements PropertyChangeListener {
    private UserAccountPeriodicTransactionPanel userAccountPeriodicTransactionPanel;
    private UserAccountPeriodicTransactionViewModel viewModel;

    /**
     * Constructs a {@code UserAccountPeriodicTransactionView} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the view with the necessary components and sets up event listeners to handle
     * user interactions and updates from the view model.
     * </p>
     *
     * @param viewModel                  the view model for the periodic transaction view
     * @param userAccountPeriodicTransactionController the controller handling periodic transaction actions
     * @param viewManager                the view manager for handling view transitions
     */
    public UserAccountPeriodicTransactionView(UserAccountPeriodicTransactionViewModel viewModel,
                                              UserAccountPeriodicTransactionController userAccountPeriodicTransactionController,
                                              ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        userAccountPeriodicTransactionPanel = new UserAccountPeriodicTransactionPanel(viewModel, userAccountPeriodicTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface layout for the periodic transaction view.
     * <p>
     * This method adds the {@code UserAccountPeriodicTransactionPanel} to the frame and configures the layout.
     * </p>
     */
    private void setupUI() {
        this.getContentPane().add(userAccountPeriodicTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model by updating the UI.
     * <p>
     * This method listens for changes in the {@link UserAccountPeriodicTransactionViewModel} and
     * displays either a success or error message in a dialog box based on the state of the transaction.
     * </p>
     *
     * @param evt the property change event containing the new state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountPeriodicTransactionState state = (UserAccountPeriodicTransactionState) evt.getNewValue();
        if (state.getErrorMessage() == null) {
            String successMsg = state.getSuccessMessage();
            JOptionPane.showMessageDialog(this, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String errMsg = state.getErrorMessage();
            JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Makes the view visible and clears the fields in the periodic transaction panel when the view becomes visible.
     * <p>
     * This method ensures that all fields are reset when the view is displayed, preparing it for new user input.
     * </p>
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            userAccountPeriodicTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
