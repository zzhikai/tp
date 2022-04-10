package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.Model;
import seedu.linkedout.ui.ClearAlertBox;

/**
 * Clears the linkedout app.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "LinkedOUT has been cleared!";
    public static final String MESSAGE_ABORT = "Clear command aborted by user, LinkedOUT was not cleared.";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you want to clear LinkedOUT app?";

    private ClearAlertBox clearAlertBox = new ClearAlertBox();
    private boolean isConfirmClear;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        alertConfirmClear();
        if (isConfirmClear) {
            return executeClear(model);
        } else {
            return executeAbortClear();
        }
    }

    /**
     * clears the linkedout app
     */
    public CommandResult executeClear(Model model) {
        model.setLinkedout(new Linkedout());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * linkedout app is not clear
     */
    public CommandResult executeAbortClear() {
        return new CommandResult(MESSAGE_ABORT);
    }

    /**
     * Display confirmation box to get confirmation from user on clearing the linkedout app
     * Display notification if user confirms on clearing the linkedout app
     */
    private void alertConfirmClear() {
        boolean isConfirmed = clearAlertBox.showConfirmationAlert(MESSAGE_CONFIRMATION);
        if (isConfirmed) {
            clearAlertBox.showInformationAlert(ClearCommand.MESSAGE_SUCCESS);
        }
        isConfirmClear = isConfirmed;
    }
}
