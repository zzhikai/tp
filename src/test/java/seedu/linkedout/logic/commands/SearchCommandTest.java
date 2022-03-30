package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.BENSON;
import static seedu.linkedout.testutil.TypicalApplicants.CARL;
import static seedu.linkedout.testutil.TypicalApplicants.DANIEL;
import static seedu.linkedout.testutil.TypicalApplicants.ELLE;
import static seedu.linkedout.testutil.TypicalApplicants.FIONA;
import static seedu.linkedout.testutil.TypicalApplicants.GEORGE;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.ApplicantContainsSkillKeywordsPredicate;
import seedu.linkedout.model.applicant.JobContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.KeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.RoundContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLinkedout(), new UserPrefs());

    @Test
    public void equals() {
        List<KeywordsPredicate> firstKeywordsPredicateList = new ArrayList<>();
        List<KeywordsPredicate> secondKeywordsPredicateList = new ArrayList<>();

        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        firstKeywordsPredicateList.add(firstPredicate);
        secondKeywordsPredicateList.add(secondPredicate);

        SearchCommand searchFirstCommand = new SearchCommand(firstKeywordsPredicateList);
        SearchCommand searchSecondCommand = new SearchCommand(secondKeywordsPredicateList);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstKeywordsPredicateList);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        JobContainsKeywordsPredicate jobPredicate = prepareJobPredicate(" ");
        RoundContainsKeywordsPredicate roundPredicate = prepareRoundPredicate(" ");
        ApplicantContainsSkillKeywordsPredicate skillPredicate = prepareSkillPredicate(" ");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        Collections.addAll(keywordPredicate, namePredicate, jobPredicate, roundPredicate, skillPredicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDefaultApplicantList());

    }

    @Test
    public void executeOnName_multipleKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        keywordPredicate.add(predicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getDefaultApplicantList());
    }

    @Test
    public void executeOnJob_multipleKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 6);
        JobContainsKeywordsPredicate predicate = prepareJobPredicate("Developer Engineer");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        keywordPredicate.add(predicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getDefaultApplicantList());
    }

    @Test
    public void executeOnRound_multipleKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        RoundContainsKeywordsPredicate predicate = prepareRoundPredicate("Job Test");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        keywordPredicate.add(predicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA), model.getDefaultApplicantList());
    }

    @Test
    public void executeOnSkill_multipleSkills_applicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        ApplicantContainsSkillKeywordsPredicate predicate = prepareSkillPredicate("Photography Videography");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        keywordPredicate.add(predicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getDefaultApplicantList());
    }

    @Test
    public void execute_multipleAttributes_applicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        ApplicantContainsSkillKeywordsPredicate keywordsPredicate = prepareSkillPredicate("Photography");
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Benson");
        List<KeywordsPredicate> keywordPredicate = new ArrayList<>();
        keywordPredicate.add(keywordsPredicate);
        keywordPredicate.add(namePredicate);
        SearchCommand command = new SearchCommand(keywordPredicate);
        expectedModel.updateSearchedApplicantList(keywordPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getDefaultApplicantList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code JobContainsKeywordsPredicate}.
     */
    private JobContainsKeywordsPredicate prepareJobPredicate(String userInput) {
        return new JobContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RoundContainsKeywordsPredicate}.
     */
    private RoundContainsKeywordsPredicate prepareRoundPredicate(String userInput) {
        return new RoundContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ApplicantContainsSkillKeywordsPredicate}.
     */
    private ApplicantContainsSkillKeywordsPredicate prepareSkillPredicate(String userInput) {
        return new ApplicantContainsSkillKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
