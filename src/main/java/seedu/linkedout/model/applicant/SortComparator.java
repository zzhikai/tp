package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

public class SortComparator implements Comparator<Applicant> {

    private final Field field;
    private final Order order;

    /**
     *
     * @param field
     * @param order
     */
    public SortComparator(Field field, Order order) {
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
        String orderString = this.order.toString();
        String fieldString = this.field.toString();
        switch(orderString) {
        case "ASC":
            switch (fieldString) {
            case "NAME":
                return applicant1.getName().compareTo(applicant2.getName());
            case "JOB":
                return applicant1.getJob().compareTo(applicant2.getJob());
            default:
                return 0;
            }
        case "DESC":
            switch (fieldString) {
            case "NAME":
                return applicant2.getName().compareTo(applicant1.getName());
            case "JOB":
                return applicant2.getJob().compareTo(applicant2.getJob());
            default:
                return 0;
            }
        default:
            return 0;
        }
    }

    @Override
    public String toString() {
        String orderString = this.order.toString();
        switch(orderString) {
        case "ASC":
            return "ascending order according to " + this.field;
        case "DESC":
            return "descending order according to " + this.field;
        default:
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortComparator
            && field.equals(((SortComparator) other).field)
            && order.equals(((SortComparator) other).order));
    }

}
