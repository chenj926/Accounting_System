package view.transaction.one_time.user_account;

import interface_adaptors.*;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionState;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code UserAccountOneTimeTransactionView} class represents the user interface for the one-time transaction feature
 * in a user account. It extends {@link JFrame} and implements {@link PropertyChangeListener} to handle property changes
 * in the associated {@link UserOneTimeTransactionViewModel}.
 * <p>
 * As part of the Clean Architecture, this class resides in the view layer, where it interacts with the
 * {@link UserOneTimeTransactionViewModel} to display data and updates the UI based on state changes.
 * It also communicates with the {@link UserAccountOneTimeTransactionController} to manage the business logic for
 * one-time transactions.
 * </p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountOneTimeTransactionView extends JFrame implements PropertyChangeListener {
    private UserAccountOneTimeTransactionPanel userAccountOneTimeTransactionPanel;
    private UserOneTimeTransactionViewModel viewModel;

    /**
     * Constructs a {@code UserAccountOneTimeTransactionView} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor sets up the user interface for the one-time transaction feature,
     * initializes the associated panel, and registers as a listener for property changes in the view model.
     * </p>
     *
     * @param viewModel                the view model for the one-time transaction view
     * @param userAccountOneTimeTransactionController the controller handling one-time transaction actions
     * @param viewManager              the view manager for handling view transitions
     */
    public UserAccountOneTimeTransactionView(UserOneTimeTransactionViewModel viewModel,
                                             UserAccountOneTimeTransactionController userAccountOneTimeTransactionController,
                                             ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        userAccountOneTimeTransactionPanel = new UserAccountOneTimeTransactionPanel(viewModel, userAccountOneTimeTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the one-time transaction panel to the frame.
     * <p>
     * This method is responsible for setting up the layout and components of the frame,
     * ensuring the {@code userAccountOneTimeTransactionPanel} is displayed correctly.
     * </p>
     */
    private void setupUI() {
        this.getContentPane().add(userAccountOneTimeTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property changes in the view model.
     * <p>
     * This method is triggered whenever there is a change in the {@link UserOneTimeTransactionViewModel}'s state.
     * It displays a success message if the transaction was successful, or an error message if it failed.
     * </p>
     *
     * @param evt the property change event containing the new value of the state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserOneTimeTransactionState state = (UserOneTimeTransactionState) evt.getNewValue();
        if (state.getSuccessMessage() == null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

    /**
     * Makes the view visible and clears the transaction fields when the view becomes visible.
     * <p>
     * This method overrides {@link JFrame#setVisible(boolean)} to ensure that when the view becomes visible,
     * the fields in {@code userAccountOneTimeTransactionPanel} are cleared, providing a fresh start for the user.
     * </p>
     *
     * @param visible boolean indicating whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            userAccountOneTimeTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }

}
