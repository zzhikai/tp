package seedu.linkedout.model.applicant;
import java.util.List;

import seedu.linkedout.commons.util.StringUtil;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches any of the keywords given.
 */
public class JobContainsKeywordsPredicate extends KeywordsPredicate {

    private final List<String> keywords;

    /**
     * @param keywords
     */
    public JobContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(applicant.getJob().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobContainsKeywordsPredicate // instanceof handles nulls
                && keywords.toString().equalsIgnoreCase(((
                        JobContainsKeywordsPredicate) other).keywords.toString())); // state check
    }

}
