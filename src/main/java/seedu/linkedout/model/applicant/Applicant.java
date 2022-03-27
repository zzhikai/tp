package seedu.linkedout.model.applicant;

import static seedu.linkedout.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.linkedout.model.skill.Skill;

/**
 * Represents an Applicant in linkedout.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant implements Comparable<Applicant> {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Round round;
    private final Job job;
    private final Set<Skill> skills = new HashSet<>();
    private final Flag flagged;

    /**
     * Default constructor, sets flagged to false.
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Job job, Round round, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, job, round, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.round = round;
        this.skills.addAll(skills);
        this.flagged = new Flag(false);
    }

    /**
     * Constructor that allows an applicant to be flagged
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Job job, Round round, Set<Skill> skills, Flag flag) {
        requireAllNonNull(name, phone, email, job, round, skills, flag);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.round = round;
        this.skills.addAll(skills);
        this.flagged = flag;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Round getRound() {
        return round;
    }

    public Job getJob() {
        return job;
    }

    public Flag getFlag() {
        return flagged;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getRound().equals(getRound())
                && otherApplicant.getJob().equals(getJob())
                && otherApplicant.getSkills().equals(getSkills())
                && otherApplicant.getFlag().equals(getFlag());
    }

    @Override
    public int compareTo(Applicant other) {
        if (other == this) {
            return 0;
        }
        if (this.flagged.value && !other.getFlag().value) {
            return -1;
        } else if (!this.flagged.value && other.getFlag().value) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, job, round, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Job: ")
                .append(getJob())
                .append("; Round: ")
                .append(getRound());

        Set<Skill> skills = getSkills();
        if (!skills.isEmpty()) {
            builder.append("; Skills: ");
            skills.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns integer value representing if String value of name of current applicant
     * is more or less, or equal String value of name of current applicant to {@code applicant2}.
     */
    public int compareNames(Applicant applicant2) {
        String nameOfFirstApplicant = this.getName().toString().toLowerCase();
        String nameOfSecondApplicant = applicant2.getName().toString().toLowerCase();
        return nameOfFirstApplicant.compareTo(nameOfSecondApplicant);
    }

    /**
     * Returns integer value representing if String value of job of current applicant
     * is more or less, or equal String value of job of current applicant to {@code applicant2}.
     */
    public int compareJobs(Applicant applicant2) {
        String jobOfFirstApplicant = this.getJob().toString().toLowerCase();
        String jobOfSecondApplicant = applicant2.getJob().toString().toLowerCase();
        return jobOfFirstApplicant.compareTo(jobOfSecondApplicant);
    }
}
