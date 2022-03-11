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
        // null stage
        assertThrows(NullPointerException.class, () -> seedu.linkedout.model.applicant.Stage.isValidStage(null));

        // invalid stages
        assertFalse(seedu.linkedout.model.applicant.Stage.isValidStage("")); // empty string
        assertFalse(seedu.linkedout.model.applicant.Stage.isValidStage(" ")); // spaces only

        // valid stages
        assertTrue(seedu.linkedout.model.applicant.Stage.isValidStage("Technical Interview"));
        assertTrue(seedu.linkedout.model.applicant.Stage.isValidStage("-")); // one character
        assertTrue(Stage.isValidStage("Behavioral and HR interview")); // long stage
    }
}
