package seedu.linkedout.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.linkedout.model.applicant.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.linkedout.model.applicant.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.linkedout.model.applicant.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.linkedout.model.applicant.Name.isValidName("")); // empty string
        assertFalse(seedu.linkedout.model.applicant.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.linkedout.model.applicant.Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(seedu.linkedout.model.applicant.Name.isValidName("Goel 12")); //contains numeric characters

        // valid name
        assertTrue(seedu.linkedout.model.applicant.Name.isValidName("peter jack")); // alphabets only
        assertTrue(seedu.linkedout.model.applicant.Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr IInd")); // long names
        assertTrue(Name.isValidName("Patrick O'Brian")); // Name with '
        assertTrue(Name.isValidName("François Hollande Koch-Mehrin")); // Name with special chars
    }
}
