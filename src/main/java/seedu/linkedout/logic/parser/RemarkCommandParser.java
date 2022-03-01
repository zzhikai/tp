package seedu.linkedout.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.commons.exceptions.IllegalValueException;
import seedu.linkedout.logic.commands.RemarkCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), ive);
        }

        String str = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(str);

        return new RemarkCommand(index, remark);
    }
}