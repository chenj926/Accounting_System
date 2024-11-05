package view.financial_report.user_account.user_account;

import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportState;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * UserAccountFinancialReportView is a JFrame that represents the user interface for displaying financial reports related
 * to user accounts. This class adheres to the principles of Clean Architecture by separating the concerns of the view,
 * controller, and view model.
 * <p>
 * The view listens for changes in the {@link UserAccountFinancialReportViewModel} and updates itself accordingly. It
 * also communicates with the {@link UserAccountFinancialReportController} to execute necessary actions when the view becomes visible.
 * </p>
 * <p>
 * The view is part of the presentation layer in the Clean Architecture, responsible for displaying data and capturing
 * user interactions.
 * </p>
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 */
public class UserAccountFinancialReportView extends JFrame implements PropertyChangeListener {
    private UserAccountFinancialReportPanel userAccountFinancialReportPanel;
    private UserAccountFinancialReportViewModel viewModel;
    private UserAccountFinancialReportController userAccountFinancialReportController;
    private ViewManagerModel viewManager;

    /**
     * Constructs a new {@code UserAccountFinancialReportView} with the specified view model,
     * controller, and view manager. Sets up the UI components and listens for changes in the view model.
     *
     * @param viewModel the {@link UserAccountFinancialReportViewModel} that holds the data for the view
     * @param userAccountFinancialReportController the {@link UserAccountFinancialReportController} that handles user actions
     * @param viewManager the {@link ViewManagerModel} that manages different views in the application
     */
    public UserAccountFinancialReportView(UserAccountFinancialReportViewModel viewModel,
                                          UserAccountFinancialReportController userAccountFinancialReportController,
                                          ViewManagerModel viewManager) {
        super("Financial Report");
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);
        this.userAccountFinancialReportController = userAccountFinancialReportController;

        this.userAccountFinancialReportPanel = new UserAccountFinancialReportPanel(
                this.viewModel,
                this.userAccountFinancialReportController,
                this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the {@link UserAccountFinancialReportPanel}
     * to the center of the frame.
     */
    private void setupUI() {
        this.getContentPane().add(this.userAccountFinancialReportPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model. This method is called whenever
     * a bound property is changed.
     *
     * @param evt the {@link PropertyChangeEvent} object containing the event details
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountFinancialReportState state = (UserAccountFinancialReportState) evt.getNewValue();
    }

    /**
     * Makes the view visible and triggers the controller to execute the financial report generation
     * when the view becomes visible. It also clears the fields in the periodic transaction panel.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            String id = this.viewManager.getUserId();
            this.userAccountFinancialReportController.execute(id);
        }
    }
}
