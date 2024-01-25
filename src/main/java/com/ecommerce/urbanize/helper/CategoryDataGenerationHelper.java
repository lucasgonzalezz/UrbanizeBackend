package com.ecommerce.urbanize.helper;

import java.util.Random;

public class CategoryDataGenerationHelper {

    private static final String[] categoryNames = {
            "Electronics", "Clothing", "Home and Kitchen", "Sports", "Books"
    };

    public static String getRandomCategoryName() {
        return getRandomFromArray(categoryNames);
    }

    private static String getRandomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

}