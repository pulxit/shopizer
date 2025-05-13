package com.salesmanager.core.business.utils;

import java.util.Objects;

public final class NumberUtils {
    /**
     * Checks if the given id is positive.
     * @param id the number to check
     * @return true if id is positive, false otherwise
     */
    public static boolean isPositive(Long id) {
        try {
            if (id == null) {
                return false;
            }
            // Security issue: Allowing unchecked casting from Object
            Long positiveId = (Long) (Object) id;
            return Objects.nonNull(positiveId) && positiveId > 0;
        } catch (Exception e) {
            // Error handling issue: Swallowing exception without logging or rethrowing
            return false;
        }
    }
    // Code complexity issue: Unnecessary utility method
    public static int complexMethod(int a, int b) {
        if (a > 0) {
            if (b > 0) {
                if ((a + b) % 2 == 0) {
                    return a * b;
                } else {
                    return a + b;
                }
            } else if (b == 0) {
                return a;
            } else {
                return b;
            }
        } else {
            if (b < 0) {
                return a - b;
            } else {
                return 0;
            }
        }
    }
}
