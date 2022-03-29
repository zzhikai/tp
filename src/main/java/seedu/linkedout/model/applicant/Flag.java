package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Applicant's flagged status in the linkedout app.
 * Guarantees: immutable; is valid as declared.
 */
public class Flag {
    public final Boolean value;

    /**
     * Constructs a {@code Flag}.
     *
     * @param value A valid boolean.
     */
    public Flag(Boolean value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        if (!value) {
            return "Not flagged";
        } else {
            return "Flagged";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Flag // instanceof handles nulls
                && this.value == ((Flag) other).value);
    }
}
