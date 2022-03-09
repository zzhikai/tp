package seedu.linkedout.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.linkedout.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.linkedout.commons.exceptions.IllegalValueException;
import seedu.linkedout.model.applicant.Address;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;

public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SKILL = "Skill with Too Many Words Inside";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedSkill> VALID_SKILLS = BENSON.getSkills().stream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(BENSON);
        assertEquals(BENSON, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_SKILLS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidSkills_throwsIllegalValueException() {
        List<JsonAdaptedSkill> invalidSkills = new ArrayList<>(VALID_SKILLS);
        invalidSkills.add(new JsonAdaptedSkill(INVALID_SKILL));
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidSkills);
        assertThrows(IllegalValueException.class, applicant::toModelType);
    }

}
