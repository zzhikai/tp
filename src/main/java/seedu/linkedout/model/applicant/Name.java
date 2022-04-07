package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's name in the linkedout app.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {



    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain letters,spaces, and the following special characters "
                    + "(excluding parentheses) -> " + "(' -)"
                    + "\nName should not be blank";

    /*
     * Validation regex to use for matching names
     * allows special letters such as ç and (' -)
     */
    public static final String VALIDATION_REGEX = "^[\\p{L} .'-]+$";



    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name.trim().replaceAll(" +", " ");
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public int compareTo(Name other) {
        return this.toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }
}
