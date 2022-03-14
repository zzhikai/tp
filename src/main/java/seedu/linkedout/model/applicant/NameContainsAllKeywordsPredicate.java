package seedu.linkedout.model.applicant;

import java.util.function.Predicate;


/**
 * Tests that a {@code Applicant}'s {@code Name} matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Applicant> {
    private final String keywords;

    public NameContainsAllKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.equalsIgnoreCase(applicant.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equalsIgnoreCase(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
