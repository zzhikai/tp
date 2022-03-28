package seedu.linkedout.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.logic.commands.CommandResult;
import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.applicant.Applicant;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the linkedout app.
     *
     * @see seedu.linkedout.model.Model#getLinkedout()
     */
    ReadOnlyLinkedout getLinkedout();

    /** Returns an unmodifiable view of the filtered list of applicants */
    ObservableList<Applicant> getFilteredApplicantList();

    ObservableList<Applicant> getSortedApplicantList();

    /**
     * Returns the user prefs' linkedout app file path.
     */
    Path getLinkedoutFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
