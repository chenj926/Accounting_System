package view.transaction.one_time.user_account;

import interface_adaptors.*;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionState;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The {@code UserAccountOneTimeTransactionPanel} class represents the user interface for managing one-time transactions
 * within a user account. It extends {@link JPanel} and handles user inputs and interactions related to one-time transactions.
 * <p>
 * This class is a part of the Clean Architecture's user interface layer, where it interacts with the
 * {@link UserOneTimeTransactionViewModel} to retrieve and display data, and the {@link UserAccountOneTimeTransactionController}
 * to execute transaction-related actions.
 * </p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountOneTimeTransactionPanel extends JPanel {
    private final UserOneTimeTransactionViewModel viewModel;
    private final UserAccountOneTimeTransactionController oneTimeTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField descriptionField;
    private JComboBox<String> categoryButton;
    private JButton submitButton;
    private JButton cancelButton;

    /**
     * Constructs a {@code UserAccountOneTimeTransactionPanel} object with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the components, sets up the user interface layout, and configures the event listeners
     * to manage user interactions for the one-time transaction functionality.
     * </p>
     *
     * @param viewModel                  the view model for the one-time transaction panel
     * @param oneTimeTransactionController the controller handling one-time transaction actions
     * @param viewManager                the view manager for handling view transitions
     */
    public UserAccountOneTimeTransactionPanel(UserOneTimeTransactionViewModel viewModel,
                                              UserAccountOneTimeTransactionController oneTimeTransactionController,
                                              ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.oneTimeTransactionController = oneTimeTransactionController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the one-time transaction panel, including labels, text fields, and buttons.
     * <p>
     * This method sets up the labels, text fields, buttons, and the category combo box that are required
     * for the user to input transaction details.
     * </p>
     */
    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountField = new JTextField(20);
        this.dateField = new JTextField(20);
        this.descriptionField = new JTextField(20);

        // add buttons
        this.submitButton = new JButton(this.viewModel.getSubmitButton());
        this.cancelButton = new JButton(this.viewModel.getCancelButton());

        // add ComboBox for category
        String[] category = {"Auto", "Personal item", "Food", "Transport", "Income", "entertainment", "Travel",
                "Utilities", "Medical", "Home", "Custom"};
        this.categoryButton = new JComboBox<>(category);

        // Style buttons
        styleButton(this.submitButton, new Color(100, 150, 200));
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
    }

    /**
     * Sets up the user interface layout for the one-time transaction panel using a {@link GridBagLayout}.
     * <p>
     * This method arranges the UI components within the panel, ensuring a logical flow and alignment for user interactions.
     * </p>
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // Amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // Date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDate()), constraints);
        constraints.gridx = 1;
        add(this.dateField, constraints);

        // Description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // Category
        constraints.gridx = 0;
        constraints.gridy++;
        add (new JLabel(viewModel.getCategoryButton()), constraints);
        constraints.gridx = 1;
        add(this.categoryButton, constraints);

        // button panel for submit and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.submitButton);
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
     * Sets up listeners for handling user interactions, including submit and cancel actions,
     * and updates to the form fields.
     * <p>
     * This method configures event listeners for the buttons and form fields, allowing the
     * panel to react to user inputs and to interact with the controller and view model.
     * </p>
     */
    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    String category = (String) categoryButton.getSelectedItem();
                    if ("custom".equals(category)) {
                        category = viewModel.getState().getTransactionCategory();
                    }
                    System.out.println("in one time panel"+viewManager.getUserId());
                    oneTimeTransactionController.execute(
                            viewManager.getUserId(),
                            amountField.getText(),
                            dateField.getText(),
                            descriptionField.getText(),
                            category
                    );
                }
            }
        });

        // cancel button response action
        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("Homepage Two");
        });

        // get typed amount
        this.amountField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        UserOneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        // get typed date
        this.dateField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        UserOneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionDate(dateField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        // get typed description
        this.descriptionField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        UserOneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        this.categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserOneTimeTransactionState currentState = viewModel.getState();

                // Check if the selected item is "custom"
                if ("Custom".equals(categoryButton.getSelectedItem())) {
                    // Prompt the user to enter the number of custom days
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the category type",
                            "Custom category", JOptionPane.PLAIN_MESSAGE);

                    // set the custom category
                    currentState.setTransactionCategory(input);
                    viewModel.setState(currentState);
                } else {
                    //set the selected period
                    currentState.setTransactionCategory((String) categoryButton.getSelectedItem());
                    viewModel.setState(currentState);
                }
            }
        });
    }

    /**
     * Clears all input fields in the one-time transaction panel.
     * <p>
     * This method resets the text fields for the amount, date, and description to ensure
     * that the form is ready for a new transaction entry.
     * </p>
     */
    public void clearFields() {
        amountField.setText("");
        dateField.setText("");
        descriptionField.setText("");
    }
}
