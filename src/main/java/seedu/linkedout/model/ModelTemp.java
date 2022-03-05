package seedu.linkedout.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.model.applicant.Applicant;

/**
 * The API of the Model component.
 */
public interface ModelTemp {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' linkedout book file path.
     */
    Path getLinkedoutFilePath();

    /**
     * Sets the user prefs' linkedout book file path.
     */
    void setLinkedoutFilePath(Path linkedoutFilePath);

    /**
     * Replaces linkedout book data with the data in {@code linkedout}.
     */
    void setLinkedout(ReadOnlyLinkedout linkedout);

    /** Returns the Linkedout book */
    ReadOnlyLinkedout getLinkedout();

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the linkedout book.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the linkedout book.
     */
    void deleteApplicant(Applicant applicant);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the linkedout book.
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the linkedout book.
     * The applicant identity of {@code editedApplicant} must not
     * be the same as another existing applicant in the linkedout book.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);
}
