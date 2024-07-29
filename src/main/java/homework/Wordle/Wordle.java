package homework.Wordle;

import java.util.*;

public class Wordle {
    static final int ALPHABET_SIZE = 26;            // The size of the alphabet
    static final int WORD_LENGTH = 5;               // The length of words
    static final int TOTAL_CHANCES = 6;             // The chances in total

    // Guess `word` at state `s`
    private static void updateAlphabetState(State s, char c, Color color) {
        if(color == Color.GREEN)
            s.alphabetState[c - 'A'] = Color.GREEN;
        else if(color == Color.YELLOW && s.alphabetState[c - 'A'] != Color.GREEN)
            s.alphabetState[c - 'A'] = Color.YELLOW;
        else if(color == Color.RED && s.alphabetState[c - 'A'] != Color.GREEN && s.alphabetState[c - 'A'] != Color.YELLOW)
            s.alphabetState[c - 'A'] = Color.RED;
    }
    public static State guess(State s) {
        // TODO begin
        s.chancesLeft--;
        if (s.word.equals(s.answer)) {
            s.status = GameStatus.WON;
        } else if (s.chancesLeft == 0) {
            s.status = GameStatus.LOST;
        }
        int[] answerCount = new int[ALPHABET_SIZE];
        for (int i = 0; i < WORD_LENGTH; i++)
            answerCount[s.answer.charAt(i) - 'A']++;
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (s.answer.charAt(i) == s.word.charAt(i)) {
                s.wordState[i] = Color.GREEN;
                answerCount[s.answer.charAt(i) - 'A']--;
                updateAlphabetState(s, s.word.charAt(i), Color.GREEN);
            }
        }
        for (int i = 0; i < WORD_LENGTH; i++){
            if (s.answer.charAt(i) == s.word.charAt(i))
                continue;
            if(answerCount[s.word.charAt(i) - 'A'] > 0) {
                s.wordState[i] = Color.YELLOW;
                answerCount[s.word.charAt(i) - 'A']--;
                updateAlphabetState(s, s.word.charAt(i), Color.YELLOW);
            }
            else {
                s.wordState[i] = Color.RED;
                updateAlphabetState(s, s.word.charAt(i), Color.RED);
            }
        }
        // TODO end
        return s;
    }
    public static void main(String[] args) {
        // Read word sets from files
        WordSet wordSet = new WordSet("assets/wordle/FINAL.txt", "assets/wordle/ACC.txt");

        Scanner input = new Scanner(System.in);
        // Keep asking for an answer if invalid
        String answer;
        do {
            System.out.print("Enter answer: ");
            answer = input.nextLine().toUpperCase().trim();
            if (wordSet.isNotFinalWord(answer)) {
                System.out.println("INVALID ANSWER");
            }
        } while (wordSet.isNotFinalWord(answer));

        State state = new State(answer);
        while (state.status == GameStatus.RUNNING) {
            // Keep asking for a word guess if invalid
            String word;
            do {
                System.out.print("Enter word guess: ");
                word = input.nextLine().toUpperCase().trim();
                if (wordSet.isNotAccWord(word)) {
                    System.out.println("INVALID WORD GUESS");
                }
            } while (wordSet.isNotAccWord(word));
            // Try to guess a word
            state.word = word;
            state = guess(state);
            state.show();
        }
        if (state.status == GameStatus.LOST) {
            System.out.println("You lost! The correct answer is " + state.answer + ".");
        } else {
            System.out.println("You won! You only used " + (TOTAL_CHANCES - state.chancesLeft) + " chances.");
        }
    }
}
