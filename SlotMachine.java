import java.util.Random;
import java.util.Scanner;


public class SlotMachine {

    private static final String[] SYMBOLS = {"🍒", "🍋", "🔔", "💎", "7️⃣", "⭐"};
    private static final double WIN_PROBABILITY = 0.01;
    private static final Random random = new Random();

    // Spins the slot machine and returns an array of three symbols
    public static String[] spin() {
        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            result[i] = SYMBOLS[random.nextInt(SYMBOLS.length)];
        }
        return result;
    }

    // Determines if the user wins (1% chance)
    public static boolean isWin() {
        return random.nextDouble() < WIN_PROBABILITY;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n💰🎰  Welcome to the Java Slot Machine!  🎰💰");
        System.out.println("Press <Enter> to spin or type 'q' to quit. Each spin has a 1% chance to win.\n");

        while (true) {
            System.out.print("Push the button and spin! → ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Thanks for playing. See you next time!");
                break;
            }

            String[] combo = spin();
            System.out.println(String.join(" | ", combo));

            if (isWin()) {
                System.out.println("\n🎉  JACKPOT! You win!  🎉\n");
            } else {
                System.out.println("No luck this time—try again!\n");
            }
        }

        scanner.close();
    }
}
