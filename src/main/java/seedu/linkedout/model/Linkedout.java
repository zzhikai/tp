package seedu.linkedout.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.UniqueApplicantList;

/**
 * Wraps all data at the linkedout app level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class Linkedout implements ReadOnlyLinkedout {

    private final UniqueApplicantList applicants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
    }

    public Linkedout() {}

    /**
     * Creates a Linkedout app using the Applicants in the {@code toBeCopied}
     */
    public Linkedout(ReadOnlyLinkedout toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code Linkedout} with {@code newData}.
     */
    public void resetData(ReadOnlyLinkedout newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the linkedout book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds an applicant to the Linkedout app.
     * The applicant must not already exist in the linkedout app.
     */
    public void addApplicant(Applicant a) {
        applicants.add(a);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the linkedout app.
     * The applicant identity of {@code editedApplicant} must not
     * be the same as another existing applicant in the linkedout app.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code Linkedout}.
     * {@code key} must exist in the linkedout app.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applicants.asUnmodifiableObservableList().size() + " applicants";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Linkedout// instanceof handles nulls
                && applicants.equals(((Linkedout) other).applicants));
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }
}
