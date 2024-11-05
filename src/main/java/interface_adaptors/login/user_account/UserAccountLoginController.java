package interface_adaptors.login.user_account;

import interface_adaptors.login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import use_case.login.user_account.UserAccountLoginInputData;
import use_case.login.user_account.UserAccountLoginMediator;

/**
 * The {@code UserAccountLoginController} class is responsible for handling user interactions
 * related to the login process for user accounts. It communicates with the use case interactor (mediator)
 * to execute the login process based on the provided user credentials.
 *
 * <p>This controller is part of the interface adapters layer in a clean architecture, ensuring that
 * user inputs are correctly passed to the application logic without direct dependency on the
 * use case layer.</p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")  // Allow all origins temporarily for testing
public class UserAccountLoginController extends LoginController<UserAccountLoginMediator> {

//    private final UserAccountLoginInputBoundary loginInteractor;
//    private final UserAccountLoginOutputBoundaryTemp presenter;

    /**
     * Constructs a {@code UserAccountLoginController} object with the specified use case interactor (mediator).
     *
     * @param loginMediator the use case interactor (mediator) for user login
     */
    @Autowired
    public UserAccountLoginController(UserAccountLoginMediator loginMediator) {
        super(loginMediator);
//        this.presenter = new UserAccountLoginPresenterTemp();
//        this.loginInteractor = loginInteractor;
//        this.loginInteractor.setOutputBoundary(presenter);
    }



    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserAccountLoginInputData loginInputData) {
        // Call the execute method with the extracted data
        this.execute(loginInputData.getIdentification(), loginInputData.getPassword());

        return ResponseEntity.ok("Success!");


    }

    /**
     * Executes the login process with the given user details.
     *
     * @param id       the identification of the user
     * @param password the password of the user
     */
    @Override
    public void execute(String id, String password) {
        UserAccountLoginInputData loginInputData = new UserAccountLoginInputData(id, password);
        this.loginMediator.execute(loginInputData);
    }
}
