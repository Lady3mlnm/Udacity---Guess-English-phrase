import java.util.Scanner;

public class SingleRound {
    final static char chX = '.';

    public static char game (String summaryStr) {
        char[] lettersCorrect = new char[27];
        int counterCorrect = 0;
        char[] lettersWrong = new char[5]; //The length of array lettersWrong determined how many mistakes player can made
        int counterWrong = 0;
        Scanner scanner = new Scanner(System.in);

        // temp
        // counterWrong = 3;
        // lettersWrong[0] = 'q';
        // lettersWrong[1] = 'w';
        // lettersWrong[2] = 'e';

        int iSplit = summaryStr.indexOf(" ~ ");
        String hintStr = summaryStr.substring(iSplit + 3);
        String wantedStr = summaryStr.substring(0, iSplit);
        String wantedStrLower = wantedStr.toLowerCase();
        char[] guessedStr = InitializeGuessedStr(wantedStr);

        System.out.println("wantedStr:      " + wantedStr);
        System.out.println("wantedStrLower: " + wantedStrLower);
        System.out.println("guessedStr:     " + String.copyValueOf(guessedStr) + "\n");
        System.out.println("Phrase meaning or hint:\n\"" + hintStr + "\"\n");

        String choiceStr;
        char choiceLetter = ' ';
        do {
            boolean isLetterFound;

            System.out.println("You are guessing: " + String.copyValueOf(guessedStr));

            if (counterWrong >= 1) {
                System.out.print("Wrong letters: ");
                for (int i = 0; i < counterWrong; i++)
                    System.out.print(lettersWrong[i] + " ");
                System.out.println("[" + counterWrong + "]");
            }

            //Receive a letter from player
            do {
                System.out.print("Guess a letter: ");
                choiceStr = scanner.nextLine().toLowerCase();
            } while (choiceStr.equals(""));

            if (choiceStr.length() == 1) {
                choiceLetter = choiceStr.charAt(0);
                //if entered symbol is '^' then break the game
                // else check if entered symbol is letter or apostrophe
                if (choiceLetter == '^') {
                    break;
                } else if (!Character.isLetter(choiceLetter) && choiceLetter != '\'') {
                    System.out.println("In this program it is allowed only letters and apostrophe (').\n");
                    continue;
                }
                //Check if this letter already was in use
                if (IsCharInArray(lettersWrong, lettersCorrect, choiceLetter)) {
                    System.out.println("Please, attend! You've already tried this letter.\n");
                    continue;
                }

                isLetterFound = false;
                for (int i = 0; i < guessedStr.length; i++) {
                    if (guessedStr[i] == chX && choiceLetter == wantedStrLower.charAt(i)) {
                        guessedStr[i] = wantedStr.charAt(i);
                        isLetterFound = true;
                    }
                }

                if (isLetterFound) {
                    System.out.println("YES\n");
                    lettersCorrect[counterCorrect++] = choiceLetter;
                } else {
                    System.out.println("NO\n");
                    lettersWrong[counterWrong++] = choiceLetter;
                }
            } else {  //if line consists of several characters
                for (int i=0; i<choiceStr.length(); i++){
                    choiceLetter = choiceStr.charAt(i);

                    if (!Character.isLetter(choiceLetter) && choiceLetter != '\'')
                        continue;

                    if (IsCharInArray(lettersWrong, lettersCorrect, choiceLetter))
                        continue;

                    isLetterFound = false;
                    for (int j = 0; j < guessedStr.length; j++) {
                        if (guessedStr[j] == chX && choiceLetter == wantedStrLower.charAt(j)) {
                            guessedStr[j] = wantedStr.charAt(j);
                            isLetterFound = true;
                        }
                    }

                    if (isLetterFound) {
                        lettersCorrect[counterCorrect++] = choiceLetter;
                        if (!IsCharInArray(guessedStr, chX))
                            break;
                    } else {
                        lettersWrong[counterWrong++] = choiceLetter;
                        if (counterWrong >= lettersWrong.length) {
                            System.out.println("It was too dangerous to enter several letters at once. " +
                                    " Your guess is not correct and you exceed the limit of wrong letters.");
                            break;
                        }
                    }
                }
                System.out.println();
            }

        } while (counterWrong < lettersWrong.length && IsCharInArray(guessedStr, chX));

        if (choiceLetter == '^') {
            return '^';
        } else if (counterWrong < lettersWrong.length) {
            System.out.println("Good job! You win!\n\n\n");
            return 'V';
        } else {
            System.out.println("This round is not yours! Try again!\n\n\n");
            return 'X';
        }

    }



    public static char[] InitializeGuessedStr(String wantedStr){
        int lng = wantedStr.length();
        char[] guessedStr = new char[lng];

        for(int i=0; i<lng; i++){
            char ch = wantedStr.charAt(i);
            if (ch=='(') {
                guessedStr[i] = ch;
                do {
                    i++;
                    ch = wantedStr.charAt(i);
                    guessedStr[i] = ch;
                } while (ch != ')' && i < lng - 1);
            } else if (ch=='['){
                guessedStr[i] = ch;
                do {
                    i++;
                    ch = wantedStr.charAt(i);
                    guessedStr[i] = ch;
                } while (ch!=']' && i<lng-1);
            } else if (Character.isLetter(ch) || ch=='\'')
                guessedStr[i] = chX;
            else
                guessedStr[i] = ch;
        }

        return guessedStr;
    }



    public static boolean IsCharInArray(char[] arCh, char ch){
        for (char t : arCh)
            if (t==ch)
                return true;
        return false;
    }



    public static boolean IsCharInArray(char[] arCh1, char[] arCh2, char ch){
        for (char t : arCh1)
            if (t==ch)
                return true;
        for (char t : arCh2)
            if (t==ch)
                return true;
        return false;
    }
}
