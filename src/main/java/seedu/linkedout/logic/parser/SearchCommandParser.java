package seedu.linkedout.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.linkedout.logic.commands.SearchCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.ApplicantContainsSkillKeywordsPredicate;
import seedu.linkedout.model.applicant.JobContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.KeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.RoundContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {


        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_JOB,
                PREFIX_ROUND, PREFIX_SKILL);
        boolean hasNoPrefixesPresent = !hasAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_JOB,
                PREFIX_ROUND, PREFIX_SKILL);
        boolean hasNoEmptyPreamble = !argMultimap.getPreamble().isEmpty();
        boolean hasEmptyArguments = args.isEmpty();
        boolean hasInvalidPrefix = ArgumentTokenizer.hasInvalidPrefix(args, argMultimap);
        if (hasInvalidPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_PREFIX, SearchCommand.MESSAGE_USAGE));
        }
        if (hasNoPrefixesPresent || hasNoEmptyPreamble || hasEmptyArguments) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();
        return parseKeyword(argMultimap, keywordsPredicateList);
    }

    /**
     *Parses the keywords to be searched in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     *
     * {@code ArgumentMultimap}.
     * {@code List<KeywordsPredicate>}
     * @throws ParseException if there exists a case where no prefixes match
     */
    public SearchCommand parseKeyword(ArgumentMultimap argMultimap,
                                      List<KeywordsPredicate> keywordsPredicateList) throws ParseException {
        requireNonNull(argMultimap);
        if (isPrefixPresent(PREFIX_NAME, argMultimap)) {
            List<String> nameKeywords = getListOfKeywords(PREFIX_NAME, argMultimap);
            keywordsPredicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (isPrefixPresent(PREFIX_JOB, argMultimap)) {
            List<String> jobKeywords = getListOfKeywords(PREFIX_JOB, argMultimap);
            keywordsPredicateList.add(new JobContainsKeywordsPredicate(jobKeywords));
        }
        if (isPrefixPresent(PREFIX_ROUND, argMultimap)) {
            List<String> roundKeywords = getListOfKeywords(PREFIX_ROUND, argMultimap);
            keywordsPredicateList.add(new RoundContainsKeywordsPredicate(roundKeywords));
        }
        if (isPrefixPresent(PREFIX_SKILL, argMultimap)) {
            List<String> skillKeywords = getListOfKeywords(PREFIX_SKILL, argMultimap);
            keywordsPredicateList.add(new ApplicantContainsSkillKeywordsPredicate(skillKeywords));
        }

        return new SearchCommand(keywordsPredicateList);

    }

    /**
     * Returns true if the prefix is not empty
     */
    private static boolean isPrefixPresent(Prefix prefix, ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    /**
     * Returns true if any of the prefixes are not empty  {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean hasAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Get the input keywords according to prefix and split the keywords with white space
     * @param prefix input prefixes
     * {@code ArgumentMultimap}.
     * @return an array of keywords without white space
     * @throws  ParseException if the user input is empty
     */
    private static List<String> getListOfKeywords(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(prefix).get();
        if (keyword.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));
        }
        List<String> keywords = argMultimap.getAllValues(prefix);
        return splitKeywordsWithWhiteSpace(keywords);
    }

    /**
     * Split the keywords with white space to allow partial matching
     * @param keywords input keywords
     * @return List of substrings without whitespace
     * @throws  ParseException if the user input is empty
     */
    private static List<String> splitKeywordsWithWhiteSpace(List<String> keywords) throws ParseException {
        if (keywords.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));
        }
        List<String> partialKeywords = new ArrayList<>();
        for (int i = 0; i < keywords.size(); i++) {
            String[] splitKeywords = keywords.get(i).split("\\s+");
            partialKeywords.addAll(Arrays.asList(splitKeywords));
        }
        return partialKeywords;
    }

}

