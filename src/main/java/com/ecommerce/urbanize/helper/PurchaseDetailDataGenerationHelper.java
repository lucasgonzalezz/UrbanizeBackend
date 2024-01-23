package com.ecommerce.urbanize.helper;

import java.util.Random;

public class PurchaseDetailDataGenerationHelper {

    public static int getRandomAmount() {
        // Assuming the amount is between 1 and 10
        return new Random().nextInt(10) + 1;
    }

    public static int getRandomPrice() {
        // Assuming the price is between 10 and 100
        return new Random().nextInt(91) + 10;
    }

}