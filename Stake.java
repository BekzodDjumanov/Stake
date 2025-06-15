import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Stake{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int balance = 100;
        int bet = 0;
        int payout;
        boolean playAgain = true;
        String[] row;
        int loss = 0;
        double lossBet = 0;
        
        System.out.println("***************************");
        System.out.println("Welcome to Slots at Stake!");
        System.out.println("***************************");
        System.out.println("Loss Condition: A reduction in balance by bet amount.");
        System.out.println("***************************");

        while(balance > 0){
            System.out.printf("Balance: $%s\n\n", balance);
            try {
                System.out.print("Bet Amount: ");
                bet = scanner.nextInt();
            } catch(InputMismatchException e){
                System.out.println("The Slot Machine has crashed.");
                System.exit(0);
            }

            if(bet > balance){
                System.out.println("Insufficient Funds!\n");
                System.out.println("***************************");
                continue;
            } else if(bet <= 0){
                System.out.println("Bet Required\n");
                System.out.println("***************************");
                continue;
            } else{
                loss += 1;
                if (loss > 2){
                    lossBet = bet * 1.4;
                    System.out.println("Loss Conditions Activated! Balance will reduce by 40% of bet!!");
                    balance -= lossBet;
                }
                else if (loss > 4){
                    lossBet = bet * 1.7;
                    System.out.println("Loss Conditions Activated! Balance will reduce by 70% of bet!!");
                    balance -= lossBet;
                }
                else{
                    balance -= bet;
                }
                System.out.println("***************************");
            }
            row = spinRow();
            printRow(row);
            payout = getPayout(row, bet);

            if(payout > 0){
                System.out.println("Winner!");
                System.out.printf("You won: $%d\n", payout);
                balance += payout;
                loss = 0;
            }
            else {
                System.out.println("Nothing this time.");
            }
        }
        System.out.println("Session ended.");
        
        scanner.close();
    }
    static String[] spinRow(){
        String[] emoji = {"🌕", "🌗", "🌑", "✨", "💫"};
        String[] row = new String[3];
        Random random = new Random();

        for (int i = 0; i < 3; ++i){
            row[i] = emoji[random.nextInt(5)];
        }
        return row;
    }
    static void printRow(String[] row){
        System.out.println(" " + String.join(" | ", row));
        System.out.println("***************************");
    }
    static int getPayout(String[] row, int bet){
        if (row[0].equals(row[1]) && row[1].equals(row[2])) {
            return switch(row[0]){
                case "🌕" -> bet * 5;
                case "🌗" -> bet * 4;
                case "🌑" -> bet * 4;
                case "✨" -> bet * 20;
                case "💫" -> bet * 1000;
                default -> 0;
            };
        }
        else if(row[0].equals(row[1])) {
            return switch(row[0]){
                case "🌕" -> bet * 4;
                case "🌗" -> bet * 3;
                case "🌑" -> bet * 2;
                case "✨" -> bet * 10;
                case "💫" -> bet * 50;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])){
            return switch(row[1]){
                case "🌕" -> bet * 4;
                case "🌗" -> bet * 3;
                case "🌑" -> bet * 2;
                case "✨" -> bet * 10;
                case "💫" -> bet * 50;
                default -> 0;
            };
        }
        return 0;
    }
}