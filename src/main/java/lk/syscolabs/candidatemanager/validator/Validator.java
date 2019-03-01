package lk.syscolabs.candidatemanager.validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {

    private static Pattern emailPattern = Pattern.compile(
            "[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
    private static Pattern urlPattern = Pattern.compile(
            "(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b");

    public abstract ValidationResult validate(Object o);

    protected boolean onlyContainsNumbers(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    protected boolean isValidEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        return m.matches();
    }

    protected boolean isValidUrl(String url) {
        Matcher m = urlPattern.matcher(url);
        return m.matches();
    }

    protected boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    protected boolean oneOf(String value, String[] expected) {
        return Arrays.asList(expected).contains(value);
    }

    protected boolean isValidContactNumber(String contactNumber) {
        boolean value = true;
        if (!Character.toString(contactNumber.charAt(0)).equals("+") && !Character.toString(contactNumber.charAt(0)).equals("0"))
            value = false;
        else if (Character.toString(contactNumber.charAt(0)).equals("+") && !onlyContainsNumbers(contactNumber.substring(1)))
            value = false;
        else if (!Character.toString(contactNumber.charAt(0)).equals("+") && !onlyContainsNumbers(contactNumber))
            value = false;
        return value;
    }

    protected boolean isEmptyList(Collection<?> list) {
        return list == null || list.isEmpty();
    }

}
