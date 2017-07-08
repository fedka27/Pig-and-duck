package app.meat.util;

import android.support.annotation.Nullable;

import java.util.regex.Pattern;

public final class ValidationUtil {

    public static boolean hasEmptyFields(String... fields) {
        for (String field : fields) {
            if (field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(@Nullable String email) {
        return email != null && !email.isEmpty() && Patterns
                .EMAIL_ADDRESS
                .matcher(email)
                .matches();
    }

    private static class Patterns {


        private static final String EMAIL_PATTERN =
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." +
                        "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                        "|\"(?:[\\x01-\\x08\\x0b\\x0c\\" +
                        "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-" +
                        "\\x7f]|\\\\[\\x01-\\x09\\x0b\\" +
                        "x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9]" +
                        "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
                        "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]" +
                        "|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?" +
                        "|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b" +
                        "\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                        "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        private static final Pattern EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN);
    }
}
