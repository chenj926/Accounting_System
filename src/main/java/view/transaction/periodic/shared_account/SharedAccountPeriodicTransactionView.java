package view.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code SharedAccountPeriodicTransactionView} class provides the graphical user interface for managing
 * periodic transactions within a shared account. It extends {@link JFrame} and implements {@link PropertyChangeListener}
 * to respond to changes in the associated {@link SharedAccountPeriodicTransactionViewModel}.
 * <p>
 * This class is part of the user interface layer in the Clean Architecture structure. It is responsible for displaying
 * the current state of shared account periodic transactions and for handling user interactions that may alter that state.
 * </p>
 *
 * <p><b>Author:</b> Xile Chen</p>
 */
public class SharedAccountPeriodicTransactionView extends JFrame implements PropertyChangeListener {
    private SharedAccountPeriodicTransactionPanel sharedAccountPeriodicTransactionPanel;
    private SharedAccountPeriodicTransactionViewModel viewModel;

    /**
     * Constructs a {@code SharedAccountPeriodicTransactionView} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the view by setting up the panel with the necessary UI components and registers
     * itself as a listener to the view model for property changes.
     * </p>
     *
     * @param viewModel the view model for the shared account periodic transaction view
     * @param sharedAccountPeriodicTransactionController the controller handling shared account periodic transaction actions
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountPeriodicTransactionView(SharedAccountPeriodicTransactionViewModel viewModel,
                                                SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController,
                                                ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        sharedAccountPeriodicTransactionPanel = new SharedAccountPeriodicTransactionPanel(viewModel, sharedAccountPeriodicTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);  // Adjusted size to accommodate additional UI components
        setVisible(true);
    }

    /**
     * Sets up the user interface layout for the shared account periodic transaction view.
     * <p>
     * This method arranges the panel within the frame and prepares the UI components for interaction.
     * </p>
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountPeriodicTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model by updating the UI accordingly.
     * <p>
     * This method listens for changes in the {@link SharedAccountPeriodicTransactionViewModel} and displays appropriate
     * messages to the user based on the success or error state of the transaction.
     * </p>
     *
     * @param evt the property change event containing the new state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountPeriodicTransactionState state = (SharedAccountPeriodicTransactionState) evt.getNewValue();
        if (state.getErrorMessage() == null) {
            String successMsg = state.getSuccessMessage();
            System.out.println(successMsg);
            JOptionPane.showMessageDialog(this, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String errMsg = state.getErrorMessage();
            JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Makes the view visible and clears the fields in the shared account periodic transaction panel when the view becomes visible.
     * <p>
     * This method ensures that the input fields are cleared each time the view is displayed,
     * providing a fresh start for entering transaction details.
     * </p>
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountPeriodicTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}

