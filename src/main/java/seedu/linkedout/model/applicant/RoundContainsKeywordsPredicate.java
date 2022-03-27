package seedu.linkedout.model.applicant;
import java.util.List;

import seedu.linkedout.commons.util.StringUtil;

/**
 * Tests that a {@code Applicant}'s {@code Round} matches any of the keywords given.
 */
public class RoundContainsKeywordsPredicate extends KeywordsPredicate {

    private final List<String> keywords;

    /**
     * @param keywords
     */
    public RoundContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(applicant.getRound().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoundContainsKeywordsPredicate // instanceof handles nulls
                && keywords.toString().equalsIgnoreCase(((
                RoundContainsKeywordsPredicate) other).keywords.toString())); // state check
    }

}
