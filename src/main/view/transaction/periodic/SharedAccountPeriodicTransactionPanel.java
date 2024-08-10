package view.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.SharedAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.SharedAccountPeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 * The SharedAccountPeriodicTransactionPanel class represents the panel used to input and manage shared account periodic transactions.
 * It includes fields for transaction amount, start and end dates, description, period, and a button for selecting responsible users.
 */
public class SharedAccountPeriodicTransactionPanel extends JPanel {
    private final SharedAccountPeriodicTransactionViewModel viewModel;
    private final SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField descriptionField;
    private JTextField userId;

    private JComboBox<String> periodComb;
    private JComboBox<String> categoryButton;

    private JButton submitButton;
    private JButton cancelButton;
    private JButton selectUsersButton;

    /**
     * Constructs a SharedAccountPeriodicTransactionPanel with the specified view model, controller, and view manager.
     *
     * @param viewModel the view model for the shared account periodic transaction panel
     * @param sharedAccountPeriodicTransactionController the controller handling shared account periodic transaction actions
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountPeriodicTransactionPanel(SharedAccountPeriodicTransactionViewModel viewModel,
                                                 SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController,
                                                 ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.sharedAccountPeriodicTransactionController = sharedAccountPeriodicTransactionController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components for the panel, including labels, text fields, and buttons.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountField = new JTextField(20);
        this.startDateField = new JTextField(20);
        this.endDateField = new JTextField(20);
        this.descriptionField = new JTextField(20);
        this.userId = new JTextField(20);

        // Initialize drop downs
        String[] periods = {"day", "week", "month", "year", "custom"};
        this.periodComb = new JComboBox<>(periods);
        String[] category = {"Auto", "Personal item", "Food", "Transport", "Income", "Entertainment", "Travel",
                "Utilities", "Medical", "Home", "Custom"};
        this.categoryButton = new JComboBox<>(category);

        // Initialize buttons
        JPanel buttons = new JPanel();
        this.submitButton = new JButton(this.viewModel.getSubmitButton());
        buttons.add(this.submitButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButton());
        buttons.add(this.submitButton);
        this.selectUsersButton = new JButton(this.viewModel.getSELECT_USER()); // New button for selecting users

        // Style buttons
        this.submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.selectUsersButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.submitButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.selectUsersButton.setBackground(new Color(150, 150, 200));
        this.submitButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);
        this.selectUsersButton.setForeground(Color.WHITE);

        // Adjust environment for compatibility with MAC
        this.submitButton.setOpaque(true);
        this.submitButton.setBorderPainted(false);
        this.cancelButton.setOpaque(true);
        this.cancelButton.setBorderPainted(false);
        this.selectUsersButton.setOpaque(true);
        this.selectUsersButton.setBorderPainted(false);
    }

    /**
     * Sets up the layout and UI components of the panel using GridBagLayout.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Title
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // Amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // Start date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getStartDate()), constraints);
        constraints.gridx = 1;
        add(this.startDateField, constraints);

        // End date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getEndDate()), constraints);
        constraints.gridx = 1;
        add(this.endDateField, constraints);

        // Description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // User id
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getUSER_ID_FIELD_LABEL()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // Period drop down
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getPeriod()), constraints);
        constraints.gridx = 1;
        add(this.periodComb, constraints);

        // Category
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getCategoryButton()), constraints);
        constraints.gridx = 1;
        add(this.categoryButton, constraints);

        // Select Users Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.selectUsersButton, constraints);

        // Button panel for submit and cancel
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
     * Sets up the action listeners for the buttons and fields.
     */
    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    // check if is custom period
                    String period = (String) periodComb.getSelectedItem();
                    if ("custom".equals(period)) {
                        period = viewModel.getState().getTransactionPeriod();
                    }

                    // check if is custom category
                    String category = (String) categoryButton.getSelectedItem();
                    if ("custom".equals(category)) {
                        category = viewModel.getState().getTransactionCategory();
                    }

                    // execute to controller
                    sharedAccountPeriodicTransactionController.execute(
                            amountField.getText(),
                            startDateField.getText(),
                            descriptionField.getText(),
                            period,
                            endDateField.getText(),
                            category,
                            startDateField.getText(),
                            viewManager.getUserId(),
                            userId.getText()
                            // Pass userIds to the controller
                    );
                }
            }
        });

        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("Shared Account Homepage Two");
        });

        selectUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Simulate user selection dialog
                String input = JOptionPane.showInputDialog(
                        null,
                        "Enter the IDs of responsible users separated by commas.",
                        "Select Users",
                        JOptionPane.PLAIN_MESSAGE
                );

                if (input != null && !input.trim().isEmpty()) {
                    // Update the view model with the selected responsible users
                    viewModel.getState().setIdentification(userId.getText());
                }
            }
        });

        this.amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountPeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.startDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                viewModel.getState().setTransactionStartDate(startDateField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.endDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                viewModel.getState().setTransactionEndDate(endDateField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.descriptionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                viewModel.getState().setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.periodComb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Check if the selected item is "custom"
                if ("custom".equals(periodComb.getSelectedItem())) {
                    // Prompt the user to enter the number of custom days
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the number of custom days:",
                            "Custom Period", JOptionPane.PLAIN_MESSAGE);

                    // set the custom period
                    viewModel.getState().setTransactionPeriod(input);
                } else {
                    // set the selected period
                    viewModel.getState().setTransactionPeriod((String) periodComb.getSelectedItem());
                }
            }
        });

        this.categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Check if the selected item is "custom"
                if ("Custom".equals(categoryButton.getSelectedItem())) {
                    // Prompt the user to enter the number of custom days
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the category type",
                            "Custom category", JOptionPane.PLAIN_MESSAGE);

                    // set the custom category
                    viewModel.getState().setTransactionCategory(input);
                } else {
                    // set the selected period
                    viewModel.getState().setTransactionCategory((String) categoryButton.getSelectedItem());
                }
            }
        });
    }

    /**
     * Clears all text fields in the panel and resets the set of responsible user IDs.
     */
    public void clearFields() {
        amountField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        descriptionField.setText("");
        userId.setText("");

    }
}


