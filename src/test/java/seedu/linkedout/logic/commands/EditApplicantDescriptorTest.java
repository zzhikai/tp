package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_ROUND_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.linkedout.testutil.EditApplicantDescriptorBuilder;

public class EditApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicantDescriptor descriptorWithSameValues = new EditApplicantDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditApplicantDescriptor editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different job -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withJob(VALID_JOB_BOB).build();

        // different round -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withRound(VALID_ROUND_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withSkills(VALID_SKILL_PYTHON).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
