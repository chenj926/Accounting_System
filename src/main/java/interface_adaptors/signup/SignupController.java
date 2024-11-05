package interface_adaptors.signup;

import use_case.signup.*;

/**
 * The {@code SignupController} class is responsible for managing user interactions
 * related to the signup process. It acts as a mediator between the user interface
 * and the use case interactor, executing the signup logic as dictated by the application’s
 * business rules.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, facilitating
 * the flow of information between the user interface and the underlying use case interactor.
 * It ensures that the business logic is triggered correctly in response to user actions.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 *
 * @param <InputBoundary> the type of {@code SignupInputBoundary} used by this controller.
 * @param <Interactor>    the type of {@code SignupInteractor} used by this controller.
 */
public abstract class SignupController<
        InputBoundary extends SignupInputBoundary,
        Interactor extends SignupInteractor> {
    protected final InputBoundary signupInteractor;  // General interactor for standard signup

    /**
     * Constructs a {@code SignupController} object with the specified use case interactor.
     *
     * @param signupInteractor the use case interactor responsible for handling the standard user signup.
     */
    public SignupController(Interactor signupInteractor) {
        this.signupInteractor = (InputBoundary) signupInteractor;
    }
}

