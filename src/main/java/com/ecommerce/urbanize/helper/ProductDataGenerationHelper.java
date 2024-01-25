package com.ecommerce.urbanize.helper;

import java.util.Random;

public class ProductDataGenerationHelper {

    private static final String[] productNames = {
            "StreetFlex Joggers",
            "UrbanPulse Hoodie",
            "CityChic Bomber Jacket",
            "GraffitiVibe T-Shirt",
            "MetroStyle Denim Jeans",
            "Concrete Jungle Sneakers",
            "SkateSoul Skateboard Shorts",
            "GraffTag Snapback Cap",
            "Cityscape Leggings",
            "SubwayStrut High-Tops",
            "VibeCheck Graphic Tee",
            "MetroMotion Cargo Pants",
            "CitySlicker Windbreaker",
            "BrickLane Beanie",
            "UrbanBeat Track Jacket",
            "GraffitiGroove Backpack",
            "StreetSwag Sunglasses",
            "MetroMesh Sneaker Socks",
            "PavementPulse Capri Pants",
            "AlleyArt Hooded Dress"
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