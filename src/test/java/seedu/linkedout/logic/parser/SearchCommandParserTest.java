package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.SearchCommand;
import seedu.linkedout.model.applicant.JobContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        //missing name prefix
        assertParseFailure(parser, "Alice Bob", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

        //missing job prefix
        assertParseFailure(parser, "Engineer", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_keywordMissing_throwsParseException() {
        // missing name keyword
        assertParseFailure(parser, " n/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        // missing job keyword
        assertParseFailure(parser, " j/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        SearchCommand expectedNameSearchCommand =
                new SearchCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        SearchCommand expectedJobSearchCommand =
                new SearchCommand(new JobContainsKeywordsPredicate(Arrays.asList("Software", "Engineer")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Alice Bob", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/Software Engineer", expectedJobSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice \n \t Bob  \t", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/Software \n \t Engineer  \t", expectedJobSearchCommand);

        //leading whitespace after prefix
        assertParseSuccess(parser, " n/\nAlice \n \t Bob  \t", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/                  Software \n \t Engineer  \t", expectedJobSearchCommand);

        //case insensitive
        assertParseSuccess(parser, " n/AlIce BOb", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/soFTwaRe enGiNeer", expectedJobSearchCommand);

    }

}
