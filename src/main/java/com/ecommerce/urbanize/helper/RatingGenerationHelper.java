package com.ecommerce.urbanize.helper;

import java.time.LocalDate;
import java.util.Random;

public class RatingGenerationHelper {

    private static final String[] titles = {
            "Great Product", "Awesome Experience", "Not Satisfied", "Highly Recommended", "Terrible Quality"
    };

    private static final String[] descriptions = {
            "The product exceeded my expectations.", "Smooth transaction and fast shipping.",
            "Disappointed with the purchase.",
            "Will buy again!", "Waste of money, do not recommend."
    };

    public static String getRandomTitle() {
        return getRandomFromArray(titles);
    }

    public static String getRandomDescription() {
        return getRandomFromArray(descriptions);
    }

    public static int getRandomPunctuation() {
        // Assuming punctuation is between 1 and 5
        return new Random().nextInt(5) + 1;
    }

    public static LocalDate getRandomDate() {
        // Assuming the date is within the last year
        return LocalDate.now().minusDays(new Random().nextInt(365));
    }

    private static String getRandomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

}