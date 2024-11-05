package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.TransactionViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code OneTimeTransactionViewModel} class extends {@code TransactionViewModel}
 * to manage the state and labels specific to one-time transactions. It provides additional
 * labels and state management tailored to the needs of the one-time transaction view,
 * including the transaction date.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the view reflects the current state of a one-time transaction and supports property
 * change notifications to keep the view updated.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Xile Chen, Eric Chen, Dana Huang
 * </p>
 *
 * @param <S> the type of {@code OneTimeTransactionState} managed by this view model.
 */
public abstract class OneTimeTransactionViewModel<S extends OneTimeTransactionState> extends TransactionViewModel {
    protected final String TITLE_LABEL = "One Time Transaction";
    protected final String DATE = "Transaction Date";

    protected S state;
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a {@code OneTimeTransactionViewModel} object with the view name set to "One Time Transaction".
     *
     * @param viewName the name of the view associated with this view model.
     */
    public OneTimeTransactionViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() {
        return this.TITLE_LABEL;
    }

    /**
     * Gets the date label.
     *
     * @return the date label
     */
    public String getDate() {
        return this.DATE;
    }

    /**
     * Gets the category label.
     *
     * @return the category label
     */
    public String getCategoryButton() {
        return this.CATEGORY_BUTTON;
    }

    /**
     * Gets the current one-time transaction state.
     *
     * @return the current one-time transaction state
     */
    public S getState() {
        return this.state;
    }

    /**
     * Sets the current one-time transaction state.
     *
     * @param state the new one-time transaction state
     */
    public void setState(S state) {
        this.state = state;
    }

    /**
     * Notifies listeners that the one-time transaction state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
     * Resets the one-time transaction state to default values.
     * Subclasses should implement this method to reset the state according to their specific requirements.
     */
    public abstract void resetState();

}
