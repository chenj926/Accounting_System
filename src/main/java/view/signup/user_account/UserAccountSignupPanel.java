package view.signup.user_account;

import interface_adaptors.*;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupState;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The {@code UserAccountSignupPanel} class represents the panel used for user signup in the application.
 * It contains fields for user input, including username, password, and identification, as well as buttons
 * for submitting the signup form and canceling the action.
 * <p>
 * This class is part of the view layer in the Clean Architecture, interacting with the {@link UserAccountSignupViewModel}
 * to display and manage user input, and with the {@link UserAccountSignupController} to handle the business logic
 * for user account creation.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Jessica Chen, Xile Chen</p>
 */
public class UserAccountSignupPanel extends JPanel {
    private final UserAccountSignupViewModel viewModel;
    private UserAccountSignupController userAccountSignupController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField idenficationField;
    private JButton signupButton;
    private JButton cancelButton;

    /**
     * Constructs a {@code UserAccountSignupPanel} object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the signup panel, providing data and state management
     * @param userAccountSignupController the controller for handling the signup actions and business logic
     * @param viewManager      the view manager for managing view transitions within the application
     */
    public UserAccountSignupPanel(UserAccountSignupViewModel viewModel, UserAccountSignupController userAccountSignupController, ViewManagerModel viewManager) {
        this.userAccountSignupController = userAccountSignupController;
        this.viewManager = viewManager;
        this.viewModel = viewModel;

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the signup panel, including the input fields and buttons.
     * This method also styles the buttons to match the application's design.
     */
    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.usernameTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.idenficationField = new JTextField(20);

        // Tooltips explanation to users
        this.usernameTextField.setToolTipText("Set a nick-name for your account");
        this.passwordField.setToolTipText("Set a secret password");
        this.idenficationField.setToolTipText("Set a unique ID for you to login");

        // add buttons
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        this.cancelButton = new JButton(this.viewModel.getCancelButtonLabel());

        // Style buttons
        styleButton(this.signupButton, new Color(100, 150, 200));
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
     * Sets up the layout of the signup panel using {@link GridBagLayout}.
     * This method arranges the labels, text fields, and buttons on the panel.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // username
        constraints.gridy = 1;
        add(new JLabel(this.viewModel.getUsernameLabel()), constraints);
        constraints.gridx = 1;
        add(this.usernameTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getPasswordLabel()), constraints);
        constraints.gridx = 1;
        add(this.passwordField, constraints);

        // identification
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getID_LABEL()), constraints);
        constraints.gridx = 1;
        add(this.idenficationField, constraints);

        // button panel for sign-up and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.signupButton);
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
     * Sets up listeners for the signup and cancel buttons, as well as key events in the text fields.
     * This method updates the view model based on user input and handles the actions for the buttons.
     */
    private void setupListeners() {
        // sign up button response action
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        userAccountSignupController.execute(
                                usernameTextField.getText(),
                                String.valueOf(passwordField.getPassword()),
                                idenficationField.getText()
                        );
                    }
                }
        );

        // cancel button response action
        this.cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // get typed username
        this.usernameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountSignupState currentState = viewModel.getState();
                currentState.setUsername(usernameTextField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // get typed password
        this.passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountSignupState currentState = viewModel.getState();
                currentState.setPassword(String.valueOf(passwordField.getPassword()) + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.idenficationField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountSignupState currentState = viewModel.getState();
                currentState.setIdentification(idenficationField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Clears the text fields in the signup panel.
     * This method is typically called when the view is made visible to ensure the fields are reset.
     */
    public void clearFields() {
        usernameTextField.setText("");
        passwordField.setText("");
        idenficationField.setText("");
    }
}

