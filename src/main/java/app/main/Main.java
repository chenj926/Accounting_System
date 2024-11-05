package app.main;

import interface_adaptors.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Main class is the entry point for the application. It initializes the Swing GUI
 * and manages the primary view using a {@link ViewManagerModel}.
 * <p>
 * It sets up the initial view and listens for changes in the active view name, switching
 * views accordingly.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
@SpringBootApplication
@ComponentScan(basePackages = {"app.main", "interface_adaptors", "use_case.login.user_account"})
public class Main {

    /**
     * The main method is the entry point of the application. It sets up the Swing GUI and
     * initializes the {@link ViewManagerModel}. The initial view is set to "home page", and
     * the application listens for changes in the active view to switch views as needed.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {;
//
//            ViewManagerModel viewManagerModel = new ViewManagerModel();
//            viewManagerModel.changeView("home page");
//
//            // Listen for changes in the active view name and switch views accordingly
//            viewManagerModel.addPropertyChangeListener(evt -> {
//                String newViewName = (String) evt.getNewValue();
//                // Only pass the viewModel if it is needed for the new view
//                viewManagerModel.changeView(newViewName);
//            });
//        });
        SpringApplication.run(Main.class, args);
    }
}
