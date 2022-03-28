package seedu.linkedout.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.KeywordsPredicate;

/**
 * The API of the Model component.
 */
public interface Model {
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
     * Returns the user prefs' linkedout app file path.
     */
    Path getLinkedoutFilePath();

    /**
     * Sets the user prefs' linkedout app file path.
     */
    void setLinkedoutFilePath(Path linkedoutFilePath);

    /**
     * Replaces linkedout app data with the data in {@code linkedout}.
     */
    void setLinkedout(ReadOnlyLinkedout linkedout);

    /** Returns the Linkedout app */
    ReadOnlyLinkedout getLinkedout();

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the linkedout app.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the linkedout app.
     */
    void deleteApplicant(Applicant applicant);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the linkedout app.
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces an applicant with its flagged version
     * The applicant must exist in the linkedout app.
     */
    void flagApplicant(Applicant applicant, Applicant flaggedApplicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the linkedout app.
     * The applicant identity of {@code editedApplicant} must not
     * be the same as another existing applicant in the linkedout app.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);

    /** Returns an unmodifiable view of the sorted applicant list */
    ObservableList<Applicant> getSortedApplicantList();

    /**
     * Updates the filter of the sorted applicant list to sorted by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedApplicantList(Comparator<Applicant> comparator);

    void updateSearchedApplicantList(List<KeywordsPredicate> predicate);
}
