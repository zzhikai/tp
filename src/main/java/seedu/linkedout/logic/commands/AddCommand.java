package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;

import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.Applicant;

/**
 * Adds an applicant to the linkedout app.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to the LinkedOUT app.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_JOB + "JOB "
            + PREFIX_ROUND + "ROUND "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_JOB + "Software Engineer "
            + PREFIX_ROUND + "Technical Interview "
            + PREFIX_SKILL + "Python "
            + PREFIX_SKILL + "Java";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the LinkedOUT app.";

    private final Applicant toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Applicant}
     */
    public AddCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
