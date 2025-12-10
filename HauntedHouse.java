import java.util.*;

public class HauntedHouse {

    private static class Node {
        String name;
        Node north, south, east, west;

        Node(String name) {
            this.name = name;
        }
    }

    private Node A, L;

    public HauntedHouse() {
        createMap();
    }

    private void createMap() {
        // Create nodes
        A = new Node("A"); Node B = new Node("B"); Node C = new Node("C"); Node D = new Node("D");
        Node E = new Node("E"); Node F = new Node("F"); Node G = new Node("G"); Node H = new Node("H");
        Node I = new Node("I"); Node J = new Node("J"); Node K = new Node("K"); L = new Node("L");

        // Row 1
        A.east = B;
        B.west = A; B.east = C;
        C.west = B; C.east = D;
        D.west = C; D.south = H;

        // Row 2
        A.south = E;
        E.north = A; E.east = F;
        F.west = E; F.east = G; F.south = J;
        G.west = F; G.east = H; G.south = K;
        H.west = G; H.north = D; H.south = L;

        // Row 3
        E.south = I;
        I.north = E; I.east = J;
        J.west = I; J.north = F; J.east = K;
        K.west = J; K.north = G; K.east = L;
        L.west = K; L.north = H;
    }

    public void runHauntedHouse() {
        Scanner sc = new Scanner(System.in);
        Node current = A;
        String move = "";

        System.out.println("\nEscape the Haunted House.\n");

        while (!current.equals(L)) {
            System.out.println("You are in room " + current.name + " of the Haunted House.");

            System.out.print("You can go ");
            boolean printed = false;

            if (current.north != null) { System.out.print("north "); printed = true; }
            if (current.south != null) { 
                if (printed) System.out.print("or ");
                System.out.print("south "); 
                printed = true; 
            }
            if (current.east != null) { 
                if (printed) System.out.print("or ");
                System.out.print("east "); 
                printed = true; 
            }
            if (current.west != null) { 
                if (printed) System.out.print("or ");
                System.out.print("west "); 
            }

            System.out.println(".");
            move = sc.nextLine().trim().toUpperCase();

            Node next = null;
            switch (move) {
                case "N": next = current.north; break;
                case "S": next = current.south; break;
                case "E": next = current.east; break;
                case "W": next = current.west; break;
                default:
                    System.out.println("Invalid direction!");
                    continue;
            }

            if (next == null) {
                System.out.println("You can't go in that direction!\n");
            } else {
                current = next;
                System.out.println();
            }
        }

        System.out.println("You are in room L of the Haunted House. You made it out alive!\n");
    }
}
