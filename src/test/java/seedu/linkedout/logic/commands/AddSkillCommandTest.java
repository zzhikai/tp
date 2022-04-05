package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_MARKETING;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.testutil.ApplicantBuilder;
import seedu.linkedout.testutil.ApplicantUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddSkillCommand}
 */
public class AddSkillCommandTest {

    private Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());

    @Test
    public void execute_addExistingSkills_success() {
        Applicant firstApplicant = model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        ApplicantBuilder applicantInList = new ApplicantBuilder(firstApplicant);
        Applicant editedApplicant = applicantInList.withSkills(VALID_SKILL_JAVA, VALID_SKILL_PYTHON).build();

        AddSkillCommand addSkillCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA, VALID_SKILL_PYTHON}));

        String expectedMessage = String.format(AddSkillCommand.MESSAGE_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new Linkedout(model.getLinkedout()), new UserPrefs());
        expectedModel.setApplicant(model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased()),
                editedApplicant);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNewSkills_success() {
        Applicant firstApplicant = model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        ApplicantBuilder applicantInList = new ApplicantBuilder(firstApplicant);
        Applicant editedApplicant = applicantInList.withSkills(VALID_SKILL_JAVA, VALID_SKILL_PYTHON,
                VALID_SKILL_MARKETING).build();

        AddSkillCommand addSkillCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_MARKETING}));

        String expectedMessage = String.format(AddSkillCommand.MESSAGE_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new Linkedout(model.getLinkedout()), new UserPrefs());
        expectedModel.setApplicant(model.getDefaultApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased()),
                editedApplicant);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidApplicantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDefaultApplicantList().size() + 1);
        AddSkillCommand addSkillCommand = new AddSkillCommand(outOfBoundIndex,
                ApplicantUtil.getApplicantNewSkills(new String[0]));

        assertCommandFailure(addSkillCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddSkillCommand addSkillFirstCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA}));
        AddSkillCommand addSkillSecondCommand = new AddSkillCommand(INDEX_SECOND_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA}));
        AddSkillCommand addSkillThirdCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_PYTHON}));

        // same object -> returns true
        assertTrue(addSkillFirstCommand.equals(addSkillFirstCommand));

        // same values -> returns true
        AddSkillCommand addSkillFirstCommandCopy = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA}));
        assertTrue(addSkillFirstCommand.equals(addSkillFirstCommandCopy));

        // different types -> returns false
        assertFalse(addSkillFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addSkillFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(addSkillFirstCommand.equals(addSkillSecondCommand));

        // different skill -> returns false
        assertFalse(addSkillFirstCommand.equals(addSkillThirdCommand));
    }
}
