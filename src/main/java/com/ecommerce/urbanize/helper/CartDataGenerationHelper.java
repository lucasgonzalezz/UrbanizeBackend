package com.ecommerce.urbanize.helper;

import java.util.Random;

public class CartDataGenerationHelper {

    public static int getRandomAmount() {
        // You can customize the logic for generating random amounts
        return new Random().nextInt(10) + 1; // Generates a random number between 1 and 10
    }

}