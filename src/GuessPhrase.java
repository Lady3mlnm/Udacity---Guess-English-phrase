//import java.util.Arrays;
//import java.util.Scanner;

public class GuessPhrase {
    final static int maxRounds = 2;

    public static void main(String[] args) {
        int counterRounds;
        String summaryStr = "";
        PhraseGenerator PG = new PhraseGenerator();
        char gameResult = '-';

        System.out.println("   === GAME START ===");
        System.out.println("Enter single symbol '^' to break the game and exit.\n");

        for (counterRounds = 1; counterRounds <= maxRounds; counterRounds++) {
            summaryStr = PG.getPhrase();
            if (summaryStr.equals("")){
                break;
            }
            gameResult = SingleRound.game(summaryStr);
            if (gameResult == 'X') {
                counterRounds--;
                PG.addPhrase(summaryStr);
            } else if (gameResult == '^') {
                break;
            }
        }
        counterRounds--;

        System.out.println("\n------\n");
        if (summaryStr.equals("")) {
            System.out.println("Great!\n" +
                    "Number of phrases in the file with tasks is less then maximum number of rounds in the game.\n" +
                    "Now the game is silent. You mastered " + counterRounds + " phrases.");
        } else if (gameResult == '^') {
            System.out.println("You choose to break the game. This is your decision.");
        } else if (gameResult == 'V') {
            System.out.println("Excellent!! " +
                    "You mastered all " + counterRounds + " phrases!");
        } else {
            System.out.println("The game is over with not standard result. ERROR. Please, report about this to developer.");
        }
    }
}
