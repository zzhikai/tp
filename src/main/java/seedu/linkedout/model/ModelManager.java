package seedu.linkedout.model;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.commons.core.LogsCenter;
import seedu.linkedout.model.applicant.Applicant;

/**
 * Represents the in-memory model of the linkedout app data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Linkedout linkedout;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private final SortedList<Applicant> sortedApplicants;

    /**
     * Initializes a ModelManager with the given linkedout app and userPrefs.
     */
    public ModelManager(ReadOnlyLinkedout linkedout, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(linkedout, userPrefs);

        logger.fine("Initializing with linkedout: " + linkedout + " and user prefs " + userPrefs);

        this.linkedout = new Linkedout(linkedout);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<Applicant>(this.linkedout.getApplicantList());
        sortedApplicants = new SortedList<>(filteredApplicants); // default to filteredList
    }

    public ModelManager() {
        this(new Linkedout(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLinkedoutFilePath() {
        return userPrefs.getLinkedoutFilePath();
    }

    @Override
    public void setLinkedoutFilePath(Path linkedoutFilePath) {
        requireNonNull(linkedoutFilePath);
        userPrefs.setLinkedoutFilePath(linkedoutFilePath);
    }

    //=========== Linkedout ================================================================================

    @Override
    public void setLinkedout(ReadOnlyLinkedout linkedout) {
        this.linkedout.resetData(linkedout);
    }

    @Override
    public ReadOnlyLinkedout getLinkedout() {
        return linkedout;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return linkedout.hasApplicant(applicant);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        linkedout.removeApplicant(target);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        linkedout.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        updateSortedApplicantList((a1, a2) -> 0);
        // update list to sort when adding applicant
        // need to set applicantList sort to new filteredApplicant list
        // non null comparator in order for it to update?
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        linkedout.setApplicant(target, editedApplicant);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedLinkedout}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
        sortedApplicants.setComparator(null);
        // so that list will not become permanently sorted
    }

    //=========== Sorted Applicant List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedLinkedout}
     */
    @Override
    public ObservableList<Applicant> getSortedApplicantList() {
        return sortedApplicants;
    }

    @Override
    public void updateSortedApplicantList(Comparator<Applicant> comparator) {
        requireNonNull(comparator);
        // no difference
        // filteredApplicants.setPredicate(PREDICATE_SHOW_ALL_APPLICANTS);
        sortedApplicants.setComparator(comparator);
    }

    //===========
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return linkedout.equals(other.linkedout)
                && userPrefs.equals(other.userPrefs)
                && filteredApplicants.equals(other.filteredApplicants)
                && sortedApplicants.equals(other.sortedApplicants);
    }


}
