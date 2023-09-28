package ma.MaCNSS.Helpers;

public class TextColor {
    public static String yellowText(String originText) {
        String yellowText = "\u001B[33m";
        String resetText = "\u001B[0m";
        originText = yellowText + originText + resetText;
        return originText;
    }
    public static String greenText(String originText) {
        String greenText = "\u001B[32m";
        String resetText = "\u001B[0m";
        originText = greenText + originText + resetText;
        return originText;
    }
}
