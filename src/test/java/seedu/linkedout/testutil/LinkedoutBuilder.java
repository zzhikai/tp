package seedu.linkedout.testutil;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.applicant.Applicant;

/**
 * A utility class to help with building Linkedout objects.
 * Example usage: <br>
 *     {@code Linkedout ab = new LinkedoutBuilder().withApplicant("John", "Doe").build();}
 */
public class LinkedoutBuilder {

    private Linkedout linkedout;

    public LinkedoutBuilder() {
        linkedout = new Linkedout();
    }

    public LinkedoutBuilder(Linkedout linkedout) {
        this.linkedout = linkedout;
    }

    /**
     * Adds a new {@code Applicant} to the {@code Linkedout} that we are building.
     */
    public LinkedoutBuilder withApplicant(Applicant applicant) {
        linkedout.addApplicant(applicant);
        return this;
    }

    public Linkedout build() {
        return linkedout;
    }
}
