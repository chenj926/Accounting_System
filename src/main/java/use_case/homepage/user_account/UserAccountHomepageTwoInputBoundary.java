package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoInputBoundary;

/**
 * Input boundary interface for processing the second homepage view use case specifically for user accounts.
 * <p>
 * This interface extends {@link HomepageTwoInputBoundary} and specifies the input type as
 * {@link UserAccountHomepageTwoInputData}. It is used to trigger the use case logic for generating the homepage view
 * related to user accounts.
 * </p>
 *
 * @see UserAccountHomepageTwoInputData
 * @see HomepageTwoInputBoundary
 *
 * @author Eric
 */
public interface UserAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<UserAccountHomepageTwoInputData> {
}
