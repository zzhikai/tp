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
        // null job
        assertThrows(NullPointerException.class, () -> Job.isValidJob(null));

        // invalid jobs
        assertFalse(Job.isValidJob("")); // empty string
        assertFalse(Job.isValidJob(" ")); // spaces only

        // valid Jobs
        assertTrue(Job.isValidJob("Software Engineer"));
        assertTrue(Job.isValidJob("-")); // one character
        assertTrue(Job.isValidJob("Full Stack Software Engineer")); // long job
    }
}
