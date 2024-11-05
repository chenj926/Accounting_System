package view.home_page;

import interface_adaptors.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code HomePagePanel} class represents the panel for the home page in the application.
 * It extends {@link JPanel} and is responsible for displaying the home page interface, including
 * buttons for login, shared account login, signup, shared account signup, and exit, along with a background image.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation
 * of concerns. It interacts with the {@link HomePageViewModel} to reflect the state of the application and
 * with the {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Jessica Chen, Xile Chen, Dana Huang</p>
 */
public class HomePagePanel extends JPanel {
    private final HomePageViewModel viewModel;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JButton loginButton;
    private JButton sharedAccountLoginButton;
    private JButton signupButton;
    private JButton sharedAccountSignupButton;
    private JButton exitButton;
    private Image backgroundImage;

    /**
     * Constructs a {@code HomePagePanel} object with the specified view model and view manager.
     *
     * @param viewModel   the view model providing data and state information to this panel
     * @param viewManager the view manager responsible for managing view transitions within the application
     */
    public HomePagePanel(HomePageViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;

        String baseDir = System.getProperty("user.dir");
        Path imgPath = Paths.get(baseDir, "src/main/UI_img/homePage3.png");
        loadBackgroundImage(String.valueOf(imgPath));
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Loads the background image from the specified path.
     *
     * @param path the path to the image file
     */
    private void loadBackgroundImage(String path) {
        try {
            this.backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the component, including the background image.
     *
     * @param g the {@link Graphics} context to use for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Initializes the components used in the home page panel, including buttons and labels.
     * Sets the style and dimensions for these components.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        this.sharedAccountLoginButton = new JButton(this.viewModel.getSharedAccountLoginButtonLabel());
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        this.sharedAccountSignupButton = new JButton(this.viewModel.getSharedAccountSignupButtonLabel());
        this.exitButton = new JButton(this.viewModel.getExitButtonLabel());

        // Style buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Color buttonBackgroundColor = new Color(255, 255, 255);
        Color buttonTextColor = Color.BLACK;

        JButton[] buttons = {loginButton, sharedAccountLoginButton, signupButton, sharedAccountSignupButton, exitButton};
        Dimension buttonSize = new Dimension(220, 30);
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonBackgroundColor);
            button.setForeground(buttonTextColor);
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
        }
    }

    /**
     * Sets up the UI layout for the home page panel using {@link BorderLayout}.
     * Arranges the buttons in the center panel and the title label at the top.
     */
    private void setupUI() {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);  // Make the panel transparent
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(loginButton, constraints);

        constraints.gridy++;
        centerPanel.add(signupButton, constraints);

        constraints.gridy++;
        centerPanel.add(sharedAccountLoginButton, constraints);

        constraints.gridy++;
        centerPanel.add(sharedAccountSignupButton, constraints);

        constraints.gridy++;
        centerPanel.add(exitButton, constraints);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up listeners for the buttons in the home page panel. Each button is associated with
     * an action listener that triggers a transition to a different view or exits the application.
     */
    private void setupListeners() {
        // login button response action
        loginButton.addActionListener(e -> this.viewManager.setActiveViewName("log in"));

        // shared account login button response action
        sharedAccountLoginButton.addActionListener(e -> this.viewManager.setActiveViewName("shared account log in"));

        // signup button response action
        signupButton.addActionListener(e -> this.viewManager.setActiveViewName("sign up"));

        // shared account signup button response action
        sharedAccountSignupButton.addActionListener(e -> this.viewManager.setActiveViewName("shared account sign up"));

        // exit button response action
        this.exitButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            // Explicitly terminate the application
            System.exit(0);
        });

    }
}
