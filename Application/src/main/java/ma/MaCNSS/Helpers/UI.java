package ma.MaCNSS.Helpers;

public class UI {
    public static void MAIN_MENU() {
        System.out.println(
                TextColor.
                        yellowText("\t\t\t\t\tMA CNSS\n\n\n") +
                        "1-Admin\n" +
                        "2-Agent CNSS\n" +
                        "3-Patient\n" +
                        "4-Company\n" +
                        "0- Exit\n"
        );
    }

    public static void ADMIN_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Add agent\n" +
                                "\t0- Go back"));
    }

    public static void AGENT_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Submit a file\n" +
                                "\t2- Add a company\n" +
                                "\t0- Go back"));
    }

    public static void PATIENT_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Display my files\n" +
                                "\t2 - Check retirement" +
                                "\t0- Go back"));
    }

    public static void COMPANY_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Add Employee\n" +
                                "\t2- Update Employee Presence Status\n" +
                                "\t0- Go back"));
    }
}
