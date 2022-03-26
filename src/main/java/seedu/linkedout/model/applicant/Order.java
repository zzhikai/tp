package seedu.linkedout.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's name in the linkedout app.
 * Guarantees: immutable; is valid as declared in {@link #isValidOrder(String)}
 */
public class Order {

    public static final String MESSAGE_CONSTRAINTS =
            "Order should either be 'asc' or 'desc'(case-insensitive)";

    public final String sortOrder;

    public static final String ascendingOrder = "ASC";
    public static final String descendingOrder = "DESC";

    /**
     * Constructs a {@code Order}.
     *
     * @param order A valid order.
     */
    public Order(String order) {
        requireNonNull(order);
        checkArgument(isValidOrder(order), MESSAGE_CONSTRAINTS);
        sortOrder = order.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOrder(String test) {
        String orderUpperCase = test.toUpperCase();
        if (orderUpperCase.equals(ascendingOrder) || orderUpperCase.equals(descendingOrder)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return sortOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && sortOrder.equals(((Order) other).sortOrder)); // state check
    }

    @Override
    public int hashCode() {
        return sortOrder.hashCode();
    }

}
