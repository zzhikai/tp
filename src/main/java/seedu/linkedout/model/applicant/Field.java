package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's name in the linkedout app.
 * Guarantees: immutable; is valid as declared in {@link #isValidField(String)}
 */
public class Field {
    public static final String NAME_FIELD = "NAME";
    public static final String JOB_FIELD = "JOB";
    public static final String MESSAGE_CONSTRAINTS =
            "Field should either be 'NAME' or 'JOB' for sorting (case-insensitive)";

    public final String sortField;

    /**
     * Constructs a {@code Field}.
     *
     * @param field A valid field.
     */
    public Field(String field) {
        requireNonNull(field);
        checkArgument(isValidField(field), MESSAGE_CONSTRAINTS);
        sortField = field.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidField(String test) {
        String fieldUpperCase = test.toUpperCase();
        if (fieldUpperCase.equals(NAME_FIELD) || fieldUpperCase.equals(JOB_FIELD)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return sortField;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Field // instanceof handles nulls
                && sortField.equals(((Field) other).sortField)); // state check
    }

    @Override
    public int hashCode() {
        return sortField.hashCode();
    }

}
