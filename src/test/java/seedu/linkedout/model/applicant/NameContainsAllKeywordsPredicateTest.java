package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.linkedout.testutil.ApplicantBuilder;

public class NameContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "Steve Job";
        String secondPredicateKeyword = "Steve";

        NameContainsAllKeywordsPredicate firstPredicate =
                new NameContainsAllKeywordsPredicate(firstPredicateKeyword);
        NameContainsAllKeywordsPredicate secondPredicate =
                new NameContainsAllKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsAllKeywordsPredicate firstPredicateCopy =
                new NameContainsAllKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // not exact keyword -> returns false
        assertFalse(firstPredicate.equals("Steve J"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsAllKeywords_returnsTrue() {
        // Exact keyword
        NameContainsAllKeywordsPredicate predicate =
                new NameContainsAllKeywordsPredicate("Alice Bob");
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Case-insensitive keywords
        predicate = new NameContainsAllKeywordsPredicate("alice bob");
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate("AliCe BoB");
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainAllKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate =
                new NameContainsAllKeywordsPredicate(" ");
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").build()));

        // Not exact keyword
        predicate = new NameContainsAllKeywordsPredicate("Alice");
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Exact keyword without space
        predicate = new NameContainsAllKeywordsPredicate("AliceBob");
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));
    }
}
