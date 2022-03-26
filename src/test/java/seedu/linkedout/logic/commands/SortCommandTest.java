package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.CARL;
import static seedu.linkedout.testutil.TypicalApplicants.ELLE;
import static seedu.linkedout.testutil.TypicalApplicants.FIONA;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Order;
import seedu.linkedout.model.applicant.SortComparator;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLinkedout(), new UserPrefs());

    @Test
    public void equals() {
        SortComparator firstComparator = new SortComparator("NAME", new Order("asc"));
        SortComparator secondComparator = new SortComparator("NAME", new Order("asc"));
        
        SortCommand sortFirstCommand = new SortCommand(firstComparator);
        SortCommand sortSecondCommand = new SortCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

//    @Test
//    public void execute_zeroKeywords_noApplicantFound() {
//        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsComparator comparator = prepareComparator(" ");
//        SortCommand command = new SortCommand(comparator);
//        expectedModel.updateFilteredApplicantList(comparator);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multipleApplicantsFound() {
//        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsComparator comparator = prepareComparator("Kurz Elle Kunz");
//        SortCommand command = new SortCommand(comparator);
//        expectedModel.updateFilteredApplicantList(comparator);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsComparator}.
//     */
//    private NameContainsKeywordsComparator prepareComparator(String userInput) {
//        return new NameContainsKeywordsComparator(Arrays.asList(userInput.split("\\s+")));
//    }
}
