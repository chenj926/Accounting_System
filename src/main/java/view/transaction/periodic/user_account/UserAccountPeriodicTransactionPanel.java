package view.transaction.periodic.user_account;

import interface_adaptors.*;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The {@code UserAccountPeriodicTransactionPanel} class provides the graphical user interface for managing
 * periodic transactions within a user account. It extends {@link JPanel} and includes input fields for transaction
 * amount, start and end dates, description, and period, along with submit and cancel buttons.
 * <p>
 * This class is part of the user interface layer in the Clean Architecture structure. It interacts with the
 * {@link UserAccountPeriodicTransactionViewModel} to display data and the {@link UserAccountPeriodicTransactionController}
 * to handle user actions related to periodic transactions.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Jessica Chen</p>
 */
public class UserAccountPeriodicTransactionPanel extends JPanel {
    private final UserAccountPeriodicTransactionViewModel viewModel;
    private final UserAccountPeriodicTransactionController userAccountPeriodicTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField descriptionField;
    private JComboBox<String> periodComb;
    private JComboBox<String> categoryButton;
    private JButton submitButton;
    private JButton cancelButton;

    /**
     * Constructs a {@code UserAccountPeriodicTransactionPanel} with the specified view model, controller, and view manager.
     * <p>
     * This constructor initializes the panel with the necessary UI components and sets up the layout and event listeners.
     * </p>
     *
     * @param viewModel                   the view model for the periodic transaction panel
     * @param userAccountPeriodicTransactionController the controller handling periodic transaction actions
     * @param viewManager                 the view manager for handling view transitions
     */
    public UserAccountPeriodicTransactionPanel(UserAccountPeriodicTransactionViewModel viewModel,
                                               UserAccountPeriodicTransactionController userAccountPeriodicTransactionController,
                                               ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.userAccountPeriodicTransactionController = userAccountPeriodicTransactionController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components for the panel, including labels, text fields, dropdown menus, and buttons.
     * <p>
     * This method configures the individual components that will be added to the panel, such as the input fields
     * for transaction details and the buttons for submitting or canceling the transaction.
     * </p>
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountField = new JTextField(20);
        this.startDateField = new JTextField(20);
        this.endDateField = new JTextField(20);
        this.descriptionField = new JTextField(20);

        // initialize drop downs
        String[] periods = {"day", "week", "month", "year", "custom"};
        this.periodComb = new JComboBox<>(periods);
        String[] category = {"Auto", "Personal item", "Food", "Transport", "Income", "entertainment", "Travel",
                "Utilities", "Medical", "Home", "Custom"};
        this.categoryButton = new JComboBox<>(category);

        // add buttons
        JPanel buttons = new JPanel();
        this.submitButton = new JButton(this.viewModel.getSubmitButton());
        buttons.add(this.submitButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButton());
        buttons.add(this.cancelButton);

        // Style buttons
        this.styleButton(this.submitButton, new Color(100, 150, 200));
        this.styleButton(this.cancelButton, new Color(200, 100, 100));
    }

    /**
     * Styles the given button with the specified background color.
     * <p>
     * This method sets the font, background color, and foreground color of the button, and ensures
     * it is opaque with no border painting.
     * </p>
     *
     * @param button the button to style
     * @param color  the background color for the button
     */
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * Sets up the layout and user interface components of the panel using {@link GridBagLayout}.
     * <p>
     * This method arranges the components in the panel, such as the input fields and buttons, and positions them using
     * a flexible grid-based layout.
     * </p>
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // title
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // start date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getStartDate()), constraints);
        constraints.gridx = 1;
        add(this.startDateField, constraints);

        // end date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getEndDate()), constraints);
        constraints.gridx = 1;
        add(this.endDateField, constraints);

        // description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // period drop down
        constraints.gridx = 0;
        constraints.gridy++;
        add (new JLabel(viewModel.getPeriod()), constraints);
        constraints.gridx = 1;
        add(this.periodComb, constraints);

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
     * Sets up the action listeners for the buttons and input fields to handle user interactions.
     * <p>
     * This method defines the behavior of the submit and cancel buttons, as well as handling key events in the text fields.
     * It ensures that the state of the transaction is updated based on user input.
     * </p>
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
                    userAccountPeriodicTransactionController.execute(
                            viewManager.getUserId(),
                            amountField.getText(),
                            startDateField.getText(),
                            descriptionField.getText(),
                            period,
                            endDateField.getText(),
                            category, startDateField.getText()
                    );
                }
            }
        });

        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("Homepage Two");
        });

        this.amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.startDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionStartDate(startDateField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.endDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionEndDate(endDateField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.descriptionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });


        this.periodComb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent  evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();

                // Check if the selected item is "custom"
                if ("custom".equals(periodComb.getSelectedItem())) {
                    // Prompt the user to enter the number of custom days
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the number of custom days:",
                            "Custom Period", JOptionPane.PLAIN_MESSAGE);

                    // set the custom period
                    currentState.setTransactionPeriod(input);
                    viewModel.setState(currentState);
                } else {
                    //set the selected period
                    currentState.setTransactionPeriod((String) periodComb.getSelectedItem());
                    viewModel.setState(currentState);
                }
            }
        });

        this.categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserAccountPeriodicTransactionState currentState = viewModel.getState();

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
     * Clears all input fields in the panel.
     * <p>
     * This method resets the text fields to be empty, preparing the panel for new input.
     * </p>
     */
    public void clearFields() {
        amountField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        descriptionField.setText("");
    }
}
