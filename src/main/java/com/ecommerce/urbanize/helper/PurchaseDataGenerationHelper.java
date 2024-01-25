package com.ecommerce.urbanize.helper;

import java.time.LocalDate;
import java.util.Random;

public class PurchaseDataGenerationHelper {

    private static final String[] statuses = {
            "Pending", "Shipped", "Delivered", "Cancelled"
    };

    private static final String[] purchaseCodes = {
            "ABC123", "XYZ456", "123DEF", "789GHI", "456JKL"
    };

    public static String getRandomStatus() {
        return getRandomFromArray(statuses);
    }

    public static String getRandomPurchaseCode() {
        return getRandomFromArray(purchaseCodes);
    }

    public static LocalDate getRandomDate() {
        // Assuming the date is within the last year
        return LocalDate.now().minusDays(new Random().nextInt(365));
    }

    private static String getRandomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

}
