package com.ecommerce.urbanize.helper;

import java.util.Random;

public class ProductDataGenerationHelper {

    private static final String[] productNames = {
            "ProductA", "ProductB", "ProductC", "ProductD", "ProductE"
    };

    private static final String[] sizes = {
            "S", "M", "L", "XL", "XXL"
    };

    public static String getRandomProductName() {
        return getRandomFromArray(productNames);
    }

    public static String getRandomSize() {
        return getRandomFromArray(sizes);
    }

    public static int getRandomStock() {
        // Assuming the stock is between 10 and 100
        return new Random().nextInt(91) + 10;
    }

    public static int getRandomPrice() {
        // Assuming the price is between 50 and 500
        return new Random().nextInt(451) + 50;
    }

    private static String getRandomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

}