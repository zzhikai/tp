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
    public static final String MESSAGE_FAIL = "LinkedOUT is not cleared";
    public static final String MESSAGE_CONFIRMTION = "Are you sure you want to clear LinkedOUT app?";

    private ClearAlertBox clearAlertBox = new ClearAlertBox();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isConfirmClear()) { // if user press yes on the confirmation box
            clearAlertBox.showInformationAlert(ClearCommand.MESSAGE_SUCCESS);
            model.setLinkedout(new Linkedout());
            return new CommandResult(MESSAGE_SUCCESS);
        } else { //if user press no on the confirmation box
            return new CommandResult(MESSAGE_FAIL);
        }
    }

    /**
     * Display confirmation box to get confirmation from user on clearing the linkedout app
     * @return True if user press yes
     */
    private boolean isConfirmClear() {
        return clearAlertBox.showConfirmationAlert(MESSAGE_CONFIRMTION);
    }
}
