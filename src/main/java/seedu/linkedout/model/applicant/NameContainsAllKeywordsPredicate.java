package seedu.linkedout.model.applicant;

import java.util.Arrays;


/**
 * Tests that a {@code Applicant}'s {@code Name} matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate extends KeywordsPredicate {
    private final String keywords;
    /**
     * @param keywords
     */
    public NameContainsAllKeywordsPredicate(String keywords) {
        super(Arrays.asList(new String[] {keywords}));
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
