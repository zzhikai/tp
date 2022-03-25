package seedu.linkedout.model.applicant;

/**
 * Represents a Applicant's flagged status in the linkedout app.
 * Guarantees: immutable; is valid as declared.
 */
public class Flag {
    public final Boolean value;

    public Flag(Boolean value) {
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
