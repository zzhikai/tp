package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.linkedout.logic.commands.CommandTestUtil.INVALID_SKILL_ALL_SYMBOLS;
import static seedu.linkedout.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.linkedout.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.linkedout.logic.commands.CommandTestUtil.SKILL_DESC_WITH_SYMBOL;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_WITH_SYMBOL;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.AddSkillCommand;
import seedu.linkedout.model.skill.Skill;
import seedu.linkedout.testutil.ApplicantUtil;

public class AddSkillCommandParserTest {

    private static final String SKILL_EMPTY = " " + PREFIX_SKILL;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE);

    private AddSkillCommandParser parser = new AddSkillCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_SKILL_JAVA, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddSkillCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsAddSkillCommand() {
        assertParseSuccess(parser, "1 s/Python", new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_PYTHON})));

        assertParseSuccess(parser, "1 s/Python s/Java", new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA, VALID_SKILL_PYTHON})));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_SKILL_JAVA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_SKILL_JAVA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ Vue", String.format(MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // too many words in skills
        assertParseFailure(parser, "1" + INVALID_SKILL_DESC, Skill.MESSAGE_CONSTRAINTS);

        // skill completely made up of symbols
        assertParseFailure(parser, "1" + INVALID_SKILL_ALL_SYMBOLS, Skill.MESSAGE_CONSTRAINTS);
    }

    @Test void parse_singleSkillSpecified_success() {
        String userInput = INDEX_FIRST_APPLICANT.getOneBased() + SKILL_DESC_JAVA;

        AddSkillCommand expectedCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA}));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test void parse_multipleSkillSpecified_success() {
        String userInput = INDEX_FIRST_APPLICANT.getOneBased() + SKILL_DESC_JAVA + SKILL_DESC_WITH_SYMBOL;
        AddSkillCommand expectedCommand = new AddSkillCommand(INDEX_FIRST_APPLICANT,
                ApplicantUtil.getApplicantNewSkills(new String[]{VALID_SKILL_JAVA, VALID_SKILL_WITH_SYMBOL}));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        // wrong prefix
        assertParseFailure(parser, "1 w/Java", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // first prefix correct but second prefix wrong
        assertParseFailure(parser, "1 s/Java w/Python", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // wrong prefix between correct prefixes
        assertParseFailure(parser, "1 s/Java w/Python s/CSS", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // spaces between prefixes
        assertParseFailure(parser, "1 s/   Java      w/  Python s/CSS", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // wrong prefix of any length
        assertParseFailure(parser, "1 s/   Java      www/  Python s/CSS", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // wrong prefix with slash in input parameter
        assertParseFailure(parser, "1 s/   Java      www/  Python/C++ s/CSS", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));

        // correct prefix with whitespace between input parameter with slash
        assertParseFailure(parser, "1 s/   Java      s/   Python/C++ s/CSS", String.format(
                MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));
    }
}
