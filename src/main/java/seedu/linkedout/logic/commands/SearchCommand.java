package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;

/**
 * search on applicant(s) in linkedout app whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search an overview of a specific applicant"
            + "specified by applicant's name (case-insensitive) and displays the applicant's information.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "Steve";

    private final NameContainsKeywordsPredicate predicate;

    public SearchCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
