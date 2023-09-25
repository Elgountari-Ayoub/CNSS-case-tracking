package ma.MaCNSS;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int choice ;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("****** Menu *******\n" +
                    "1 - space agent \n" +
                    "2 - space patient \n" +
                    "3 - space Admin");
            System.out.println("Please enter your choice ");
            choice = scanner.nextInt();
        }while(choice != 0);
        switch (choice){
            case 1 :


            case 2 :
                System.out.println("agent space");
            case 3 :
                System.out.println("agent space");

        }
    }
}