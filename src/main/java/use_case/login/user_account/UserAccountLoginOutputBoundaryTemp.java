package use_case.login.user_account;

import use_case.login.LoginOutputData;

public interface UserAccountLoginOutputBoundaryTemp {
    LoginOutputData prepareSuccessView(LoginOutputData outputData);
    LoginOutputData prepareFailView(String errorMessage);
}
