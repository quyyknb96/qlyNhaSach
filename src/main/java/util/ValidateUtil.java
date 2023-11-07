package util;

import java.util.Arrays;
import java.util.Objects;

public class ValidateUtil {
    public static boolean checkAllNotNull(Object... args) {
        return Arrays.stream(args).anyMatch(Objects::isNull);
    }
}
