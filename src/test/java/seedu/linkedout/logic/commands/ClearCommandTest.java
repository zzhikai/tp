package seedu.linkedout.logic.commands;

import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyLinkedout_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLinkedout_success() {
        Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        expectedModel.setLinkedout(new Linkedout());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
