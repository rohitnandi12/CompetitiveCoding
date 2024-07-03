package com.playground;

import java.text.MessageFormat;

public class Test {

    public static boolean isNumber(String s) {

        if (s == null) {

            return false;

        }

        return s.chars().allMatch(Character::isDigit);

    }

    public static void main(String[] args) {
        System.out.println(isNumber("12324"));
        System.out.println(isNumber("12324Aad"));
    }
}
