package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.TransactionViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code PeriodicTransactionViewModel} class extends {@code TransactionViewModel}
 * to manage the state and labels specific to periodic transactions. It provides additional
 * labels and state management tailored to the needs of the periodic transaction view,
 * including start date, end date, and period.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the view accurately reflects the current state of periodic transactions and supports
 * property change notifications to keep the view updated.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Xile Chen
 * </p>
 *
 * @param <S> the type of {@code PeriodicTransactionState} managed by this view model.
 */
public abstract class PeriodicTransactionViewModel<S extends PeriodicTransactionState> extends TransactionViewModel {
    // labels
    protected final String TITLE_LABEL = "Periodic Transaction";
    protected final String STARTDATE = "Transaction Start Date";
    protected final String ENDDATE = "Transaction End Date";
    protected final String PERIOD = "Period";

    protected S transactionState;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a {@code PeriodicTransactionViewModel} object with the specified view name.
     *
     * @param viewName the name of the view associated with this view model.
     */
    public PeriodicTransactionViewModel(String viewName) {
        super(viewName);
    }

    // getters
    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }

    /**
     * Gets the start date label.
     *
     * @return the start date label
     */
    public String getStartDate() {
        return this.STARTDATE;
    }

    /**
     * Gets the end date label.
     *
     * @return the end date label
     */
    public String getEndDate() {
        return this.ENDDATE;
    }

    /**
     * Gets the period label.
     *
     * @return the period label
     */
    public String getPeriod() {
        return this.PERIOD;
    }

    /**
     * Gets the current periodic transaction state.
     *
     * @return the current periodic transaction state
     */
    public S getState() {
        return this.transactionState;
    }

    public String getCategoryButton() {
        return this.CATEGORY_BUTTON;
    }

    // setters
    /**
     * Sets the current periodic transaction state.
     *
     * @param state the new periodic transaction state
     */
    public void setState(S state) {
        this.transactionState = state;
    }

    /**
     * Notifies listeners that the periodic transaction state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.transactionState);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the {@code PropertyChangeListener} to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the {@code PropertyChangeListener} to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Resets the periodic transaction state to default values.
     * Subclasses should implement this method to reset the state according to their specific requirements.
     */
    public abstract void resetState();

}


