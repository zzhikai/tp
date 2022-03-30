package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_FLAG_VALUE_TRUE;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FlagCommand}.
 */
public class FlagCommandTest {
    private Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToFlag = model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        Applicant flaggedApplicant = new ApplicantBuilder(applicantToFlag).withFlag(VALID_FLAG_VALUE_TRUE).build();
        FlagCommand flagCommand = new FlagCommand(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(FlagCommand.MESSAGE_FLAG_APPLICANT_SUCCESS, applicantToFlag);

        ModelManager expectedModel = new ModelManager(model.getLinkedout(), new UserPrefs());
        expectedModel.flagApplicant(applicantToFlag, flaggedApplicant);

        assertCommandSuccess(flagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDefaultApplicantList().size() + 1);
        FlagCommand flagCommand = new FlagCommand(outOfBoundIndex);

        assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Applicant applicantToFlag = model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        Applicant flaggedApplicant = new ApplicantBuilder(applicantToFlag).withFlag(VALID_FLAG_VALUE_TRUE).build();
        FlagCommand flagCommand = new FlagCommand(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(FlagCommand.MESSAGE_FLAG_APPLICANT_SUCCESS, applicantToFlag);

        ModelManager expectedModel = new ModelManager(model.getLinkedout(), new UserPrefs());
        expectedModel.flagApplicant(applicantToFlag, flaggedApplicant);

        assertCommandSuccess(flagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of linkedout app list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkedout().getApplicantList().size());

        FlagCommand flagCommand = new FlagCommand(outOfBoundIndex);

        assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FlagCommand flagFirstCommand = new FlagCommand(INDEX_FIRST_APPLICANT);
        FlagCommand flagSecondCommand = new FlagCommand(INDEX_SECOND_APPLICANT);

        // same object -> returns true
        assertTrue(flagFirstCommand.equals(flagFirstCommand));

        // same values -> returns true
        FlagCommand flagFirstCommandCopy = new FlagCommand(INDEX_FIRST_APPLICANT);
        assertTrue(flagFirstCommand.equals(flagFirstCommandCopy));

        // different types -> returns false
        assertFalse(flagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(flagFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(flagFirstCommand.equals(flagSecondCommand));
    }


}
