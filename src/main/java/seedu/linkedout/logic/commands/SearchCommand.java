package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.List;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.KeywordsPredicate;


/**
 * search on applicant(s) in linkedout app whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_CONSTRAINTS = "Keyword(s) should not be empty";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search an overview of a specific applicant \n"
            + "Parameters: must contain at least one prefix "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_JOB + "JOB] "
            + "[" + PREFIX_ROUND + "ROUND] "
            + "[" + PREFIX_SKILL + "SKILL]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " Steve" + " " + PREFIX_JOB + " Software Engineer"
            + " " + PREFIX_ROUND + " Interview" + " " + PREFIX_SKILL + "Java" + " " + PREFIX_SKILL + "Python";

    private final List<KeywordsPredicate> predicate;


    public SearchCommand(List<KeywordsPredicate> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSearchedApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getDefaultApplicantList().size()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
