package seedu.linkedout.model.applicant.util.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Order(null));
    }

    @Test
    public void constructor_invalidOrder_throwsIllegalArgumentException() {
        String invalidOrder = "";
        assertThrows(IllegalArgumentException.class, () -> new Order(invalidOrder));
    }

    @Test
    public void equalsOrder() {
        Order ascOrder = new Order("ASC");
        Order descOrder = new Order("DESC");

        // asc order
        assertTrue(ascOrder.equals(new Order("Asc")));
        assertTrue(ascOrder.equals(new Order("asc")));

        // desc order
        assertTrue(descOrder.equals(new Order("desc")));
        assertTrue(descOrder.equals(new Order("desc")));

        // different desc order
        assertFalse(ascOrder.equals(descOrder));
        assertFalse(ascOrder.equals(new Order("desc")));
    }

    @Test
    public void isValidOrder() {
        // null order
        assertThrows(NullPointerException.class, () -> Order.isValidOrder(null));

        // invalid orders
        assertFalse(Order.isValidOrder("")); // empty string
        assertFalse(Order.isValidOrder(" ")); // spaces only
        assertFalse(Order.isValidOrder("hello")); // random string
        assertFalse(Order.isValidOrder("descending")); // wrong format
        assertFalse(Order.isValidOrder("DSC")); // wrong format
        assertFalse(Order.isValidOrder("   DESC  ")); // with white space
        assertFalse(Order.isValidOrder("   ASC")); // with white space

        // valid orders
        assertTrue(Order.isValidOrder("asc"));
        assertTrue(Order.isValidOrder("AsC")); // case-insensitive
        assertTrue(Order.isValidOrder("DESC"));
        assertTrue(Order.isValidOrder("DesC")); // case-insensitive
    }
}
