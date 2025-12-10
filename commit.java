import java.util.*;
import java.io.*;

public class Sentiment {

    HashSet<String> positives = new HashSet<>();
    HashSet<String> negatives = new HashSet<>();

    public Sentiment() {
        try {
            loadWords("positive.txt", positives);
            loadWords("negative.txt", negatives);
        } catch (Exception e) {
            System.out.println("Error loading sentiment files: " + e.getMessage());
        }
    }

    private void loadWords(String filename, HashSet<String> set) throws Exception {
        Scanner file = new Scanner(new File(filename));
        while (file.hasNextLine()) {
            String word = file.nextLine().trim().toLowerCase();
            if (word.length() > 0 && !word.startsWith(";"))
                set.add(word);
        }
        file.close();
    }

    public void runSentiment() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nSentiment Analysis.\n");
        System.out.println(positives.size() + " Positive Words Successfully Loaded.");
        System.out.println(negatives.size() + " Negative Words Successfully Loaded.\n");

        while (true) {
            System.out.print("Enter the name of the text file to perform sentiment analysis: ");
            String filename = sc.nextLine().trim();

            try {
                analyzeFile(filename);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\nWould you like to analyze another file Y/N? ");
            String again = sc.nextLine().trim();
            if (!again.equalsIgnoreCase("Y")) {
                System.out.println();
                break;
            }
            System.out.println();
        }
    }

    private void analyzeFile(String filename) throws Exception {

        Scanner file = new Scanner(new File(filename));

        int total = 0;
        int pos = 0;
        int neg = 0;

        while (file.hasNext()) {
            String word = file.next().toLowerCase().replaceAll("[^a-z]", "");
            if (word.length() == 0) continue;

            total++;

            if (positives.contains(word))
                pos++;
            else if (negatives.contains(word))
                neg++;
        }
        file.close();

        System.out.println("\nSentiment Report for " + filename + ":");
        System.out.println("There were " + pos + " positive words, " + neg + " negative words and " + total + " total words.");

        double posPct = (total == 0 ? 0 : (double) pos / total);
        double negPct = (total == 0 ? 0 : (double) neg / total);

        System.out.printf("That's %.0f%% positive and %.0f%% negative.  ", posPct * 100, negPct * 100);

        if (posPct - negPct >= 0.05)
            System.out.println("Overall the file's sentiment was positive.");
        else if (negPct - posPct >= 0.05)
            System.out.println("Overall the file's sentiment was negative.");
        else
            System.out.println("Overall the file's sentiment was neutral.");
    }
}
