package ma.MaCNSS.Helpers;

public class PmScanner {

    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    //    Helper function
    public static int takeUserChoice(int min, int max) {
        int choice = -1;
        do {
            try {
                System.out.print("Enter a choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                scanner.next();
                choice = -1;
                System.out.println(TextColor.yellowText("Invalid input, must be a number !"));
            }
        } while (choice < min || choice > max);
        return choice;
    }

    public static String takeStringInputValue(String inputMessage) {
        String inputValue;
        try {
            System.out.print(inputMessage);
            inputValue = scanner.nextLine();
        } catch (java.util.InputMismatchException e) {
            scanner.next();
            inputValue = "";
        }
        return inputValue;
    }

    public static int takeIntInputValue(String inputMessage) {
        int inputValue = -1;
        do {
            try {
                System.out.print(inputMessage + ": ");
                inputValue = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                scanner.next();
            }
            if (inputValue < 1) {
                System.out.println(TextColor.yellowText("invalid input(must be a positive number)!"));
            }
        } while (inputValue < 1);
        return inputValue;
    }

    public static float takeFloatInputValue(String inputMessage) {
        float inputValue = -1;
        do {
            try {
                System.out.print(inputMessage + ": ");
                inputValue = scanner.nextFloat();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                scanner.next();
            }
            if (inputValue < 1) {
                System.out.println(TextColor.yellowText("invalid input(must be a positive number)!"));
            }
        } while (inputValue < 1);
        return inputValue;
    }

    public static boolean confirmation(String confirmationMessage) {
        String response;
        do {
            response = takeStringInputValue(confirmationMessage);
        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
        return response.equalsIgnoreCase("y");
    }
    public static String takeGender(String confirmationMessage) {
        String response;
        do {
            response = takeStringInputValue(confirmationMessage);
        } while (!response.equalsIgnoreCase("HOMME") && !response.equalsIgnoreCase("FEMME"));
        return response.toUpperCase();
    }
}
