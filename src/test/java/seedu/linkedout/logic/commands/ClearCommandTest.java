package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_confirmation_success() {
        ClearCommand clearCommand = new ClearCommand();
        Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        //if user press no on confirmation box
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS);
        assertEquals(expectedResult, clearCommand.executeClear(model));
    }

    @Test
    public void execute_confirmation_failure() {
        ClearCommand clearCommand = new ClearCommand();
        //if user press no on confirmation box
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_ABORT);
        assertEquals(expectedResult, clearCommand.executeAbortClear());
    }

}
