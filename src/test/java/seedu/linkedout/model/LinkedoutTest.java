package seedu.linkedout.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.exceptions.DuplicateApplicantException;
import seedu.linkedout.testutil.ApplicantBuilder;

public class LinkedoutTest {

    private final Linkedout linkedout = new Linkedout();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), linkedout.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> linkedout.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLinkedout_replacesData() {
        Linkedout newData = getTypicalLinkedout();
        linkedout.resetData(newData);
        assertEquals(newData, linkedout);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        LinkedoutStub newData = new LinkedoutStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> linkedout.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> linkedout.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInLinkedout_returnsFalse() {
        assertFalse(linkedout.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInLinkedout_returnsTrue() {
        linkedout.addApplicant(ALICE);
        assertTrue(linkedout.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInLinkedout_returnsTrue() {
        linkedout.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(linkedout.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> linkedout.getApplicantList().remove(0));
    }

    /**
     * A stub ReadOnlyLinkedout whose applicants list can violate interface constraints.
     */
    private static class LinkedoutStub implements ReadOnlyLinkedout {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        LinkedoutStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
