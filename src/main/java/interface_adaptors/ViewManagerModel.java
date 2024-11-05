package interface_adaptors;

import app.financial_report.SharedAccountFinancialReportUseCaseFactory;
import app.authentication.SharedAccountLoginUseCaseFactory;
import app.authentication.UserAccountLoginUseCaseFactory;
//import app.authentication.LogoutUseCaseFactory;
import app.authentication.SignupUseCaseFactory;
import app.home_page.HomePageUseCaseFactory;
import app.home_page.HomepageTwoUseCaseFactory;
import app.home_page.SharedAccountHomepageTwoUseCaseFactory;
import app.transaction.OneTimeTransactionUseCaseFactory;
import app.transaction.PeriodicTransactionUseCaseFactory;
//import app.transaction.TransactionUseCaseFactory;
import app.financial_report.FinancialReportUseCaseFactory;
import app.transaction.SharedAccountOneTimeTransactionUseCaseFactory;
import app.transaction.SharedAccountPeriodicTransactionUseCaseFactory;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;
//import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import view.financial_report.user_account.user_account.UserAccountFinancialReportView;
import view.financial_report.shared_account.shared_account.SharedAccountFinancialReportView;
import view.home_page.HomePageView;
import view.home_page.user_account.UserAccountHomepageTwoView;
import view.home_page.shared_account.SharedAccountHomepageTwoView;
import view.login.user_account.UserAccountLoginView;
import view.login.shared_account.SharedAccountLoginView; // Import the SharedAccountLoginView
//import view.logout.LogoutView;
import view.signup.user_account.UserAccountSignupView;
import view.signup.shared_account.SharedAccountSignupView;
//import view.transaction.TransactionView;
import view.transaction.one_time.user_account.UserAccountOneTimeTransactionView;
import view.transaction.one_time.shared_account.SharedAccountOneTimeTransactionView;
import view.transaction.periodic.user_account.UserAccountPeriodicTransactionView;
import view.transaction.periodic.shared_account.SharedAccountPeriodicTransactionView;
//import view.transaction.TransactionView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * The ViewManagerModel class manages the views in the application.
 * It keeps track of the current view and handles the switching between different views.
 *
 * @author Xile
 * @author Rita
 * @author Eric
 * @author Dana
 */
public class ViewManagerModel {

    private String userId;
    private String[] basicUserInfo;
//    private String sharedAccountId;
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JFrame currentView;
    private final Map<String, JFrame> views;
    private final Map<String, ViewModel> viewModels;

    private boolean isJavaFXInitialized = false;  // Flag to track JavaFX initialization

    /**
     * Constructs a ViewManagerModel object with no initial views.
     */
    public ViewManagerModel() {
        this.views = new HashMap<>();
        this.viewModels = new HashMap<>();
//        this.initViewModels();
    }

    // 2种方式，一种是set好viewModel， 然后新创建的时候，viewModel里就已近有了id/shareId
    //还有一种就是一个个加
    private void initViewModels() {
        // init every view in the beginning
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        this.viewModels.put("home page", homePageViewModel);

        UserAccountSignupViewModel userAccountSignupViewModel = new UserAccountSignupViewModel();
        this.viewModels.put("sign up", userAccountSignupViewModel);

        SharedAccountSignupViewModel sharedAccountSignupViewModel = new SharedAccountSignupViewModel();
        this.viewModels.put("shared account sign up", sharedAccountSignupViewModel);

        UserAccountLoginViewModel loginViewModel =  new UserAccountLoginViewModel();
        this.viewModels.put("log in", loginViewModel);

        SharedAccountLoginViewModel sharedAccountLoginViewModel = new SharedAccountLoginViewModel();
        this.viewModels.put("shared account log in", sharedAccountLoginViewModel);

        UserAccountHomepageTwoViewModel userAccountHomepageTwoViewModel = new UserAccountHomepageTwoViewModel();
        this.viewModels.put("Homepage Two", userAccountHomepageTwoViewModel);

        SharedAccountHomepageTwoViewModel sharedAccounthomepageTwoViewModel = new SharedAccountHomepageTwoViewModel();
        this.viewModels.put("Share Account Homepage Two", sharedAccounthomepageTwoViewModel);

        UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel = new UserOneTimeTransactionViewModel();
        this.viewModels.put("One Time Transaction", userAccountOneTimeTransactionViewModel);

        SharedAccountOneTimeTransactionViewModel sharedAccountOneTimeTransactionViewModel = new SharedAccountOneTimeTransactionViewModel();
        this.viewModels.put("Shared Account One Time Transaction", sharedAccountOneTimeTransactionViewModel);

        UserAccountPeriodicTransactionViewModel userAccountPeriodicTransactionViewModel = new UserAccountPeriodicTransactionViewModel();
        this.viewModels.put("Periodic Transaction", userAccountPeriodicTransactionViewModel);

        SharedAccountPeriodicTransactionViewModel sharedAccountperiodicTransactionViewModel = new SharedAccountPeriodicTransactionViewModel();
        this.viewModels.put("Shared Account Periodic Transaction", sharedAccountperiodicTransactionViewModel);

        UserAccountFinancialReportViewModel userAccountFinancialReportViewModel = new UserAccountFinancialReportViewModel();
        this.viewModels.put("Financial Report", userAccountFinancialReportViewModel);

        SharedAccountFinancialReportViewModel sharedAccountFinancialReportViewModel = new SharedAccountFinancialReportViewModel();
        this.viewModels.put("Shared Account Financial Report", sharedAccountFinancialReportViewModel);
    }

