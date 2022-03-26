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

        boolean hasNoPrefixesPresent = !hasAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_JOB);
        boolean hasNoEmptyPreamble = !argMultimap.getPreamble().isEmpty();
        boolean hasEmptyArguments = args.isEmpty();
        if (hasNoPrefixesPresent || hasNoEmptyPreamble || hasEmptyArguments) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();

        if (isPrefixPresent(PREFIX_NAME, argMultimap)) {
            List<String> nameKeywords = getArrayOfKeywords(PREFIX_NAME, argMultimap);
            keywordsPredicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (isPrefixPresent(PREFIX_JOB, argMultimap)) {
            List<String> jobKeywords = getArrayOfKeywords(PREFIX_JOB, argMultimap);
            keywordsPredicateList.add(new JobContainsKeywordsPredicate(jobKeywords));
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
    private static List<String> getArrayOfKeywords(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
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
