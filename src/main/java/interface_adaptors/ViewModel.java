package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class serves as a base class for view models in the application.
 * It provides support for property change notifications and stores the name of the view.
 *
 * @author Rita
 * @author Xile
 */
public abstract class ViewModel {
    protected String viewName;
    private String id;
    private final PropertyChangeSupport propertyChangeSupport;

    /**
     * Constructs a ViewModel object with the specified view name.
     *
     * @param viewName the name of the view
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Gets the name of the view.
     *
     * @return the name of the view
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Fires a property change event to notify listeners that a property has changed.
     *
     * @param propertyName the name of the property that changed
     * @param oldValue     the old value of the property
     * @param newValue     the new value of the property
     */
    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

