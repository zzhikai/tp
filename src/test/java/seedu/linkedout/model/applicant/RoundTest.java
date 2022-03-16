package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Round(null));
    }

    @Test
    public void constructor_invalidRound_throwsIllegalArgumentException() {
        String invalidRound = "";
        assertThrows(IllegalArgumentException.class, () -> new Round(invalidRound));
    }

    @Test
    public void isValidRound() {
        // null round
        assertThrows(NullPointerException.class, () -> Round.isValidRound(null));

        // invalid rounds
        assertFalse(Round.isValidRound("")); // empty string
        assertFalse(Round.isValidRound(" ")); // spaces only

        // valid rounds
        assertTrue(Round.isValidRound("Technical Interview"));
        assertTrue(Round.isValidRound("-")); // one character
        assertTrue(Round.isValidRound("Behavioral and HR interview")); // long Round
    }
}
