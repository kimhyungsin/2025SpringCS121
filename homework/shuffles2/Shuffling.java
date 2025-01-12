/* *****************************************************************************
 *
 *  Description: This client class of Deck does various shuffling operations
 *               on deck and computes shuffle based properties.
 *
 **************************************************************************** */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Shuffling {

    /**
     * For a deck of maxRank starting in the factory order,
     * find the minimum number of out shuffles needed to get the deck
     * back to its original order.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return the smallest number of out-shuffles needed to get the deck
     * back to its original order
     */
    public static int minOutShuffles(int maxRank) {
        return 0;
    }

    /**
     * For a deck of maxRank starting in the factory order,
     * find the minimum number of in shuffles needed to get the deck
     * back to its original order.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return the smallest number of in-shuffles needed to get the deck
     * to its original order
     */
    public static int minInShuffles(int maxRank) {
        return 0;
    }

    /**
     * computes and returns a queue of decks, starting with the specified
     * deck and going through the shuffling sequence
     *
     * @param deck            the start deck
     * @param shuffleSequence a String representing a shuffling sequence, with
     *                        'I' representing in-shuffle and 'O' representing
     *                        out-shuffle
     * @return a queue of decks starting with the given deck and going through
     * the shuffling sequence, including each intermediate deck
     */
    public static Queue<Deck> computeDeckSequence(Deck deck, String shuffleSequence) {
        Queue<Deck> queue = new LinkedList<>();
        return queue;
    }

    /**
     * For a deck of maxRank starting in the factory order,
     * Find the shortest shuffle sequence that brings each card to the top.
     *
     * @param maxRank the max rank of the cards in the deck
     * @return a hashmap, with Card as the Key and String as the value,
     * showing the shortet shuffle sequence to bring each card to the top
     */
    public static HashMap<Card, String> findShortestShuffles(int maxRank) {
        //declare and create a hashmap, to save card - shortest shuffle sequence
        HashMap<Card, String> shortestShuffles = new HashMap<Card, String>();
        return shortestShuffles;
    }

    /*
     * Print the states of a deck as the specified shuffle sequence is
     * performed on it.
     *
     * @param maxRank         the initial deck state is a new deck with the
     *                        specified rank
     * @param shuffleSequence a String representing a shuffling sequence, with
     *                        'I' representing in-shuffle and 'O' representing
     *                        out-shuffle
     */
    private static void printSequence(int maxRank, String shuffleSequence) {
        String FORMAT_STRING = "  maxRank=%s, shuffleSequence=\"%s\"\n";
        Shuffling shuffling = new Shuffling();
        System.out.printf(FORMAT_STRING, maxRank, shuffleSequence);
        for (Deck deck : shuffling.computeDeckSequence(new ArrayDeck(maxRank),
                                                       shuffleSequence)) {
            System.out.println("    " + deck);
        }
    }

    /*
     * Print the shortest combination of shuffles needed to bring each card to
     * the top of a factor order deck
     *
     * @param maxRank the factory order deck has the specified rank
     */
    private static void printShortestShuffles(int maxRank) {
        Shuffling shuffling = new Shuffling();
        System.out.println("  maxRank=" + maxRank);
        String FORMAT_STRING = "    %-5s %s\n";
        System.out.printf(FORMAT_STRING, "Card", "Shuffle Sequence");
        HashMap<Card, String> shortestShuffles =
                shuffling.findShortestShuffles(maxRank);
        for (Card c : shortestShuffles.keySet()) {
            System.out.printf(FORMAT_STRING, c, shortestShuffles.get(c));
        }
    }

    public static void main(String[] args) {
        int highestRank = Integer.parseInt(args[0]);

        System.out.println("Minimum Numbers of Shuffles");
        String FORMAT_STRING = "  %-10s %-17s %-17s\n";
        System.out.printf(FORMAT_STRING,
                          "maxRank",
                          "Min Out Shuffles",
                          "Min In Shuffles");
        for (int i = 1; i <= highestRank; i++) {
            System.out.printf(FORMAT_STRING,
                              i,
                              minOutShuffles(i),
                              minInShuffles(i));
        }

        System.out.println("\nShuffle Sequences");
        printSequence(1, "OO");
        printSequence(2, "IIIIII");
        printSequence(1, "OIOI");

        System.out.println("\nShortest Shuffles");
        for (int i = 1; i <= highestRank; i++) {
            printShortestShuffles(i);
        }
    }
}
