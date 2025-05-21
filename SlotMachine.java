import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
    private String playerName;
    private double balance;
    private double startingBalance;
    private final String[] emojis = {"🍒", "🍋", "🍉", "⭐", "🔔", "💎"};
    private final Random rand = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public SlotMachine() {
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.print("Hello " + playerName + "! Enter your starting money: $");
        balance = scanner.nextDouble();
        startingBalance = balance;
        System.out.println("Let's play!");
        startGame();
    }

    private void startGame() {
        while (balance > 0) {
            System.out.printf("You have $%.2f. How much do you want to bet? $", balance);
            double bet = scanner.nextDouble();

            if (bet <= 0 || bet > balance) {
                System.out.println("Invalid bet. Try again.");
                continue;
            }

            System.out.println("Spinning...");
            String[] spinResult = spinReels();

            System.out.println("\nResult:");
            for (String emoji : spinResult) {
                System.out.print(emoji + " ");
            }
            System.out.println();

            if (isWinningCombination(spinResult)) {
                double winnings = bet * 10;
                balance += winnings;
                System.out.printf("🎉 You won! You earned $%.2f%n", winnings);
            } else {
                balance -= bet;
                System.out.println("😢 You lost!");
            }

            if (balance <= 0) {
                System.out.println("You're out of money!");
                break;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("yes")) {
                break;
            }
        }

        double netEarnings = balance - startingBalance;
        System.out.printf("Session ended. Net earnings: $%.2f%n", netEarnings);
    }

    private String[] spinReels() {
        String[] result = new String[3];
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000); // Simulate spin delay
                result[i] = emojis[rand.nextInt(emojis.length)];
                System.out.print(result[i] + " ");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5% chance to override with a winning combo
        if (rand.nextInt(100) < 5) {
            String winEmoji = emojis[rand.nextInt(emojis.length)];
            result[0] = result[1] = result[2] = winEmoji;
        }

        return result;
    }

    private boolean isWinningCombination(String[] spin) {
        return spin[0].equals(spin[1]) && spin[1].equals(spin[2]);
    }

    public static void main(String[] args) {
        new SlotMachine();
    }
}
