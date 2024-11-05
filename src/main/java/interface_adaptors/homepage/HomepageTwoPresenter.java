package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.HomepageTwoOutputBoundary;
import use_case.homepage.HomepageTwoOutputData;

/**
 * The {@code HomepageTwoPresenter} class serves as an abstract presenter that processes the output
 * data from the use case and updates the view model accordingly. It is responsible for preparing
 * the success or failure view by setting the appropriate state in the view model and managing
 * transitions through the view manager.
 *
 * <p>This class follows Clean Architecture principles by decoupling the business logic from the
 * user interface, allowing the presenter to handle the data presentation logic independently of
 * the view's implementation details.</p>
 *
 * @param <O> the type of output data handled by this presenter
 * @param <V> the type of view model updated by this presenter
 * @param <S> the type of state managed by the view model
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public abstract class HomepageTwoPresenter<
        O extends HomepageTwoOutputData,
        V extends HomepageTwoViewModel<S>,
        S extends HomepageTwoState> implements HomepageTwoOutputBoundary<O> {

    /**
     * The basic user information array that is populated from the output data.
     */
    protected String[] basicUserInfo;
    /**
     * The view model that this presenter updates with the results of the business logic operations.
     */
    protected final V viewModel;
    /**
     * The view manager responsible for handling view transitions and updating the overall user interface state.
     */
    protected final ViewManagerModel viewManager;

    /**
     * Constructs a {@code HomepageTwoPresenter} with the specified view model and view manager.
     *
     * @param viewModel   the view model that this presenter updates
     * @param viewManager the view manager that handles view transitions
     */
    public HomepageTwoPresenter(V viewModel, ViewManagerModel viewManager){
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Prepares the view to display a success message by updating the state with the output data.
     * This method sets the basic user information in the state and notifies the view model of the change.
     *
     * @param outputData the output data containing the basic user information
     */
    @Override
    public void prepareSuccessView(O outputData) {
        S state = this.viewModel.getState();
        this.basicUserInfo = outputData.getBasicUserInfo();

        state.setBasicUserInfo(this.basicUserInfo);
        state.setErr(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setBasicUserInfo(state.getBasicUserInfo());
        this.viewManager.setBasicUserInfo(this.basicUserInfo);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Prepares the view to display a failure message by updating the state with the error.
     *
     * @param err the error message to be displayed
     */
    @Override
    public void prepareFailView(String err) {
        S state = (S) this.viewModel.getState();
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Returns the basic user information that was set in the view model.
     *
     * @return the basic user information array
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }
}
