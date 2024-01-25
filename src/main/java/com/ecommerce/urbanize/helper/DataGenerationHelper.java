package com.ecommerce.urbanize.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import com.ecommerce.urbanize.exception.CannotPerformOperationException;

public class DataGenerationHelper {

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        int numeroRandom = random.nextInt((max - min) + 1) + min;
        return numeroRandom;
    }

    public static String getSHA256(String strToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(strToHash.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException("No such algorithm: SHA256");
        }
    }
}