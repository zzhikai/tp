package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.AppUtil.checkArgument;

/**
 * Represents a Applicant's round in the linkedout app.
 * Guarantees: immutable; is valid as declared in {@link #isValidRound(String)}
 */
public class Round {

    public static final String MESSAGE_CONSTRAINTS = "Rounds can only be alphanumeric and it should not be blank";

    /*
     * The first character of the round must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Round}.
     *
     * @param round A valid round.
     */
    public Round(String round) {
        requireNonNull(round);
        checkArgument(isValidRound(round), MESSAGE_CONSTRAINTS);
        value = round;
    }

    /**
     * Returns true if a given string is a valid round.
     */
    public static boolean isValidRound(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Round // instanceof handles nulls
                && value.equals(((Round) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
