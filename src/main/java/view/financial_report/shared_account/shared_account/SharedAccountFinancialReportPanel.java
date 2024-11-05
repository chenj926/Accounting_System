package view.financial_report.shared_account.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SharedAccountFinancialReportPanel} class represents the panel that displays the financial
 * report of a shared account. This class is responsible for setting up the user interface components,
 * updating the view with the report data from the {@link SharedAccountFinancialReportViewModel}, and handling
 * user interactions, such as navigating back to the previous view.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation
 * of concerns. It interacts with the {@link SharedAccountFinancialReportController} to refresh the data and
 * manage user actions, while observing changes in the {@link SharedAccountFinancialReportViewModel} to update
 * the displayed data accordingly.
 * </p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountFinancialReportPanel extends JPanel {
    private final SharedAccountFinancialReportViewModel viewModel;
    private final SharedAccountFinancialReportController controller;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;

    private JTextArea reportTextArea;
    private JButton backButton;
    private JScrollPane scrollPane;


    private MediaPlayer mediaPlayer; // Added for audio playback
//    private static boolean isJavaFXInitialized = false;  // Flag to track JavaFX initialization

    /**
     * Constructs a {@code SharedAccountFinancialReportPanel} with the specified view model, controller, and view manager.
     *
     * @param viewModel  the view model providing data and state information to this panel
     * @param controller the controller responsible for handling user actions and executing business logic related to the shared account financial report
     * @param viewManager the view manager responsible for managing different views within the application
     */
    public SharedAccountFinancialReportPanel(SharedAccountFinancialReportViewModel viewModel,
                                             SharedAccountFinancialReportController controller,
                                             ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewManager = viewManager;
        this.reportTextArea = new JTextArea();

        this.viewModel.addPropertyChangeListener(evt -> {
            if ("sharedAccountFinancialReport".equals(evt.getPropertyName())){
                this.reportTextArea.setText(viewModel.getReportContent());
//                TextToSpeech TTS = new TextToSpeech();
//                TTS.speak(viewModel.getReportContent());
//                initializeJavaFX(); // Initialize JavaFX before playing audio
//                playAudio(); // Play the audio when the view becomes visible
            }
        });

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components of the panel, including the title label, report text area, and back button.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.reportTextArea.setSelectionColor(Color.BLUE);
        this.reportTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        this.reportTextArea.setEditable(false);

        this.scrollPane = new JScrollPane(this.reportTextArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    /**
     * Sets up the layout of the panel by arranging the components within the panel using a {@link BorderLayout}.
     * Adds the title label at the top, the report content in the center, and the back button at the bottom.
     */
    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        add(this.titleLabel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(this.scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(this.backButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the listeners for user interactions, including handling the action when the "Back" button is pressed.
     * The "Back" button navigates the user back to the "Shared Account Homepage Two" view by updating the active view
     * name in the {@link ViewManagerModel}.
     */
    private void setupListeners() {
        this.backButton.addActionListener(e -> {
//            stopAudio(); // Stop the audio when the view becomes invisible
            viewManager.setActiveViewName("Shared Account Homepage Two");
        });
    }

    /**
     * Refreshes the data displayed in the panel by executing the financial report controller with the current user ID.
     * This method can be called to update the report content when new data is available.
     */
    public void refreshData() {
        this.controller.execute(viewManager.getUserId());
    }

    /**
     * Clears the report text area, typically used when resetting the panel or before loading new data.
     */
    public void clearFields() {
        reportTextArea.setText("");
    }


//    // Method to initialize JavaFX, but only once
//    private void initializeJavaFX() {
//        if (!this.viewManager.isJavaFXInitialized()) {
//            Platform.startup(() -> {
//                // JavaFX Toolkit initialized
////                isJavaFXInitialized = true;
//                this.viewManager.setJavaFXInitialized(true);
//            });
//        }
//    }
//
//    // Method to play the audio (MP3 file)
//    public void playAudio() {
//        String audioFilePath = System.getProperty("user.dir") + "/src/main/data/audio_files/Audio.mp3";
//        Platform.runLater(() -> {
//            try {
//                if (mediaPlayer != null) {
//                    mediaPlayer.dispose(); // Dispose the previous player
//                }
//                Media media = new Media(new File(audioFilePath).toURI().toString());
//                mediaPlayer = new MediaPlayer(media);
//                mediaPlayer.play();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    // Method to stop the audio
//    public void stopAudio() {
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.dispose();
//            mediaPlayer = null;
//        }
//    }

}
