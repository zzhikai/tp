package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTest {

    static final int ASCII_CHARACTER_EXCLAMATION_MARK = 33;
    static final int ASCII_CHARACTER_BACKSLASH = 47;
    static final int ASCII_CHARACTER_COLON = 58;
    static final int ASCII_CHARACTER_AT = 64;
    static final int ASCII_CHARACTER_SQUARE_BRACKET = 91;
    static final int ASCII_CHARACTER_BACK_TICK = 96;
    static final int ASCII_CHARACTER_LEFT_CURLY_BRACE = 123;
    static final int ASCII_CHARACTER_DEL = 127;

    @Test
    public void constructor_emptyJob_throwsIllegalArgumentException() {
        String emptyJob = "";
        assertThrows(IllegalArgumentException.class, () -> new Job(emptyJob));
    }

    @Test
    public void constructor_invalidJob_throwsIllegalArgumentException() {
        String invalidJob = "#";
        assertThrows(IllegalArgumentException.class, () -> new Job(invalidJob));
    }

    @Test
    public void constructor_invalidJobWhitespace_throwsIllegalArgumentException() {
        String invalidJob = " ";
        assertThrows(IllegalArgumentException.class, () -> new Job(invalidJob));
    }

    @Test
    public void constructor_validJob_valueEqualsParameter() {
        String validJob = "job1";
        Job job = new Job(validJob);
        assertEquals(job.value, "job1");
    }

    @Test
    public void isValidJob_whitespace_false() {
        String invalidJob = " ";
        assertFalse(Job.isValidJob(invalidJob));
    }

    @Test
    public void isValidJob_nonAlphaNumeric_false() {

        char character = ASCII_CHARACTER_EXCLAMATION_MARK;
        while (character <= ASCII_CHARACTER_BACKSLASH) {
            String jobValue = String.valueOf(character);
            assertFalse(Job.isValidJob(jobValue));
            character++;
        }

        character = ASCII_CHARACTER_COLON;
        while (character <= ASCII_CHARACTER_AT) {
            String jobValue = String.valueOf(character);
            assertFalse(Job.isValidJob(jobValue));
            character++;
        }

        character = ASCII_CHARACTER_SQUARE_BRACKET;
        while (character <= ASCII_CHARACTER_BACK_TICK) {
            String jobValue = String.valueOf(character);
            assertFalse(Job.isValidJob(jobValue));
            character++;
        }

        character = ASCII_CHARACTER_LEFT_CURLY_BRACE;
        while (character <= ASCII_CHARACTER_DEL) {
            String jobValue = String.valueOf(character);
            assertFalse(Job.isValidJob(jobValue));
            character++;
        }
    }

    @Test
    public void isValidJob() {
        // null job
        assertThrows(NullPointerException.class, () -> Job.isValidJob(null));

        // invalid jobs
        assertFalse(Job.isValidJob("")); // empty string
        assertFalse(Job.isValidJob(" ")); // spaces only

        // valid jobs
        assertTrue(Job.isValidJob("Software Engineer"));
        assertTrue(Job.isValidJob("J")); // one character
        assertTrue(Job.isValidJob("Full Stack Software Engineer")); // long job
    }

    @Test
    public void equals_thisJob_true() {
        Job job = new Job("job");
        assertTrue(job.equals(job));
    }

    @Test
    public void equals_otherType_false() {
        Job job = new Job("job");
        String string = "job";
        assertFalse(job.equals(string));
    }

    @Test
    public void equals_otherJobSameValue_true() {
        Job job = new Job("job");
        Job otherJob = new Job("otherJob");
        assertFalse(job.equals(otherJob));
    }
}
