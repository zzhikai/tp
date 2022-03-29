package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class FlagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.linkedout.model.applicant.Flag(null));
    }

    @Test
    public void equals_flagTrueOtherFlagFalse_notEqual() {
        Flag flagTrue = new Flag(true);
        Flag flagFalse = new Flag(false);
        assertNotEquals(flagTrue, flagFalse);
    }

    @Test
    public void equals_sameFlag_equal() {
        Flag flagTrue = new Flag(true);
        assertEquals(flagTrue, flagTrue);
    }

    @Test
    public void equals_flagTrueOtherFlagTrue_equal() {
        Flag flagTrue = new Flag(true);
        Flag otherFlagTrue = new Flag(true);
        assertEquals(flagTrue, otherFlagTrue);
    }

    @Test
    public void equals_flagOtherObject_notEqual() {
        Flag flagTrue = new Flag(true);
        Object object = new Object();
        assertNotEquals(flagTrue, object);
    }
}
