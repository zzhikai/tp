package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.linkedout.model.applicant.Stage(null));
    }

    @Test
    public void constructor_invalidStage_throwsIllegalArgumentException() {
        String invalidStage = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.linkedout.model.applicant.Stage(invalidStage));
    }

    @Test
    public void isValidStage() {
        // null linkedout
        assertThrows(NullPointerException.class, () -> seedu.linkedout.model.applicant.Stage.isValidStage(null));

        // invalid stagees
        assertFalse(seedu.linkedout.model.applicant.Stage.isValidStage("")); // empty string
        assertFalse(seedu.linkedout.model.applicant.Stage.isValidStage(" ")); // spaces only

        // valid stagees
        assertTrue(seedu.linkedout.model.applicant.Stage.isValidStage("Blk 456, Den Road, #01-355"));
        assertTrue(seedu.linkedout.model.applicant.Stage.isValidStage("-")); // one character
        assertTrue(Stage.isValidStage("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long linkedout
    }
}
