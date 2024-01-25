package com.ecommerce.urbanize.helper;

import java.util.Random;

public class CategoryDataGenerationHelper {

    private static final String[] categoryNames = {
            "Camiseta de Manga Corta", "Camiseta Básica", "Camiseta Estampada",
            "Camiseta de Algodón", "Camiseta Casual", "Camiseta Deportiva",
            "Camiseta de Diseño", "Camiseta Vintage", "Camiseta de Rayas",
            "Camiseta Gráfica", "Camiseta de Colores", "Camiseta Oversize",
            "Camiseta Slim Fit", "Camiseta Unisex", "Camiseta para Niños",
            "Camiseta para Mujer", "Camiseta para Hombre", "Camiseta de Marca",
            "Camiseta de Verano",

            "Pantalón Vaquero", "Pantalón Chino", "Pantalón Deportivo",
            "Shorts Casual", "Shorts Deportivos", "Pantalón de Vestir",
            "Leggings Deportivos", "Pantalón Cargo", "Pantalón Corto",
            "Falda Casual", "Falda Plisada", "Falda Vaquera",

            "Zapatillas Deportivas", "Zapatillas Urbanas", "Botas de Montaña",
            "Zapatos Casuales", "Sandalias de Verano", "Botines de Cuero",
            "Zapatos de Vestir", "Zapatos Deportivos", "Zapatillas de Running",
            "Chanclas Playa", "Zapatillas de Skate", "Alpargatas",

            "Chaqueta Vaquera", "Chaqueta Deportiva", "Chaqueta de Cuero",
            "Chaqueta Bomber", "Abrigo de Lana", "Cazadora de Invierno",
            "Chaleco Casual", "Chaqueta Acolchada", "Sudadera con Capucha",
            "Jersey de Punto", "Cárdigan Elegante", "Blazer Formal"
    };

    public static String getRandomCategoryName() {
        return getRandomFromArray(categoryNames);
    }

    private static String getRandomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

}