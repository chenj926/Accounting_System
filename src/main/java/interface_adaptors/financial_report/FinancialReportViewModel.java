package interface_adaptors.financial_report;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code FinancialReportViewModel} class is an abstract view model in the Clean Architecture,
 * responsible for managing the state and data associated with financial reports. It serves as a
 * bridge between the controller/presenter and the view, holding the financial report content and
 * notifying listeners of any state changes.
 *
 * <p><b>Author:</b> Dana Hunag</p>
 *
 * @param <S> the type of the state that represents the current state of the financial report
 */
public abstract class FinancialReportViewModel<S extends FinancialReportState> extends ViewModel {
    protected S state;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    protected final String TITLE_LABEL = "Financial Report";
    protected String reportContent;

    /**
     * Constructs a {@code FinancialReportViewModel} with the specified view name.
     * <p>
     * This constructor initializes the view model with the view name and prepares it to handle
     * property changes.
     * </p>
     *
     * @param viewName the name of the view associated with this view model
     */
    public FinancialReportViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Returns the title label for the financial report view.
     *
     * @return the title label string
     */
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }

    /**
     * Returns the content of the financial report.
     *
     * @return the report content as a string
     */
    public String getReportContent() {
        return this.reportContent;
    }

    /**
     * Gets the current state of the financial report.
     * <p>
     * This method is abstract and should be implemented by subclasses to return the specific
     * state associated with the financial report.
     * </p>
     *
     * @return the current state of the financial report
     */
    public abstract S getState();

    /**
     * Sets the state of the financial report.
     * <p>
     * This method is abstract and should be implemented by subclasses to update the state
     * of the financial report.
     * </p>
     *
     * @param state the new state of the financial report
     */
    public abstract void setState(S state);

    /**
     * Resets the state of the financial report.
     * <p>
     * This method is abstract and should be implemented by subclasses to reset the state
     * to its initial values.
     * </p>
     */
    public abstract void resetState();

    /**
     * Sets the content of the financial report.
     *
     * @param reportContent the new report content as a string
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * Adds a property change listener to this view model.
     * <p>
     * The listener will be notified of changes to properties of this view model, allowing
     * the view to react to updates in the state or data.
     * </p>
     *
     * @param listener the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event, notifying all registered listeners of a change in the state.
     * <p>
     * This method should be called whenever the state is updated, ensuring that the view
     * reflects the latest data.
     * </p>
     */
    public abstract void firePropertyChange();
}
