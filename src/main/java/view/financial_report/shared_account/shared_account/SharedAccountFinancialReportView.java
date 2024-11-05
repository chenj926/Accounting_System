package view.financial_report.shared_account.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportState;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code SharedAccountFinancialReportView} class represents the user interface for displaying the financial
 * report of a shared account. This class is responsible for setting up the UI components, managing the display
 * of financial data, and interacting with the {@link SharedAccountFinancialReportController} to fetch and update
 * report data.
 * <p>
 * The view observes changes in the {@link SharedAccountFinancialReportViewModel} and updates itself accordingly
 * when the underlying data changes. This class is part of the presentation layer in the Clean Architecture,
 * maintaining a clear separation between the view, the controller, and the view model.
 * </p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountFinancialReportView extends JFrame implements PropertyChangeListener {
    private SharedAccountFinancialReportPanel sharedAccountFinancialReportPanel;
    private final SharedAccountFinancialReportViewModel viewModel;
    private final SharedAccountFinancialReportController controller;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a new {@code SharedAccountFinancialReportView} with the specified view model, controller, and view manager.
     *
     * @param viewModel  the view model that this view observes
     * @param controller the controller that handles user interactions and manages the logic for the shared account financial report
     * @param viewManager the view manager that handles navigation between different views
     */
    public SharedAccountFinancialReportView(SharedAccountFinancialReportViewModel viewModel,
                                            SharedAccountFinancialReportController controller,
                                            ViewManagerModel viewManager){
        super("Shared Account Financial Report");
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);
        this.controller = controller;

        this.sharedAccountFinancialReportPanel = new SharedAccountFinancialReportPanel(this.viewModel, this.controller, this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the {@link SharedAccountFinancialReportPanel} to the frame's content pane.
     */
    private void setupUI() {
        this.getContentPane().add(this.sharedAccountFinancialReportPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model. Specifically, it listens for changes in the state of the
     * {@link SharedAccountFinancialReportViewModel} and updates the view accordingly.
     *
     * @param evt the event that describes the property change
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountFinancialReportState state = (SharedAccountFinancialReportState) evt.getNewValue();
    }

    /**
     * Makes the view visible and triggers the controller to execute the necessary actions when the view becomes visible.
     * It also clears the fields in the financial report panel when the view is made visible.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            String id = this.viewManager.getUserId();
            this.controller.execute(id);
        }
    }

}
