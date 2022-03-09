package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.logic.commands.CommandTestUtil.*;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.AMY;
import static seedu.linkedout.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.AddCommand;
import seedu.linkedout.model.applicant.Address;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.skill.Skill;
import seedu.linkedout.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withSkills(VALID_SKILL_MARKETING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING, new AddCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING, new AddCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING, new AddCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING, new AddCommand(expectedApplicant));

        // multiple addresses - last linkedout accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING, new AddCommand(expectedApplicant));

        // multiple skills - all accepted
        Applicant expectedApplicantMultipleSkills = new ApplicantBuilder(BOB)
                .withSkills(VALID_SKILL_PYTHON, VALID_SKILL_MARKETING)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_PYTHON + SKILL_DESC_MARKETING, new AddCommand(expectedApplicantMultipleSkills));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero skills
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withSkills().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing linkedout prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_MARKETING + SKILL_DESC_PYTHON, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_MARKETING + SKILL_DESC_PYTHON, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + SKILL_DESC_MARKETING + SKILL_DESC_PYTHON, Email.MESSAGE_CONSTRAINTS);

        // invalid linkedout
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + SKILL_DESC_MARKETING + SKILL_DESC_PYTHON, Address.MESSAGE_CONSTRAINTS);

        // invalid skill
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_SKILL_DESC + VALID_SKILL_PYTHON, Skill.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_MARKETING + SKILL_DESC_PYTHON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
