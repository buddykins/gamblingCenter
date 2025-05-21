import java.util.Scanner;
import java.util.Random;

public class SlotMachine {
    private String playerName;
    private double startingMoney;
    private double currentMoney;
    private static final String[] EMOJIS = {"🍒", "🍋", "🔔", "💎", "7️⃣", "🍀"};
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public SlotMachine() {
        System.out.print("Enter your name: ");
        this.playerName = scanner.nextLine();
        System.out.print("Enter your starting amount of money: $");
        this.startingMoney = scanner.nextDouble();
        this.currentMoney = this.startingMoney;
    }

    public void play() throws InterruptedException {
        System.out.println("Welcome, " + playerName + "! Let's play the slot machine!");

        while (currentMoney > 0) {
            System.out.print("You have $" + String.format("%.2f", currentMoney) + ". Enter your bet (or 0 to quit): $");
            double bet = scanner.nextDouble();

            if (bet == 0) {
                break;
            }

            if (bet > currentMoney || bet < 0) {
                System.out.println("Invalid bet. Please enter a valid amount.");
                continue;
            }

            System.out.println("Spinning...");
            String[] result = new String[3];

            for (int i = 0; i < 3; i++) {
                Thread.sleep(700); // delay to simulate spinning
                result[i] = EMOJIS[random.nextInt(EMOJIS.length)];
                System.out.print(result[i] + " ");
            }

            System.out.println();

            boolean isWin = isWinningCombo(result);
            if (isWin && random.nextDouble() < 0.05) {
                double winnings = bet * 10;
                currentMoney += winnings;
                System.out.println("🎉 JACKPOT! You won $" + String.format("%.2f", winnings) + "!");
            } else {
                currentMoney -= bet;
                System.out.println("😢 You lost $" + String.format("%.2f", bet));
            }

            if (currentMoney <= 0) {
                System.out.println("You're out of money! Game over.");
                break;
            }
        }

        double netEarnings = currentMoney - startingMoney;
        System.out.println("\nThank you for playing, " + playerName + "!");
        System.out.println("Your final balance is $" + String.format("%.2f", currentMoney));
        System.out.println("Net earnings: $" + String.format("%.2f", netEarnings));
    }

    private boolean isWinningCombo(String[] result) {
        return result[0].equals(result[1]) && result[1].equals(result[2]);
    }

    public static void main(String[] args) throws InterruptedException {
        SlotMachine machine = new SlotMachine();
        machine.play();
    }
}
