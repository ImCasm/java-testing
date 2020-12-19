package casm.javatests.util;

public class StringsUtil {

    public static String repeat(String string, int times) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < times ; i++) {
            result.append(string);
        }

        return result.toString();
    }


    public static boolean isEmpty(String string) {
        return !(string != null && !string.trim().isEmpty());
    }
}
