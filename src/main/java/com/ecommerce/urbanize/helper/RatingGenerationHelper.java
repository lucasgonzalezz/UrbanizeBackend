package com.ecommerce.urbanize.helper;

import java.time.LocalDate;
import java.util.Random;

public class RatingGenerationHelper {

    private static final String[] titles = {
            "Excelente Producto", "Experiencia Increíble", "No Satisfecho", "Altamente Recomendado", "Calidad Terrible",
            "Buen Valor por el Dinero", "Envío Rápido y Eficiente"
    };

    private static final String[] descriptions = {
            "El producto superó mis expectativas.", "Transacción fluida y envío rápido.",
            "Decepcionado con la compra.", "¡Compraré de nuevo!", "Pérdida de dinero, no lo recomiendo.",
            "Buena relación calidad-precio.", "Entrega rápida y eficiente."
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