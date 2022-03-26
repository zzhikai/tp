package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

public class SortComparator implements Comparator<Applicant> {

    private final String field;
    private final Order order;

    public SortComparator(String field, Order order) {
        // assume both pass parseUtil before passing here
        requireNonNull(field);
        requireNonNull(order);
        this.field = field;
        this.order = order;
    }

    @Override
    public int compare(Applicant applicant1, Applicant applicant2) {
        requireNonNull(applicant1);
        requireNonNull(applicant2);
        if (order.equals("DESC")) {
            switch (field) {
            case "NAME":
                return applicant1.compareNames(applicant2);
            case "JOB":
                return applicant1.compareJobs(applicant2);
            default:
                return 0;
            }
        } else {
            switch (field) {
            case "NAME":
                return applicant2.compareNames(applicant1);
            case "JOB":
                return applicant2.compareJobs(applicant1);
            default:
                return 0;
            }
        }
    }
    // toString and equals not overriden yet
}
