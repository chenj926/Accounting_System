package view.home_page.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoController;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoState;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SharedAccountHomepageTwoPanel} class represents the user interface for the "Shared Account Homepage Two"
 * section of the shared account module. It extends {@link JPanel} and is responsible for displaying
 * user-specific information, including username, balance, income, and outflow. The panel also provides
 * navigation buttons for various actions such as one-time transactions, periodic transactions, viewing financial
 * reports, and logging out.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation
 * of concerns. It interacts with the {@link SharedAccountHomepageTwoViewModel} to reflect the state of the
 * application and with the {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountHomepageTwoPanel extends JPanel {
    private final SharedAccountHomepageTwoViewModel viewModel;
    private final ViewManagerModel viewManager;
    private SharedAccountHomepageTwoController controller;
    private String[] basicUserInfo;

    // text
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel outflowLabel;
    // value
    private JLabel usernameValueLabel;
    private JLabel balanceValueLabel;
    private JLabel incomeValueLabel;
    private JLabel outflowValueLabel;
    //button
    private JButton oneTimeButton;
    private JButton periodicButton;
    private JButton logoutButton;
    //history added
    private JButton financialReportButton;

    /**
     * Constructs a {@code SharedAccountHomepageTwoPanel} with the specified view model, view manager, and controller.
     *
     * @param viewModel  the view model for the panel, providing data and state information
     * @param viewManager the view manager for handling view transitions within the application
     * @param controller the controller responsible for handling user actions and executing
     *                   business logic related to "Shared Account Homepage Two"
     */
    public SharedAccountHomepageTwoPanel(SharedAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager,
                            SharedAccountHomepageTwoController controller) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.controller = controller;

        this.viewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())){
                SharedAccountHomepageTwoState state = viewModel.getState();
                this.basicUserInfo = state.getBasicUserInfo();
                updateUI(); // Update the UI components when state changes
            }
        });

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the panel.
     * This includes labels for title, balance, income, and outflow, and buttons for navigation.
     */
    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(this.viewModel.getTITLE_LABEL());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize labels for user information
        this.usernameLabel = new JLabel(this.viewModel.getUSERNAME_LABEL());
        this.incomeLabel = new JLabel(this.viewModel.getINCOME_LABEL());
        this.outflowLabel = new JLabel(this.viewModel.getOUTFLOW_LABEL());
        this.balanceLabel = new JLabel(this.viewModel.getBALANCE_LABEL());

        // Placeholder values for user information (to be updated later)
        this.usernameValueLabel = new JLabel(" User ");
        this.incomeValueLabel = new JLabel(" 0.00 ");
        this.outflowValueLabel = new JLabel(" 0.00 ");
        this.balanceValueLabel = new JLabel(" 0.00 ");

        // Initialize buttons and their properties
        this.oneTimeButton = new JButton(this.viewModel.getONE_TIME_BUTTON_LABEL());
        this.periodicButton = new JButton(this.viewModel.getPERIODIC_BUTTON_LABEL());
        this.logoutButton = new JButton(this.viewModel.getCANCEL_BUTTON_LABEL());
        this.financialReportButton = new JButton(this.viewModel.getFINANCIAL_REPORT_BUTTON_LABEL());

        // Style buttons
        styleButton(this.oneTimeButton, new Color(100, 150, 200));
        styleButton(this.periodicButton, new Color(100, 150, 200));
        styleButton(this.logoutButton, new Color(200, 100, 100));
        styleButton(this.financialReportButton, new Color(150, 200, 100));
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
     * Sets up the user interface by arranging the components in a {@link GridBagLayout}.
     * The layout is organized with labels and corresponding values, and buttons are aligned for navigation.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Set the preferred size for buttons
        Dimension buttonSize = new Dimension(200, 30);  // Adjust size as needed
        this.oneTimeButton.setPreferredSize(buttonSize);
        this.periodicButton.setPreferredSize(buttonSize);
        this.logoutButton.setPreferredSize(buttonSize);
        this.financialReportButton.setPreferredSize(buttonSize);

        // Title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // Username Label and Field
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        add(this.usernameLabel, constraints);
        constraints.gridx = 1;
        add(this.usernameValueLabel, constraints);

        // Income Label and Field
        constraints.gridy++;
        constraints.gridx = 0;  // Reset gridx
        constraints.gridwidth = 1;  // Reset gridwidth
        add(this.incomeLabel, constraints);
        constraints.gridx = 1;
        add(this.incomeValueLabel, constraints);

        // Outflow Label and Field
        constraints.gridx = 0;
        constraints.gridy++;
        add(this.outflowLabel, constraints);
        constraints.gridx = 1;
        add(this.outflowValueLabel, constraints);

        // Balance Label and Field
        constraints.gridx = 0;
        constraints.gridy++;
        add(this.balanceLabel, constraints);
        constraints.gridx = 1;
        add(this.balanceValueLabel, constraints);

        // One Time Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(this.oneTimeButton, constraints);

        // Periodic Button
        constraints.gridx = 1;
        add(this.periodicButton, constraints);

        // history button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.financialReportButton, constraints);

        // Logout Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.logoutButton, constraints);
    }

    /**
     * Sets up the listeners for the buttons to handle user actions.
     * <ul>
     * <li>The one-time button navigates to the shared account one-time transaction view.</li>
     * <li>The periodic button navigates to the shared account periodic transaction view.</li>
     * <li>The financial report button navigates to the shared account financial report view.</li>
     * <li>The logout button resets the user ID and navigates back to the home page.</li>
     * </ul>
     */
    private void setupListeners() {
        // login button response action
        this.oneTimeButton.addActionListener(e -> this.viewManager.setActiveViewName("Shared Account One Time Transaction"));

        // signup button response action
        this.periodicButton.addActionListener(e -> this.viewManager.setActiveViewName("Shared Account Periodic Transaction"));

        // transactionReport button response action
        this.financialReportButton.addActionListener(e -> this.viewManager.setActiveViewName("Shared Account Financial Report"));

        // exit button response action
        this.logoutButton.addActionListener(e -> {
            this.viewManager.reset(); // reset the userid before log out
            this.viewManager.setActiveViewName("home page"); // go back to home page
        });

    }

    /**
     * Updates the UI components based on the current state of the view model.
     * This includes updating the displayed user information such as username, income, outflow, and balance.
     */
    @Override
    public void updateUI() {
        if (this.basicUserInfo != null && this.basicUserInfo.length == 4) {
            this.usernameValueLabel.setText(this.basicUserInfo[0]);
            this.incomeValueLabel.setText(this.basicUserInfo[1]);
            this.outflowValueLabel.setText(this.basicUserInfo[2]);
            this.balanceValueLabel.setText(this.basicUserInfo[3]);
        }
    }

}
