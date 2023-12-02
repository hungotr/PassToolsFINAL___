package com.example.passtools.model;

import java.security.SecureRandom;
import java.util.Random;

/**
 * PasswordGenerator will use a method call to create a randomized,highly secure password
 *
 * @author Joshua Carey
 */
public class PasswordGenerator {

    /**
     * Strings of characters used in password generation
     */
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;

    private final Random random;
    private final int length;

    /**
     * Constructor for PasswordGenerator
     * @param length
     */
    public PasswordGenerator(int length) {
        this.length = length;
        this.random = new SecureRandom();
    }

    /**
     * generatePassword reads from database strings defined above and use random class to create randomized password String
     * @return String representing a generated password for user
     */
    public String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return password.toString();
    }



}