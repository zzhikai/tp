package seedu.linkedout.logic.commands;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Flag;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class FlagCommand extends Command {

    public static final String COMMAND_WORD = "flag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flags the applicant identified by the index number used in the displayed applicant list\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FLAG_APPLICANT_SUCCESS = "Flagged Applicant: %1$s";
    public static final String MESSAGE_UNFLAG_APPLICANT_SUCCESS = "Unflagged Applicant: %1$s";

    private final Index targetIndex;

    public FlagCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToFlag = lastShownList.get(targetIndex.getZeroBased());
        Applicant flaggedApplicant;

        String returnMessage;

        if (!applicantToFlag.getFlag().value) {
            flaggedApplicant = new Applicant(
                    applicantToFlag.getName(),
                    applicantToFlag.getPhone(),
                    applicantToFlag.getEmail(),
                    applicantToFlag.getJob(),
                    applicantToFlag.getRound(),
                    applicantToFlag.getSkills(),
                    new Flag(true));
            returnMessage = MESSAGE_FLAG_APPLICANT_SUCCESS;
        } else {
            flaggedApplicant = new Applicant(
                    applicantToFlag.getName(),
                    applicantToFlag.getPhone(),
                    applicantToFlag.getEmail(),
                    applicantToFlag.getJob(),
                    applicantToFlag.getRound(),
                    applicantToFlag.getSkills(),
                    new Flag(false));
            returnMessage = MESSAGE_UNFLAG_APPLICANT_SUCCESS;
        }
        model.flagApplicant(applicantToFlag, flaggedApplicant);
        return new CommandResult(String.format(returnMessage, flaggedApplicant));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlagCommand // instanceof handles nulls
                && targetIndex.equals(((FlagCommand) other).targetIndex)); // state check
    }
}
