package seedu.linkedout.logic.parser;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.linkedout.logic.commands.SearchCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.JobContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.KeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;


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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_JOB);

        boolean hasNoPrefixesPresent = !anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_JOB);
        boolean hasNoEmptyPreamble = !argMultimap.getPreamble().isEmpty();
        boolean hasEmptyArguments = args.isEmpty();
        if (hasNoPrefixesPresent || hasNoEmptyPreamble || hasEmptyArguments) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_JOB).isPresent()) {
            String[] nameKeywords = getArrayOfKeywords(PREFIX_NAME, argMultimap);
            String[] jobKeywords = getArrayOfKeywords(PREFIX_JOB, argMultimap);
            List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();
            keywordsPredicateList.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            keywordsPredicateList.add(new JobContainsKeywordsPredicate(Arrays.asList(jobKeywords)));

            return new SearchCommand(keywordsPredicateList);

        } else if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = getArrayOfKeywords(PREFIX_NAME, argMultimap);
            List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();
            keywordsPredicateList.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            return new SearchCommand(keywordsPredicateList);
        } else {
            String[] jobKeywords = getArrayOfKeywords(PREFIX_JOB, argMultimap);
            List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();
            keywordsPredicateList.add(new JobContainsKeywordsPredicate(Arrays.asList(jobKeywords)));
            return new SearchCommand(keywordsPredicateList);
        }
    }

    /**
     * Returns true if any of the prefixes are not empty  {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Get the input keywords according to prefix and split the keywords with white space
     * @param prefix input prefixes
     * {@code ArgumentMultimap}.
     * @return an array of keywords without white space
     */
    private static String[] getArrayOfKeywords(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(prefix).get();
        return splitKeywordsWithWhiteSpace(keyword);
    }

    /**
     * Split the keywords with white space to allow partial matching
     * @param keywords input keywords
     * @return Array of substrings without whitespace
     * @throws  ParseException if the user input is empty
     */
    private static String[] splitKeywordsWithWhiteSpace(String keywords) throws ParseException {
        if (keywords.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_CONSTRAINTS));
        }
        return keywords.split("\\s+");
    }



}
