package seedu.linkedout.model;

import javafx.collections.ObservableList;
import seedu.linkedout.model.person.Person;

/**
 * Unmodifiable view of an linkedout book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
