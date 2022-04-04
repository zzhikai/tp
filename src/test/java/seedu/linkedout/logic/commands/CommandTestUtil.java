package seedu.linkedout.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.linkedout.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_JOB_AMY = "Marketing Manager";
    public static final String VALID_JOB_BOB = "Data Analyst";
    public static final String VALID_ROUND_AMY = "Interview";
    public static final String VALID_ROUND_BOB = "Technical Interview";
    public static final String VALID_SKILL_JAVA = "Java";
    public static final String VALID_SKILL_PYTHON = "Python";
    public static final String VALID_SKILL_MARKETING = "Marketing";
    public static final String VALID_SKILL_WITH_SYMBOL = "C++";
    public static final Boolean VALID_FLAG_VALUE_TRUE = true;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String JOB_DESC_AMY = " " + PREFIX_JOB + VALID_JOB_AMY;
    public static final String JOB_DESC_BOB = " " + PREFIX_JOB + VALID_JOB_BOB;
    public static final String ROUND_DESC_AMY = " " + PREFIX_ROUND + VALID_ROUND_AMY;
    public static final String ROUND_DESC_BOB = " " + PREFIX_ROUND + VALID_ROUND_BOB;
    public static final String SKILL_DESC_JAVA = " " + PREFIX_SKILL + VALID_SKILL_JAVA;
    public static final String SKILL_DESC_MARKETING = " " + PREFIX_SKILL + VALID_SKILL_MARKETING;
    public static final String SKILL_DESC_PYTHON = " " + PREFIX_SKILL + VALID_SKILL_PYTHON;
    public static final String SKILL_DESC_WITH_SYMBOL = " " + PREFIX_SKILL + VALID_SKILL_WITH_SYMBOL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_JOB_DESC = " " + PREFIX_JOB; // empty string not allowed for jobs
    public static final String INVALID_ROUND_DESC = " " + PREFIX_ROUND; // empty string not allowed for rounds
    public static final String INVALID_SKILL_DESC = " " + PREFIX_SKILL + "way too many words inside skill";
    public static final String INVALID_SKILL_ALL_SYMBOLS = " " + PREFIX_SKILL + "!@#$%";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicantDescriptor DESC_AMY;
    public static final EditCommand.EditApplicantDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withJob(VALID_JOB_AMY)
                .withRound(VALID_ROUND_AMY).withSkills(VALID_SKILL_MARKETING).build();
        DESC_BOB = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withJob(VALID_JOB_BOB)
                .withRound(VALID_ROUND_BOB).withSkills(VALID_SKILL_PYTHON, VALID_SKILL_MARKETING).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the linkedout app, filtered applicant list and selected applicant in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Linkedout expectedLinkedout = new Linkedout(actualModel.getLinkedout());
        List<Applicant> expectedFilteredList = new ArrayList<>(actualModel.getDefaultApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLinkedout, actualModel.getLinkedout());
        assertEquals(expectedFilteredList, actualModel.getDefaultApplicantList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s linkedout app.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getDefaultApplicantList().size());

        Applicant applicant = model.getDefaultApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getDefaultApplicantList().size());
    }

}
