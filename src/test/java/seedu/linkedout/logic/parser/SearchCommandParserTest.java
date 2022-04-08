package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.SearchCommand;
import seedu.linkedout.model.applicant.ApplicantContainsSkillKeywordsPredicate;
import seedu.linkedout.model.applicant.JobContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.KeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.RoundContainsKeywordsPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        // missing name prefix
        assertParseFailure(parser, "Alice Bob", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

        // missing job prefix
        assertParseFailure(parser, "Engineer", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

        // missing round prefix
        assertParseFailure(parser, "Technical Interview", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

        //missing skill prefix
        assertParseFailure(parser, "Javadocs", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

        //missing one prefix
        assertParseFailure(parser, "Dave s/Javadocs", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        // wrong prefix
        assertParseFailure(parser, "w/Alice Bob", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // first prefix correct but second prefix wrong
        assertParseFailure(parser, "n/Alice Bob w/Software Engineer", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // wrong prefix between correct prefixes
        assertParseFailure(parser, "n/Alice Bob w/Software Engineer s/Java", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // spaces between prefixes
        assertParseFailure(parser, "n/   Alice Bob      w/  Software Engineer s/Java", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // wrong prefix of any length
        assertParseFailure(parser, "n/   Alice Bob      www/  Software Engineer s/Java", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // wrong prefix with slash in input parameter
        assertParseFailure(parser, "n/   Alice Bob      www/  Software/Engineer s/Java", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));

        // correct prefix with whitespace between input parameter with slash
        assertParseFailure(parser, "n/   Alice Bob      s/   Software/Engineer s/Java", String.format(
                MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_keywordMissing_throwsParseException() {
        // missing name keyword
        assertParseFailure(parser, " n/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        // missing job keyword
        assertParseFailure(parser, " j/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        // missing round keyword
        assertParseFailure(parser, " r/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        //missing skill keyword
        assertParseFailure(parser, " s/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        //missing multiple skill keyword
        assertParseFailure(parser, " j/ s/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));

        //missing one prefix
        assertParseFailure(parser, "Dave s/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validPrefix_returnSearchCommand() {
        List<KeywordsPredicate> skillsKeywordPredicateList = new ArrayList<>();

        ApplicantContainsSkillKeywordsPredicate skillsKeywordPredicate =
                new ApplicantContainsSkillKeywordsPredicate(Arrays.asList("Java/Python"));

        Collections.addAll(skillsKeywordPredicateList, skillsKeywordPredicate);

        SearchCommand expectedSkillSearchCommand =
                new SearchCommand(skillsKeywordPredicateList);

        //one valid prefix with slash in input keyword
        assertParseSuccess(parser, " s/Java/Python", expectedSkillSearchCommand);
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        List<KeywordsPredicate> nameKeywordPredicateList = new ArrayList<>();
        List<KeywordsPredicate> jobKeywordPredicateList = new ArrayList<>();
        List<KeywordsPredicate> roundKeywordPredicateList = new ArrayList<>();
        List<KeywordsPredicate> skillsKeywordPredicateList = new ArrayList<>();
        List<KeywordsPredicate> partialCombinedKeywordPredicateList = new ArrayList<>();
        List<KeywordsPredicate> combinedKeywordPredicateList = new ArrayList<>();

        NameContainsKeywordsPredicate nameKeywordPredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        JobContainsKeywordsPredicate jobKeywordPredicate =
                new JobContainsKeywordsPredicate(Arrays.asList("Software", "Engineer"));
        RoundContainsKeywordsPredicate roundKeywordPredicate =
                new RoundContainsKeywordsPredicate(Arrays.asList("Technical", "Interview"));
        ApplicantContainsSkillKeywordsPredicate skillsKeywordPredicate =
                new ApplicantContainsSkillKeywordsPredicate(Arrays.asList("Java", "Python"));

        Collections.addAll(nameKeywordPredicateList, nameKeywordPredicate);
        Collections.addAll(jobKeywordPredicateList, jobKeywordPredicate);
        Collections.addAll(roundKeywordPredicateList, roundKeywordPredicate);
        Collections.addAll(skillsKeywordPredicateList, skillsKeywordPredicate);
        Collections.addAll(combinedKeywordPredicateList, nameKeywordPredicate, jobKeywordPredicate,
                roundKeywordPredicate, skillsKeywordPredicate);
        Collections.addAll(partialCombinedKeywordPredicateList, nameKeywordPredicate, skillsKeywordPredicate);

        SearchCommand expectedNameSearchCommand =
                new SearchCommand(nameKeywordPredicateList);
        SearchCommand expectedJobSearchCommand =
                new SearchCommand(jobKeywordPredicateList);
        SearchCommand expectedRoundSearchCommand =
                new SearchCommand(roundKeywordPredicateList);
        SearchCommand expectedSkillSearchCommand =
                new SearchCommand(skillsKeywordPredicateList);
        SearchCommand expectedAllPrefixesCommand =
                new SearchCommand(combinedKeywordPredicateList);
        SearchCommand expectedPartialSearchCommand =
                new SearchCommand(partialCombinedKeywordPredicateList);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Alice Bob", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/Software Engineer", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/Technical Interview", expectedRoundSearchCommand);
        assertParseSuccess(parser, " s/Java s/Python", expectedSkillSearchCommand);
        assertParseSuccess(parser, " n/Alice Bob s/Java s/Python", expectedPartialSearchCommand);
        assertParseSuccess(parser, " n/Alice Bob j/Software Engineer r/Technical Interview s/Java s/Python",
                expectedAllPrefixesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice \n \t Bob  \t", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/Software \n \t Engineer  \t", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/Technical \n \t Interview            \t", expectedRoundSearchCommand);


        //leading whitespace after prefix
        assertParseSuccess(parser, " n/\nAlice \n \t Bob  \t", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/                  Software \n \t Engineer  \t", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/        Technical     \n         Interview  ", expectedRoundSearchCommand);
        assertParseSuccess(parser, " s/    Java s/   Python", expectedSkillSearchCommand);

        //case insensitive
        assertParseSuccess(parser, " n/AlIce BOb", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/soFTwaRe enGiNeer", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/teChnIcaL inTerVieW", expectedRoundSearchCommand);
        assertParseSuccess(parser, " s/JaVa s/PyThON", expectedSkillSearchCommand);
        assertParseSuccess(parser, " n/AlIce BOb s/JaVa s/PyThON", expectedPartialSearchCommand);
        assertParseSuccess(parser, " n/AlIce BOb j/soFTwaRe enGiNeer r/tEchniCal InteRvieW s/JaVa s/PyThON",
                expectedAllPrefixesCommand);

        //AND condition for all prefix
        assertParseSuccess(parser, " n/Alice Bob j/Software Engineer r/Technical Interview s/Java s/Python",
                expectedAllPrefixesCommand);

        //AND condition for partial prefix
        assertParseSuccess(parser, " n/Alice Bob s/Java s/Python", expectedPartialSearchCommand);
    }

}
