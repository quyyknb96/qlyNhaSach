package util;

import java.util.Arrays;

public class ValidateUtil {
    public static boolean checkAnyEmpty(Object... args) {
        return Arrays.stream(args).anyMatch(o -> o == null || o.toString().isEmpty());
    }
}
