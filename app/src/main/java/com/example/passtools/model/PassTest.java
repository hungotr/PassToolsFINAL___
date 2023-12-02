package com.example.passtools.model;

import android.app.Activity;

/**
 * PasswTest is a class that represents a PassTest object which will store a couple of Strings representing all valid password characters
 */
public class PassTest {
    /**
     * Locat String variables for valid password test keys
     */
    String lowerCase = "abcdefghijklmonpqrstuvwxyz";
    String upperCase = "ABCDEFGHIJKLMOPQRSTUVWXYZ";
    String number = "1234567890";
    String special = "!@#$%^&*()_+";

    /**
     * Constructor for Password Object
     * @param lowerCaseB
     * @param upperCaseB
     * @param numberB
     * @param specialB
     */
    public PassTest(String lowerCaseB, String upperCaseB, String numberB, String specialB) {
        this.lowerCase = lowerCaseB;
        this.upperCase = upperCaseB;
        this.number = numberB;
        this.special = specialB;

    }

    /**
     * Password exam class takes in a password string to test and the current activity, returns the Rating of the password
     * @param toTest
     * @param testaroni
     * @return String representation of the Passwords "Grade"
     */
    public String passwordExam(String toTest, Activity testaroni) {

        // String passwordEvaluation = "";
        int passwrdScore = 0;

        Boolean passLowerCaseTest = false, passUpperCaseTest = false, passNumberTest = false, passSpecialTest = false;

        int lwrMatch = 0, upprMatch = 0, numMatch = 0, spclMatch = 0;

        for (int i = 0; i < toTest.length(); i++) {
            char tempChar = toTest.charAt(i);

            for (int j = 0; j < upperCase.length(); j++) {
                if (tempChar == lowerCase.charAt(j)) {
                    lwrMatch++;
                } else if (tempChar == upperCase.charAt(j)) {
                    upprMatch++;
                } else if (j < number.length() && tempChar == number.charAt(j)) {
                    numMatch++;
                } else if (j < special.length() && tempChar == special.charAt(j)) {
                    spclMatch++;
                }
            }

        }
        if (lwrMatch >= 2) {
            passLowerCaseTest = true;
            passwrdScore++;
            //passwordEvaluation.endsWith(", Has enough lowercase letters");

        }
        if (upprMatch >= 2) {
            passUpperCaseTest = true;
            passwrdScore++;
            // passwordEvaluation.endsWith(", Has enough uppercase letters");
        }
        if (numMatch >= 2) {
            passNumberTest = true;
            passwrdScore++;
            //passwordEvaluation.endsWith(", Has enough number characters");
        }
        if (spclMatch >= 2) {
            passSpecialTest = true;
            passwrdScore++;
            //passwordEvaluation.endsWith(", Has enough special characters");
        }

        switch (passwrdScore) {

            // Case
            case 1:
                return "Weak";



            // Case
            case 2:
                return "Moderate";


            // Case
            case 3:
                return "Strong";


            // Case
            case 4:

                return "VERY STRONG";


            default:
                return "INVALID PASSWORD TRY AGAIN!";


        }

    }

    /**
     * Getters and Setters for PassTest object
     * @return lowerCase,number,special, or UpperCase String
     */

    public String getLowerCase() {
        return lowerCase;
    }

    public String getNumber() {
        return number;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUpperCase() {
        return upperCase;
    }

    public void setUpperCase(String upperCase) {
        this.upperCase = upperCase;
    }

    public void setLowerCase(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    /**
     * ToString method which returns String representation of PassTest object
     * @return String representation of PassTest Object
     */

    @Override
    public String toString() {
        return "PassTest{" +
                "lowerCase='" + lowerCase + '\'' +
                ", upperCase='" + upperCase + '\'' +
                ", number='" + number + '\'' +
                ", special='" + special + '\'' +
                '}';
    }
}