    public <V extends ViewModel> void updateViewModel(String viewModelName, V viewModel) {
        if (this.viewModels.containsKey(viewModelName)) {
            this.viewModels.put(viewModelName, viewModel);
        } else {
            throw new IllegalArgumentException("No ViewModel found with the name: " + viewModelName);
        }
    }

    /**
     * Gets the name of the active view.
     *
     * @return the name of the active view
     */
    public String getActiveViewName() {
        return activeViewName;
    }

    public ViewModel getViewModel(String viewModelName) {
        return this.viewModels.get(viewModelName);
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return this.userId;
    }

    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }

//    public String getSharedAccountId() {
//        return this.sharedAccountId;
//    }

    public Boolean isJavaFXInitialized() {
        return this.isJavaFXInitialized;
    }

    /**
     * Sets the name of the active view and notifies listeners of the change.
     *
     * @param activeViewName the name of the new active view
     */
    public void setActiveViewName(String activeViewName) {
        String oldActiveViewName = this.activeViewName;
        this.activeViewName = activeViewName;
        support.firePropertyChange("activeViewName", oldActiveViewName, this.activeViewName);
    }

    /**
     * Sets the user ID.
     *
     * @param userId the new user ID
     */
    public void setUserId(String userId) {
//        String oldUserId = this.userId;
        this.userId = userId;
//        support.firePropertyChange("userId", oldUserId, userId);
    }

    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

