package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.SignupOutputData;

/**
 * The {@code SignupPresenter} class is responsible for handling the presentation logic
 * related to the signup process. It interacts with the view model to update the signup
 * state and manage view transitions based on the outcome of the signup attempt.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the user interface reflects the current state of the application without directly
 * depending on the business logic. It helps maintain a clear separation of concerns
 * by delegating the business logic to the use case interactor.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 *
 * @param <V> the type of {@code SignupViewModel} used by this presenter.
 * @param <O> the type of {@code SignupOutputData} used by this presenter.
 * @param <S> the type of {@code SignupState} managed by the associated view model.
 */
public abstract class SignupPresenter<
        V extends SignupViewModel<S>,
        O extends SignupOutputData,
        S extends SignupState> {
    protected final ViewManagerModel viewManagerModel;
    protected final V accountSignupViewModel;

    /**
     * Constructs a {@code SignupPresenter} object with the specified view manager model
     * and signup view model.
     *
     * @param viewManagerModel        the view manager model responsible for managing view transitions.
     * @param accountSignupViewModel  the signup view model used to update the signup state.
     */
    public SignupPresenter(ViewManagerModel viewManagerModel, V accountSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountSignupViewModel = accountSignupViewModel;
    }

    /**
     * Prepares the success view with the given signup output data.
     * This method should be implemented by subclasses to handle successful signup scenarios.
     *
     * @param accountSignupViewModel the signup output data containing information to be presented upon a successful signup.
     */
    public abstract void prepareSuccessView(O accountSignupViewModel);

    /**
     * Prepares the fail view with the given error message.
     * This method updates the signup state with the provided error message and clears any previous success message,
     * ensuring that the view accurately reflects the failure state.
     *
     * @param error the error message to be presented in case of a failed signup attempt.
     */
    public void prepareFailView(String error) {
        S accountSignupState = this.accountSignupViewModel.getState();
        accountSignupState.setStateError(error);
        accountSignupState.setSuccessMsg(null); // Clear success message on failure
        this.accountSignupViewModel.firePropertyChanged();
    }
}
