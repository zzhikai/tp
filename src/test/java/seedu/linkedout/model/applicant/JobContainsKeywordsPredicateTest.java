package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.linkedout.testutil.ApplicantBuilder;

public class JobContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("engineer");
        List<String> secondPredicateKeywordList = Arrays.asList("engineer", "pilot");

        JobContainsKeywordsPredicate firstPredicate =
                new JobContainsKeywordsPredicate(firstPredicateKeywordList);
        JobContainsKeywordsPredicate secondPredicate =
                new JobContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobContainsKeywordsPredicate firstPredicateCopy =
                new JobContainsKeywordsPredicate(firstPredicateKeywordList);
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
        JobContainsKeywordsPredicate predicate =
                new JobContainsKeywordsPredicate(Collections.singletonList("Engineer"));
        assertTrue(predicate.test(new ApplicantBuilder().withJob("Engineer Intern").build()));

        // Multiple keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Engineer", "Intern"));
        assertTrue(predicate.test(new ApplicantBuilder().withJob("Engineer Intern").build()));

        // Only one matching keyword
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Software", "Engineer"));
        assertTrue(predicate.test(new ApplicantBuilder().withJob("Software Developer").build()));

        // Mixed-case keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("SoFTWare", "EnGinEER"));
        assertTrue(predicate.test(new ApplicantBuilder().withJob("Software Engineer").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        JobContainsKeywordsPredicate predicate =
                new JobContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withJob("Pilot").build()));

        // Non-matching keyword
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Pilot"));
        assertFalse(predicate.test(new ApplicantBuilder().withJob("Software Engineer").build()));

        // Keywords match name ,phone, email and stage, but does not match job
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Interview"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withJob("Engineer").withRound("Interview").build()));
    }
}
