package seedu.linkedout.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.stream.Stream;

import seedu.linkedout.logic.commands.SortCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.Field;
import seedu.linkedout.model.applicant.Order;
import seedu.linkedout.model.applicant.SortComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIELD, PREFIX_ORDER);

        // either prefix not present will throw ParseException
        if (!arePrefixesPresent(argMultimap, PREFIX_ORDER, PREFIX_FIELD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        // get().isBlank to check if String is present !Blank
        // pass upper case to SortCommand
        boolean hasOrder = !argMultimap.getValue(PREFIX_ORDER).get().isBlank();
        boolean hasField = !argMultimap.getValue(PREFIX_FIELD).get().isBlank();
        if (hasOrder && hasField) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_ORDER).get().toUpperCase());
            Field field = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get().toUpperCase());
            return new SortCommand(new SortComparator(field, order));
        }
        // field is not valid or is blank
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
