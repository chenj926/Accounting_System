package view.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginState;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code SharedAccountLoginView} class represents the graphical user interface for the shared account login screen.
 * It extends {@link JFrame} and implements {@link PropertyChangeListener} to respond to changes in the
 * {@link SharedAccountLoginViewModel}.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation
 * of concerns. It interacts with the {@link SharedAccountLoginViewModel} to reflect the state of the application
 * and with the {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Authors:</b> Xile Chen, Eric Chen</p>
 */
public class SharedAccountLoginView extends JFrame implements PropertyChangeListener {
    private SharedAccountLoginViewModel sharedViewModel;
    private SharedAccountLoginPanel sharedAccountLoginPanel;

    /**
     * Constructs a {@code SharedAccountLoginView} with the specified view model, login controller, and view manager.
     * This constructor sets up the UI components for the shared account login view and initializes the window.
     *
     * @param sharedViewModel           the shared account login view model, providing data and state information
     * @param sharedAccountLoginController the controller handling shared account login actions and business logic
     * @param viewManager               the view manager for managing view transitions within the application
     */
    public SharedAccountLoginView(SharedAccountLoginViewModel sharedViewModel, SharedAccountLoginController sharedAccountLoginController, ViewManagerModel viewManager) {
        super(sharedViewModel.getTitleLabel());
        this.sharedViewModel = sharedViewModel;
        this.sharedViewModel.addPropertyChangeListener(this);

        sharedAccountLoginPanel = new SharedAccountLoginPanel(sharedViewModel, sharedAccountLoginController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the {@link SharedAccountLoginPanel} to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountLoginPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the shared account login view based on state changes in the view model.
     * Displays a message dialog based on the success or error message in the {@link SharedAccountLoginState}.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountLoginState state = (SharedAccountLoginState) evt.getNewValue();
        if (state.getSuccessMsg() == null) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    /**
     * Sets the visibility of the shared account login view. Clears the fields in the shared account login panel when the view becomes visible.
     *
     * @param visible true to make the view visible, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountLoginPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}


