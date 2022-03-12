package seedu.linkedout.logic.commands;

import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
        expectedModel = new ModelManager(model.getLinkedout(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
