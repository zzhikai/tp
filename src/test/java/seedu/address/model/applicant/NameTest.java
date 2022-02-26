package seedu.address.model.applicant;

import org.junit.jupiter.api.Test;
import seedu.address.model.applicant.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.applicant.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.applicant.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.applicant.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.applicant.Name.isValidName("")); // empty string
        assertFalse(seedu.address.model.applicant.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.applicant.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.applicant.Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(seedu.address.model.applicant.Name.isValidName("peter jack")); // alphabets only
        assertTrue(seedu.address.model.applicant.Name.isValidName("12345")); // numbers only
        assertTrue(seedu.address.model.applicant.Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(seedu.address.model.applicant.Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
