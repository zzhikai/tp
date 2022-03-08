package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.Model;

/**
 * Clears the linkedout app.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLinkedout(new Linkedout());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
