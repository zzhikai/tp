package seedu.linkedout.model.applicant;

import java.util.List;
import java.util.function.Predicate;

import seedu.linkedout.commons.util.StringUtil;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends KeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    /**
     * @param keywords
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(applicant.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.toString().equalsIgnoreCase(((
                        NameContainsKeywordsPredicate) other).keywords.toString())); // state check
    }

    @Override
    public int numberOfKeywordMatches(Applicant applicant) {
        return (int) keywords.stream()
                .filter(keyword -> StringUtil.containsWordIgnoreCase(applicant.getName().fullName, keyword)).count();
    }

}
