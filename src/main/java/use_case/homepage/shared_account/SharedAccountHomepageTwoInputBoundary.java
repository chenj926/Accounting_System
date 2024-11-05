package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoInputBoundary;

/**
 * Input boundary interface for processing the second homepage view use case specifically for shared accounts.
 * <p>
 * This interface extends {@link HomepageTwoInputBoundary} and specifies the input type as
 * {@link SharedAccountHomepageTwoInputData}. It is used to trigger the use case logic for generating the homepage view
 * related to shared accounts.
 * </p>
 *
 * @see SharedAccountHomepageTwoInputData
 * @see HomepageTwoInputBoundary
 *
 * @author Eric
 */
public interface SharedAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<SharedAccountHomepageTwoInputData> {
}
