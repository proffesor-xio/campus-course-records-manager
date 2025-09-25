package edu.ccrm.util;

import java.util.regex.Pattern;

public class Validators {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidRegNo(String regNo) {
        return regNo != null && !regNo.trim().isEmpty();
    }

    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}