//    public void setSharedAccountId(String sharedAccountId) {
//        this.sharedAccountId = sharedAccountId;
//    }


    public void setJavaFXInitialized(boolean javaFXInitialized) {
        isJavaFXInitialized = javaFXInitialized;
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // Reset method to clear all state
    public void reset() {
        this.userId = null;
//        this.activeViewName = "home page"; // Reset to home page view
//        support.firePropertyChange("reset", null, null); // Notify listeners of reset
    }

    /**
     * Changes the current view to the specified view.
     *
     * @param viewName the name of the view to change to
     */
    public void changeView(String viewName) {
        if (currentView != null) {
            currentView.dispose();
        }
        currentView = views.get(viewName);
        if (currentView != null) {
            currentView.setVisible(true);
        } else {
            this.createView(viewName);
        }
        this.setActiveViewName(viewName);
    }

    /**
     * Creates a new view based on the specified view name.
     *
     * @param viewName the name of the view to create
     */
    private void createView(String viewName) {
        // 让他加了（新的viewModel）好像也没用……非常无奈啊
        switch (viewName) {
            case "home page":
                HomePageViewModel homePageViewModel =  new HomePageViewModel();
                HomePageView homePageView = HomePageUseCaseFactory.create(this, homePageViewModel);
                views.put("home page", homePageView);
                currentView = homePageView;
                break;
            case "sign up":
                UserAccountSignupViewModel userAccountSignupViewModel = new UserAccountSignupViewModel();
                UserAccountSignupView userAccountSignupView = SignupUseCaseFactory.create(this, userAccountSignupViewModel);
                views.put("sign up", userAccountSignupView);
                currentView = userAccountSignupView;
                break;
            case "shared account sign up":
                SharedAccountSignupViewModel sharedAccountSignupViewModel =
                        new SharedAccountSignupViewModel();
                SharedAccountSignupView sharedSignupView = SignupUseCaseFactory.createSharedAccount(this,
                        sharedAccountSignupViewModel);
                views.put("shared account sign up", sharedSignupView);
                currentView = sharedSignupView;
                break;
            case "log in":
                UserAccountLoginViewModel loginViewModel =  new UserAccountLoginViewModel();
                UserAccountLoginView userAccountLoginView = UserAccountLoginUseCaseFactory.create(this, loginViewModel);
                views.put("log in", userAccountLoginView);
                currentView = userAccountLoginView;
                break;
            case "shared account log in":
                SharedAccountLoginViewModel sharedAccountLoginViewModel =
                        new SharedAccountLoginViewModel();
                SharedAccountLoginView sharedAccountLoginView = SharedAccountLoginUseCaseFactory.create(this,
                        sharedAccountLoginViewModel);
                views.put("shared account log in", sharedAccountLoginView);
                currentView = sharedAccountLoginView;
                break;
            case "Homepage Two":
                UserAccountHomepageTwoViewModel userAccountHomepageTwoViewModel = new UserAccountHomepageTwoViewModel();
                UserAccountHomepageTwoView userAccountHomepageTwoView = HomepageTwoUseCaseFactory.create(this, userAccountHomepageTwoViewModel);
                views.put("Homepage Two", userAccountHomepageTwoView);
                currentView = userAccountHomepageTwoView;
                break;
            case "Shared Account Homepage Two":
                SharedAccountHomepageTwoViewModel sharedAccountHomepageTwoViewModel = new SharedAccountHomepageTwoViewModel();
                SharedAccountHomepageTwoView sharedAccountHomepageTwoView = SharedAccountHomepageTwoUseCaseFactory.create(this, sharedAccountHomepageTwoViewModel);
                views.put("Shared Account Homepage Two", sharedAccountHomepageTwoView);
                currentView = sharedAccountHomepageTwoView;
                break;
            case "One Time Transaction":
                UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel =
                       new UserOneTimeTransactionViewModel();
                UserAccountOneTimeTransactionView userAccountOneTimeTransactionView = OneTimeTransactionUseCaseFactory.create(
                        this, userAccountOneTimeTransactionViewModel);
                views.put("One Time Transaction", userAccountOneTimeTransactionView);
                currentView = userAccountOneTimeTransactionView;
                break;
            case "Shared Account One Time Transaction":
                SharedAccountOneTimeTransactionViewModel sharedAccountoneTimeTransactionViewModel =
                        new SharedAccountOneTimeTransactionViewModel();
                SharedAccountOneTimeTransactionView sharedAccountoneTimeTransactionView = SharedAccountOneTimeTransactionUseCaseFactory.create(
                        this, sharedAccountoneTimeTransactionViewModel);
                views.put("Shared Account One Time Transaction", sharedAccountoneTimeTransactionView);
                currentView = sharedAccountoneTimeTransactionView;
                break;
            case "Periodic Transaction":
                UserAccountPeriodicTransactionViewModel userAccountPeriodicTransactionViewModel =
                        new UserAccountPeriodicTransactionViewModel();
                UserAccountPeriodicTransactionView periodicTransactionView = PeriodicTransactionUseCaseFactory.create(
                        this, userAccountPeriodicTransactionViewModel);
                views.put("Periodic Transaction", periodicTransactionView);
                currentView = periodicTransactionView;
                break;
            case "Shared Account Periodic Transaction":
                SharedAccountPeriodicTransactionViewModel sharedAccountperiodicTransactionViewModel =
                        new SharedAccountPeriodicTransactionViewModel();
                SharedAccountPeriodicTransactionView sharedAccountperiodicTransactionView = SharedAccountPeriodicTransactionUseCaseFactory.create(
                        this, sharedAccountperiodicTransactionViewModel);
                views.put("Shared Account Periodic Transaction", sharedAccountperiodicTransactionView);
                currentView = sharedAccountperiodicTransactionView;
                break;
            case "Financial Report":
                UserAccountFinancialReportViewModel userAccountFinancialReportViewModel = new UserAccountFinancialReportViewModel();
                UserAccountFinancialReportView userAccountFinancialReportView = FinancialReportUseCaseFactory.create(this,
                        userAccountFinancialReportViewModel);
                views.put("Financial Report", userAccountFinancialReportView);
                currentView = userAccountFinancialReportView;
                break;
            case "Shared Account Financial Report":
                SharedAccountFinancialReportViewModel sharedAccountFinancialReportViewModel = new SharedAccountFinancialReportViewModel();
                SharedAccountFinancialReportView sharedAccountFinancialReportView = SharedAccountFinancialReportUseCaseFactory.create(this,
                        sharedAccountFinancialReportViewModel);
                views.put("Shared Account Financial Report", sharedAccountFinancialReportView);
                currentView = sharedAccountFinancialReportView;
                break;
        }
        if (currentView != null) {
            currentView.setVisible(true);
        }
        setActiveViewName(viewName);
    }
}
