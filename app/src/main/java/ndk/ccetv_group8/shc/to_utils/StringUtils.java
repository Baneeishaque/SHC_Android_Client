package ndk.ccetv_group8.shc.to_utils;

public class StringUtils {

    public static String removeQuotations(String string) {
        return removeSymbol(string, "\"");
    }

    public static String removeHyphens(String string) {
        return removeSymbol(string, "\\");
    }

    public static String removeSymbol(String string, String symbol) {
        return string.replace(symbol, "");
    }

    public static String removeFirstAndLastCharacters(String string) {
        return string.substring(1, string.length() - 1);
    }
}
