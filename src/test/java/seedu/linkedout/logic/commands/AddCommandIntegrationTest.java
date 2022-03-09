package seedu.linkedout.logic.commands;

import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.linkedout.model.Model;
import seedu.linkedout.model.ModelManager;
import seedu.linkedout.model.UserPrefs;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLinkedout(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getLinkedout(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddCommand(validApplicant), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validApplicant), expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getLinkedout().getApplicantList().get(0);
        assertCommandFailure(new AddCommand(applicantInList), model, AddCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}
