package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Comparator;

import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.Applicant;

/**
 * Sort the list of applciants in the linkedout app.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort applicants in the LinkedOUT app. "
            + "Parameters: "
            + PREFIX_FIELD + "FIELD "
            + PREFIX_ORDER + "ORDER..."
            + "\nFIELD has to be NAME or JOB " + "and" + " ORDER either ASC or DESC (case-insensitive)"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_FIELD + "NAME "
            + PREFIX_ORDER + "ASC ";

    public static final String MESSAGE_SUCCESS = "List has been sorted in %1$s";
    public static final Object MESSAGE_CONSTRAINTS = "FIELD and ORDER should not be empty";

    private final Comparator<Applicant> comparator;

    /**
     * Creates an SortCommand to sort the list of applicants with {@code comparator}
     */
    public SortCommand(Comparator<Applicant> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedApplicantList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator));
    }
}
