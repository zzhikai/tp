package seedu.linkedout.logic.parser;

import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.logic.commands.DeleteCommand;
import seedu.linkedout.logic.commands.FlagCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FlagCommand object
 */
public class FlagCommandParser implements Parser<FlagCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the FlagCommand
     * and returns a FlagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FlagCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FlagCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE), pe);
        }
    }
}
