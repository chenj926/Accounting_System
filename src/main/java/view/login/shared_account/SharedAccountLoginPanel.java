package view.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginState;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The {@code SharedAccountLoginPanel} class manages the user interface for logging into a shared account.
 * It provides fields for entering the shared account ID and password, and handles the logic for initiating the login process.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation of concerns.
 * It interacts with the {@link SharedAccountLoginViewModel} to reflect the state of the application, and with the
 * {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Authors:</b> Xile Chen, Eric Chen</p>
 */
public class SharedAccountLoginPanel extends JPanel {
    private final SharedAccountLoginViewModel sharedViewModel;
    private final SharedAccountLoginController sharedAccountLoginController;
    private final ViewManagerModel viewManager;

    // UI components for labels, text fields, and buttons
    private JLabel titleLabel;
    private JTextField sharedAccountIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    /**
     * Constructs a {@code SharedAccountLoginPanel} with the specified view model, login controller, and view manager.
     *
     * @param sharedViewModel           the view model for the shared account login panel, providing data and state information
     * @param sharedAccountLoginController the controller handling shared account login actions and business logic
     * @param viewManager               the view manager for managing view transitions within the application
     */
    public SharedAccountLoginPanel(SharedAccountLoginViewModel sharedViewModel,
                                   SharedAccountLoginController sharedAccountLoginController,
                                   ViewManagerModel viewManager) {
        this.sharedViewModel = sharedViewModel;
        this.sharedAccountLoginController = sharedAccountLoginController;
        this.viewManager = viewManager;

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes components specific to the shared account login panel, including the title, text fields, and buttons.
     * This method also styles the buttons to match the application's design guidelines.
     */
    protected void initializeComponents() {
        // Title layout
        this.titleLabel = new JLabel(sharedViewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize text fields for shared account ID and password
        this.sharedAccountIdField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.sharedAccountIdField.setToolTipText("Enter Shared Account ID");
        this.passwordField.setToolTipText("Enter Shared Account Password");

        // Initialize buttons for login and cancel actions
        this.loginButton = new JButton(this.sharedViewModel.getLoginButtonLabel());
        this.cancelButton = new JButton(this.sharedViewModel.getCancelButtonLabel());

        // Style buttons
        styleButton(this.loginButton, new Color(100, 150, 200));
        styleButton(this.cancelButton, new Color(200, 100, 100));
    }

    /**
     * Styles the given button with the specified background color.
     *
     * @param button the button to style
     * @param backgroundColor the background color to apply to the button
     */
    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * Sets up the user interface for the shared account login panel, arranging components using a {@link GridBagLayout}.
     * Adds components such as labels, text fields, and buttons to the panel.
     */
    protected void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding

        // Title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // Reset gridwidth and anchor for other components
        constraints.anchor = GridBagConstraints.WEST;

        // Shared Account ID
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(new JLabel(this.sharedViewModel.getIdentificationLabel()), constraints);
        // Input Shared Account ID
        constraints.gridx = 1;
        add(this.sharedAccountIdField, constraints);

        // Password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.sharedViewModel.getPasswordLabel()), constraints);
        // Input password
        constraints.gridx = 1;
        add(this.passwordField, constraints);

        // Button panel for login and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.loginButton);
        buttonsPanel.add(this.cancelButton);

        // Add Buttons Panel
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(buttonsPanel, constraints);
    }

    /**
     * Sets up listeners for user interactions, including button clicks and key presses.
     * Updates the view model based on user input and handles actions for login and cancel buttons.
     */
    protected void setupListeners() {
        // Login button response action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(loginButton)) {
                    sharedAccountLoginController.execute(
                            sharedAccountIdField.getText(), // Use shared account ID
                            String.valueOf(passwordField.getPassword())
                    );
                }
            }
        });

        // Cancel button response action
        cancelButton.addActionListener(e -> viewManager.setActiveViewName("home page"));

        // Get typed Shared Account ID
        this.sharedAccountIdField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountLoginState currentState = sharedViewModel.getState();
                currentState.setIdentification(sharedAccountIdField.getText() + evt.getKeyChar());
                viewManager.setUserId(sharedAccountIdField.getText() + evt.getKeyChar());
                sharedViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // get typed password
        this.passwordField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        SharedAccountLoginState currentState = sharedViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordField.getPassword()) + evt.getKeyChar());
                        sharedViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
    }

    /**
     * Clears the text fields for the shared account ID and password.
     * This method is typically called when the login panel is made visible again to ensure no old data remains.
     */
    public void clearFields() {
        passwordField.setText("");
        sharedAccountIdField.setText(""); // Clear shared account ID field
    }
}


