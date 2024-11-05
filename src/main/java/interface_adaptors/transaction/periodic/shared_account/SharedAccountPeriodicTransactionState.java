package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionState;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionState;

import java.util.Set;

/**
 * The {@code SharedAccountPeriodicTransactionState} class extends the {@link UserAccountPeriodicTransactionState}
 * class to manage additional information specific to shared account periodic transactions. This class adheres
 * to Clean Architecture principles by encapsulating the state management of shared account transactions,
 * thereby maintaining a clear separation between the business logic and the user interface.
 *
 * <p>In addition to tracking standard periodic transaction details, this class manages the IDs of users
 * responsible for the transaction, enabling it to handle scenarios specific to shared accounts.</p>
 *
 * <p>This state class is critical for managing the state of shared account periodic transactions, including
 * transaction details, success or error messages, and user-specific information. By centralizing this state
 * management, the class promotes clean and maintainable code.</p>
 *
 * <p>The {@code SharedAccountPeriodicTransactionState} class is authored by Xile Chen and aligns with Clean
 * Architecture principles to ensure that the application remains scalable and easy to maintain.</p>
 *
 * @see UserAccountPeriodicTransactionState
 * @see Set
 */
public class SharedAccountPeriodicTransactionState extends PeriodicTransactionState {
    public SharedAccountPeriodicTransactionState() {
        super();
    }
}
