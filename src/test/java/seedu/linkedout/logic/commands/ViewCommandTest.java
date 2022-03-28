package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.NameContainsAllKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLinkedout(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsAllKeywordsPredicate firstPredicate =
                new NameContainsAllKeywordsPredicate("first");
        NameContainsAllKeywordsPredicate secondPredicate =
                new NameContainsAllKeywordsPredicate("second");

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameContainsAllKeywordsPredicate predicate = prepareNamePredicate(" ");
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDefaultApplicantList());
    }

    @Test
    public void execute_exactKeywords_exactApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        NameContainsAllKeywordsPredicate predicate = prepareNamePredicate("Alice Pauline");
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getDefaultApplicantList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsAllKeywordsPredicate}.
     */
    private NameContainsAllKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsAllKeywordsPredicate(userInput);
    }
}
