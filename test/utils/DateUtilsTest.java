package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @org.junit.jupiter.api.Test
    void testIsInvalidDateOrder() {
        boolean isValid = DateUtils.isValidDateOrder
                ("05-07-20", "03-06-19",
                        "04-04-19", "04-06-20");

        assertFalse(isValid, "Should assert false if dates are out of order");
    }

    @org.junit.jupiter.api.Test
    void testIsValidDateOrder() {
        boolean isValid = DateUtils.isValidDateOrder
                ("05-07-20", "03-12-20",
                        "12-01-21", "12-05-21");

        assertTrue(isValid, "Should assert false if dates are in order");
    }



}