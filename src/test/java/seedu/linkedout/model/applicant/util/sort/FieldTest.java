package seedu.linkedout.model.applicant.util.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FieldTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Field(null));
    }

    @Test
    public void constructor_invalidField_throwsIllegalArgumentException() {
        String invalidField = "";
        assertThrows(IllegalArgumentException.class, () -> new Field(invalidField));
    }

    @Test
    public void equalsField() {
        Field nameField = new Field("NAME");
        Field jobField = new Field("JOB");

        // name field
        assertTrue(nameField.equals(new Field("NaMe")));
        assertTrue(nameField.equals(new Field("name")));

        // job field
        assertTrue(jobField.equals(new Field("job")));
        assertTrue(jobField.equals(new Field("jOb")));

        // different job field
        assertFalse(nameField.equals(jobField));
        assertFalse(nameField.equals(new Field("joB")));
    }

    @Test
    public void isValidField() {
        // null field
        assertThrows(NullPointerException.class, () -> Field.isValidField(null));

        // invalid fields
        assertFalse(Field.isValidField("")); // empty string
        assertFalse(Field.isValidField(" ")); // spaces only
        assertFalse(Field.isValidField("EMAIL")); // not supported
        assertFalse(Field.isValidField("round")); // wrong format
        assertFalse(Field.isValidField("NAMESSS")); // wrong format
        assertFalse(Field.isValidField("JOBS")); // wrong format
        assertFalse(Field.isValidField("   JOB ")); // with whitespace

        // valid fields
        assertTrue(Field.isValidField("NAME"));
        assertTrue(Field.isValidField("NaMe")); // case-insensitive
        assertTrue(Field.isValidField("JOB"));
        assertTrue(Field.isValidField("Job")); // case-insensitive
    }
}
