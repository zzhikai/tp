package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_ROUND_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.linkedout.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Applicant applicant = new ApplicantBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> applicant.getSkills().remove(0));
    }

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplicant(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplicant(null));

        // same name, all other attributes different -> returns true
        Applicant editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withJob(VALID_JOB_BOB).withRound(VALID_ROUND_BOB).withSkills(VALID_SKILL_PYTHON).build();
        assertTrue(ALICE.isSameApplicant(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameApplicant(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Applicant editedBob = new ApplicantBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameApplicant(editedBob));

        // name has multiple whitespaces, all other attributes same -> returns true
        String nameWithMultipleSpaces = VALID_NAME_BOB + " ";
        editedBob = new ApplicantBuilder(BOB).withName(nameWithMultipleSpaces).build();
        assertTrue(BOB.isSameApplicant(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Applicant aliceCopy = new ApplicantBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different applicant -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Applicant editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different job -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withJob(VALID_JOB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different Round -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withRound(VALID_ROUND_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withSkills(VALID_SKILL_PYTHON).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
