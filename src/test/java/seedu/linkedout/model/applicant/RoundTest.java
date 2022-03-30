package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoundTest {

    static final int ASCII_CHARACTER_EXCLAMATION_MARK = 33;
    static final int ASCII_CHARACTER_BACKSLASH = 47;
    static final int ASCII_CHARACTER_COLON = 58;
    static final int ASCII_CHARACTER_AT = 64;
    static final int ASCII_CHARACTER_SQUARE_BRACKET = 91;
    static final int ASCII_CHARACTER_BACK_TICK = 96;
    static final int ASCII_CHARACTER_LEFT_CURLY_BRACE = 123;
    static final int ASCII_CHARACTER_DEL = 127;

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
    public void constructor_validRound_valueEqualsParameter() {
        String validRound = "round1";
        Round round = new Round(validRound);
        assertEquals(round.value, "round1");
    }

    @Test
    public void isValidRound_nonAlphaNumeric_false() {
        // Partition of symbols with ascii values 33-47
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_EXCLAMATION_MARK)));
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_BACKSLASH)));

        // Partition of symbols with ascii values 58-64
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_COLON)));
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_AT)));

        // Partition of symbols with ascii values 91-96
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_SQUARE_BRACKET)));
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_BACK_TICK)));

        // Partition of symbols with ascii values 123-127
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_LEFT_CURLY_BRACE)));
        assertFalse(Round.isValidRound(String.valueOf((char) ASCII_CHARACTER_DEL)));
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
        assertTrue(Round.isValidRound("1")); // one character
        assertTrue(Round.isValidRound("Behavioral and HR interview")); // long Round
    }

    @Test
    public void equals_thisRound_true() {
        Round round = new Round("round");
        assertTrue(round.equals(round));
    }

    @Test
    public void equals_otherType_false() {
        Round round = new Round("round");
        String string = "round";
        assertFalse(round.equals(string));
    }

    @Test
    public void equals_otherRoundSameTypeDifferentValue_false() {
        Round round = new Round("round");
        Round otherRound = new Round("otherRound");
        assertFalse(round.equals(otherRound));
    }
}
