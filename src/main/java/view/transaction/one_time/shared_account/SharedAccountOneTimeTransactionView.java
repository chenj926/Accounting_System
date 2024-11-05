package view.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionState;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code SharedAccountOneTimeTransactionView} class represents the user interface for managing one-time transactions
 * within a shared account. It extends {@link JFrame} and implements {@link PropertyChangeListener} to handle property changes
 * in the view model.
 * <p>
 * This class is part of the Clean Architecture's user interface layer, where it interacts with the
 * {@link SharedAccountOneTimeTransactionViewModel} to retrieve and display data, and the {@link SharedAccountOneTimeTransactionController}
 * to execute transaction-related actions.
 * </p>
 *
 * <p><b>Authors:</b> Xile Chen, Eric Chen</p>
 */
public class SharedAccountOneTimeTransactionView extends JFrame implements PropertyChangeListener {
    private SharedAccountOneTimeTransactionPanel sharedAccountOneTimeTransactionPanel;
    private SharedAccountOneTimeTransactionViewModel viewModel;

    /**
     * Constructs a {@code SharedAccountOneTimeTransactionView} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the user interface components and sets up the property change listener
     * to monitor updates in the view model.
     * </p>
     *
     * @param viewModel the view model for the shared account one-time transaction view
     * @param sharedAccountOneTimeTransactionController the controller handling shared account one-time transaction actions
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountOneTimeTransactionView(SharedAccountOneTimeTransactionViewModel viewModel,
                                               SharedAccountOneTimeTransactionController sharedAccountOneTimeTransactionController,
                                               ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        sharedAccountOneTimeTransactionPanel = new SharedAccountOneTimeTransactionPanel(viewModel, sharedAccountOneTimeTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);  // Adjusted size for additional UI components
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the shared account one-time transaction panel to the frame.
     * <p>
     * This method arranges the main panel within the frame, ensuring that all transaction-related UI components
     * are displayed appropriately.
     * </p>
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountOneTimeTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property changes in the view model.
     * <p>
     * This method listens for changes in the {@link SharedAccountOneTimeTransactionViewModel} and updates the UI
     * accordingly. It displays error messages or success messages based on the state of the transaction.
     * </p>
     *
     * @param evt the property change event containing the new value of the state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountOneTimeTransactionState state = (SharedAccountOneTimeTransactionState) evt.getNewValue();
        if (state.getSuccessMessage() == null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

    /**
     * Makes the view visible and clears the transaction fields when the view becomes visible.
     * <p>
     * This method ensures that whenever the view becomes visible, all input fields are reset, providing
     * a fresh state for the user to enter new transaction data.
     * </p>
     *
     * @param visible boolean indicating whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountOneTimeTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}

