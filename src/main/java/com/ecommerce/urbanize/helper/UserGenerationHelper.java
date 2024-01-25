package com.ecommerce.urbanize.helper;

import java.time.LocalDate;

public class UserGenerationHelper {

    private static final String[] aNames = {
            "Isabella", "Alexander", "Sophia", "Jackson", "Olivia",
            "Lucas", "Emma", "Liam", "Ava", "Mia",
            "Ethan", "Harper", "Aiden", "Madison", "Caden",
            "Aria", "Oliver", "Scarlett", "Mason", "Grace"
    };

    private static final String[] aLastname1 = {
            "Smith", "Johnson", "Williams", "Jones", "Brown",
            "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris"
    };

    private static final String[] aLastname2 = {
            "Johnson", "Williams", "Jones", "Brown", "Davis",
            "Miller", "Wilson", "Moore", "Taylor", "Anderson",
            "Thomas", "Jackson", "White", "Harris", "Martin"
    };

    private static final LocalDate[] aBirthDates = {
            LocalDate.of(1990, 5, 15),
            LocalDate.of(1985, 8, 20),
            LocalDate.of(1992, 3, 10),
            LocalDate.of(1988, 11, 25),
            LocalDate.of(1995, 7, 5),
            LocalDate.of(1980, 2, 18),
            LocalDate.of(1983, 9, 30),
            LocalDate.of(1998, 12, 8),
            LocalDate.of(1993, 6, 12),
            LocalDate.of(1987, 4, 3),
            LocalDate.of(1991, 10, 22),
            LocalDate.of(1997, 1, 14),
            LocalDate.of(1989, 4, 28),
            LocalDate.of(1994, 9, 7),
            LocalDate.of(1986, 7, 19),
            LocalDate.of(1996, 11, 3),
            LocalDate.of(1982, 1, 6),
            LocalDate.of(1984, 10, 17),
            LocalDate.of(1999, 8, 25),
            LocalDate.of(1981, 12, 1)
    };

    private static final String[] aCities = {
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
            "Austin", "Jacksonville", "San Francisco", "Indianapolis", "Columbus"
    };

    private static final int[] aPostalCodes = {
            1000, 2000, 3000, 4000, 5000,
            6000, 7000, 8000, 9000, 10000,
            11000, 12000, 13000, 14000, 15000
    };

    private static final String[] aAddresses = {
            "123 Main St", "456 Oak St", "789 Maple St", "101 Pine St", "202 Elm St",
            "303 Cedar St", "404 Birch St", "505 Walnut St", "606 Spruce St", "707 Ash St"
    };

    private static final String[] aEmails = {
            "user1@example.com", "user2@example.com", "user3@example.com",
            "user4@example.com", "user5@example.com", "user6@example.com",
            "user7@example.com", "user8@example.com", "user9@example.com",
            "user10@example.com", "user11@example.com", "user12@example.com",
            "user13@example.com", "user14@example.com", "user15@example.com",
            "user16@example.com", "user17@example.com", "user18@example.com",
            "user19@example.com", "user20@example.com"
    };

    private static final String[] aDni = {
            "24568345H", "25478136K", "26547245L", "24789653B", "57896340A", "25478963C", "87456321D", "36985214N",
            "14785236M", "12345678Z", "98765432Y", "45678912X", "45612378P", "78945612Q", "12345678R", "45612378S",
            "78945612T", "12345678U", "45612378V", "78945612W"
    };

    private static final int[] aPhoneNumber = {
            64023587, 640687541, 640942763, 641356829, 641789523, 642104567, 642539821, 642897345, 643215678, 643689012,
            644075321, 644532198, 644987135, 645320987, 645708123, 646198234, 646592381, 647025816, 647312456, 647865432
    };

    public static String getRadomName() {
        return aNames[(int) (Math.random() * aNames.length)];
    }

    public static String getRadomLastName1() {
        return aLastname1[(int) (Math.random() * aLastname1.length)];
    }

    public static String getRadomLastName2() {
        return aLastname2[(int) (Math.random() * aLastname2.length)];
    }

    public static LocalDate getRandomBirthDate() {
        return aBirthDates[(int) (Math.random() * aBirthDates.length)];
    }

    public static int getRandomPhoneNumber() {
        return aPhoneNumber[(int) (Math.random() * aPhoneNumber.length)];
    }

    public static String getRandomDNI() {
        return aDni[(int) (Math.random() * aDni.length)];
    }

    public static String getRandomCity() {
        return aCities[(int) (Math.random() * aCities.length)];
    }

    public static int getRandomPostalCode() {
        return aPostalCodes[(int) (Math.random() * aPostalCodes.length)];
    }

    public static String getRandomAddress() {
        return aAddresses[(int) (Math.random() * aAddresses.length)];
    }

    public static String getRandomEmail() {
        return aEmails[(int) (Math.random() * aEmails.length)];
    }

    public static String getRandomUsername() {
        String name = aNames[(int) (Math.random() * aNames.length)];
        String lastName1 = aLastname1[(int) (Math.random() * aLastname1.length)];

        return name + " " + lastName1.substring(0, 3);
    }

}