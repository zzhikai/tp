package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.linkedout.model.applicant.Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.linkedout.model.applicant.Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null linkedout
        assertThrows(NullPointerException.class, () -> seedu.linkedout.model.applicant.Address.isValidAddress(null));

        // invalid addresses
        assertFalse(seedu.linkedout.model.applicant.Address.isValidAddress("")); // empty string
        assertFalse(seedu.linkedout.model.applicant.Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(seedu.linkedout.model.applicant.Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(seedu.linkedout.model.applicant.Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long linkedout
    }
}
