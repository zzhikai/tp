package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.linkedout.testutil.ApplicantBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        seedu.linkedout.model.applicant.NameContainsKeywordsPredicate firstPredicate =
                new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        seedu.linkedout.model.applicant.NameContainsKeywordsPredicate secondPredicate =
                new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        seedu.linkedout.model.applicant.NameContainsKeywordsPredicate firstPredicateCopy =
                new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        seedu.linkedout.model.applicant.NameContainsKeywordsPredicate predicate =
                new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        seedu.linkedout.model.applicant.NameContainsKeywordsPredicate predicate =
                new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new seedu.linkedout.model.applicant.NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email, job and round, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com",
                "Engineer", "Interview"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withJob("Engineer").withRound("Interview").build()));
    }
}
