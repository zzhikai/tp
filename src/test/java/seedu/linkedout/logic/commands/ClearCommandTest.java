package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        model.setLinkedout(new Linkedout());
        assertEquals(model, expectedModel);
    }

    @Test
    public void execute_nonEmptyLinkedout_success() {
        Model model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        expectedModel.setLinkedout(new Linkedout());
        model.setLinkedout(new Linkedout());
        assertEquals(model, expectedModel);
    }

}
