package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Job(null));
    }

    @Test
    public void constructor_invalidJob_throwsIllegalArgumentException() {
        String invalidJob = "";
        assertThrows(IllegalArgumentException.class, () -> new Job(invalidJob));
    }

    @Test
    public void isValidJob() {
        // null linkedout
        assertThrows(NullPointerException.class, () -> Job.isValidJob(null));

        // invalid jobes
        assertFalse(Job.isValidJob("")); // empty string
        assertFalse(Job.isValidJob(" ")); // spaces only

        // valid Jobes
        assertTrue(Job.isValidJob("Blk 456, Den Road, #01-355"));
        assertTrue(Job.isValidJob("-")); // one character
        assertTrue(Job.isValidJob("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long linkedout
    }
}
