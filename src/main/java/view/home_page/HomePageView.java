package view.home_page;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The {@code HomePageView} class represents the graphical user interface for the home page of the application.
 * It extends {@link JFrame} and implements {@link PropertyChangeListener} to respond to changes in the
 * {@link HomePageViewModel}.
 * <p>
 * This class is part of the view layer in the Clean Architecture, adhering to the principles of separation of
 * concerns. It interacts with the {@link HomePageViewModel} to reflect the state of the application and with the
 * {@link ViewManagerModel} to manage view transitions.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Dana Huang</p>
 */
public class HomePageView extends JFrame implements PropertyChangeListener {
    private HomePageViewModel viewModel;
    private HomePagePanel homePagePanel;

    /**
     * Constructs a {@code HomePageView} object with the specified view model and view manager.
     * This constructor sets up the home page's UI components and initializes the window.
     *
     * @param viewModel   the view model for the home page view, providing data and state information
     * @param viewManager the view manager for handling view transitions within the application
     */
    public HomePageView(HomePageViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.homePagePanel = new HomePagePanel(viewModel, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setVisible(true);

    }

    /**
     * Sets up the UI components for the home page view by adding the {@link HomePagePanel} to the frame's content pane.
     */
    private void setupUI() {
        this.getContentPane().add(homePagePanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model. Currently, this method does not perform any actions
     * but can be extended in the future to handle specific property changes in the {@link HomePageViewModel}.
     *
     * @param evt the event that characterizes the change
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // do something with the propertyChange
    }
}


