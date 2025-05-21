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

        while (true) {
            try {
                System.out.print("Enter your starting amount of money: $");
                this.startingMoney = Double.parseDouble(scanner.nextLine());
                if (startingMoney <= 0) {
                    System.out.println("Amount must be greater than zero.");
                    continue;
                }
                this.currentMoney = this.startingMoney;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void play() {
        System.out.println("Welcome, " + playerName + "! Let's play the slot machine!");

        while (currentMoney > 0) {
            double bet = getValidBet();
            if (bet == 0) {
                break;
            }

            System.out.println("Spinning...");
            String[] result = new String[3];

            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(700); // simulate spinning delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // reset interrupted status
                    System.out.println("Unexpected interruption. Exiting game.");
                    return;
                }

                result[i] = EMOJIS[random.nextInt(EMOJIS.length)];
                System.out.print(result[i] + " ");
            }
            System.out.println();

            boolean isWin = isWinningCombo(result) && random.nextDouble() < 0.05;
            if (isWin) {
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

    private double getValidBet() {
        while (true) {
            try {
                System.out.print("You have $" + String.format("%.2f", currentMoney) + ". Enter your bet (or 0 to quit): $");
                double bet = Double.parseDouble(scanner.nextLine());

                if (bet == 0) {
                    return 0;
                }

                if (bet > currentMoney || bet < 0) {
                    System.out.println("Invalid bet. Must be between $0 and $" + String.format("%.2f", currentMoney));
                } else {
                    return bet;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private boolean isWinningCombo(String[] result) {
        return result[0].equals(result[1]) && result[1].equals(result[2]);
    }

    public static void main(String[] args) {
        SlotMachine machine = new SlotMachine();
        machine.play();
    }
}
