package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.linkedout.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Flag;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;

/**
 * Adds skills to a specific applicant.
 */
public class AddSkillCommand extends Command {

    public static final String COMMAND_WORD = "addskill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds skills to a specific applicant\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SKILL + "Python "
            + PREFIX_SKILL + "Java";

    public static final String MESSAGE_SUCCESS = "New skills added: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the LinkedOUT app.";

    private final Index index;
    private final Set<Skill> skillsToAdd;

    /**
     * Edits the skills of an existing applicant in the linkedout app.
     */
    public AddSkillCommand(Index index, Set<Skill> skillsToAdd) {
        requireNonNull(index);
        requireNonNull(skillsToAdd);

        this.index = index;
        this.skillsToAdd = skillsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getDefaultApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = createEditedApplicant(applicantToEdit, skillsToAdd);

        if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.setApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedApplicant));
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code skillsToAdd}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit, Set<Skill> skillsToAdd) {
        assert applicantToEdit != null;
        Name name = applicantToEdit.getName();
        Phone phone = applicantToEdit.getPhone();
        Email email = applicantToEdit.getEmail();
        Job job = applicantToEdit.getJob();
        Round round = applicantToEdit.getRound();
        Set<Skill> currentSkills = applicantToEdit.getSkills();
        Set<Skill> newSkills = new HashSet<>();
        newSkills.addAll(currentSkills);
        newSkills.addAll(skillsToAdd);
        Flag flagStatus = applicantToEdit.getFlag();
        return new Applicant(name, phone, email, job, round, newSkills, flagStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSkillCommand // instanceof handles nulls
                && skillsToAdd.equals(((AddSkillCommand) other).skillsToAdd))
                && index.equals(((AddSkillCommand) other).index);
    }
}
