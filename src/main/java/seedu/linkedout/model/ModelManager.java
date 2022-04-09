package seedu.linkedout.model;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.commons.core.LogsCenter;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.KeywordsPredicate;


/**
 * Represents the in-memory model of the linkedout app data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Linkedout linkedout;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private final SortedList<Applicant> defaultApplicants;

    /**
     * Initializes a ModelManager with the given linkedout app and userPrefs.
     */
    public ModelManager(ReadOnlyLinkedout linkedout, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(linkedout, userPrefs);

        logger.fine("Initializing with linkedout: " + linkedout + " and user prefs " + userPrefs);

        this.linkedout = new Linkedout(linkedout);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<Applicant>(this.linkedout.getApplicantList());
        defaultApplicants = new SortedList<>(filteredApplicants); // default to filteredList
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

    // update FilteredList after adding
    // update defaultList with new FilteredList with comparator so that order is retained
    @Override
    public void addApplicant(Applicant applicant) {
        linkedout.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        updateDefaultApplicantList((a1, a2) -> 0);
    }

    // update FilteredList after flagging
    @Override
    public void flagApplicant(Applicant applicant, Applicant flaggedApplicant) {
        requireAllNonNull(applicant, flaggedApplicant);
        linkedout.flagApplicant(applicant, flaggedApplicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
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
    public ObservableList<Applicant> getDefaultApplicantList() {
        return defaultApplicants;
    }


    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
        defaultApplicants.setComparator(null);
    }

    //=========== Default Applicant List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedLinkedout}
     */
    @Override
    public void updateDefaultApplicantList(Comparator<Applicant> comparator) {
        requireNonNull(comparator);
        defaultApplicants.setComparator(comparator);
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
                && defaultApplicants.equals(other.defaultApplicants);
    }

    //=========== Search Applicant List Accessors =============================================================

    @Override
    public void updateSearchedApplicantList(List<KeywordsPredicate> predicates) {
        requireNonNull(predicates);
        Predicate<Applicant> keywordPredicate = combinePredicates(predicates);
        updateFilteredApplicantList(keywordPredicate);
        defaultApplicants.setComparator((applicant1, applicant2) -> numberOfKeywordMatches(applicant2, predicates)
                - numberOfKeywordMatches(applicant1, predicates));
    }

    /**
     * Returns the number of matched input keywords with an applicant
     * @param applicant
     * @param predicates list of predicates
     * @return number of keywords matched
     * @throws NullPointerException if predicate is empty
     */
    private static int numberOfKeywordMatches (Applicant applicant, List<KeywordsPredicate> predicates) {
        requireNonNull(predicates);
        int matchedNumber = 0;
        for (int i = 0; i < predicates.size(); i++) {
            matchedNumber += predicates.get(i).numberOfKeywordMatches(applicant);
        }
        return matchedNumber;
    }

    /**
     * Return the combined predicates of a short-circuiting logical AND of given predicates
     * @param predicateList list of predicates
     * @return combined predicate
     * @throws NullPointerException if predicate is empty
     */
    private static Predicate<Applicant> combinePredicates(List<KeywordsPredicate> predicateList) {
        requireNonNull(predicateList);
        Predicate<Applicant> predicates = predicateList.get(0);
        for (int i = 1; i < predicateList.size(); i++) {
            predicates = predicates.or(predicateList.get(i));
        }
        return predicates;
    }
}
