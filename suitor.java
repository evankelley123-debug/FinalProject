import java.util.*;

public class Suitor {

    private static class Node {
        String name;
        int number;
        Node next;

        Node(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    public void runSuitor() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nSelect the Suitor.\n");
        System.out.print("Enter the number of Suitors: ");

        int n = Integer.parseInt(sc.nextLine());
        Node head = null, tail = null;

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter name of Suitor #" + i + ": ");
            String name = sc.nextLine();

            Node node = new Node(name, i);

            if (head == null) {
                head = tail = node;
                node.next = head;
            } else {
                tail.next = node;
                tail = node;
                tail.next = head;
            }
        }

        // Josephus Algorithm — eliminate every 3rd suitor
        Node current = head;
        Node previous = tail;

        while (current != current.next) {
            for (int count = 1; count < 3; count++) {
                previous = current;
                current = current.next;
            }
            System.out.println("Suitor #" + current.number + ", " + current.name + ", eliminated.");
            previous.next = current.next;
            current = current.next;
        }

        System.out.println("\nThe correct suitor was #" + current.number + ", " + current.name + ".\n");
    }
}
