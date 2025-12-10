import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Suitor suitor = new Suitor();
        HauntedHouse haunted = new HauntedHouse();
        Sentiment sentiment = new Sentiment();

        String option = "";

        while (true) {
            System.out.println("1. Select the Suitor.");
            System.out.println("2. Escape the Haunted House.");
            System.out.println("3. Sentiment Analysis.");
            System.out.println();
            System.out.println("Q. Quit.\n");

            System.out.print("Your option ==> ");
            option = sc.nextLine().trim();

            if (option.equalsIgnoreCase("Q")) {
                System.out.println("Exiting Program...");
                break;
            }

            switch (option) {
                case "1":
                    suitor.runSuitor();
                    break;

                case "2":
                    haunted.runHauntedHouse();
                    break;

                case "3":
                    sentiment.runSentiment();
                    break;

                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
    }
}
