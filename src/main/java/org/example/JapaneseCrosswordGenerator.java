package src.main.java.org.example;

import java.util.ArrayList;
import java.util.List;

public class JapaneseCrosswordGenerator {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("APPLE");
        words.add("LEMON");
        words.add("GRAPE");
        words.add("BANANA");
        words.add("CHERRY");

        int crosswordSize = 10;

        int[][] crossword = generateCrossword(words, crosswordSize);

        // Виведення кросворду
        for (int i = 0; i < crosswordSize; i++) {
            for (int j = 0; j < crosswordSize; j++) {
                System.out.print(crossword[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] generateCrossword(List<String> words, int size) {
        int[][] crossword = new int[size][size];
        int wordIndex = 0;

        // Розміщення слів у кросворді
        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                int row = (int) (Math.random() * size);
                int col = (int) (Math.random() * size);
                int direction = (int) (Math.random() * 2); // 0 - горизонтально, 1 - вертикально

                if (direction == 0 && col + word.length() <= size) {
                    boolean overlap = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (crossword[row][col + i] != 0 && crossword[row][col + i] != word.charAt(i)) {
                            overlap = true;
                            break;
                        }
                    }

                    if (!overlap) {
                        for (int i = 0; i < word.length(); i++) {
                            crossword[row][col + i] = word.charAt(i);
                        }
                        placed = true;
                    }
                } else if (direction == 1 && row + word.length() <= size) {
                    boolean overlap = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (crossword[row + i][col] != 0 && crossword[row + i][col] != word.charAt(i)) {
                            overlap = true;
                            break;
                        }
                    }

                    if (!overlap) {
                        for (int i = 0; i < word.length(); i++) {
                            crossword[row + i][col] = word.charAt(i);
                        }
                        placed = true;
                    }
                }
            }

            wordIndex++;
            if (wordIndex >= words.size()) {
                wordIndex = 0;
            }
        }

        // Заповнення порожніх клітинок кросворду
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (crossword[i][j] == 0) {
                    crossword[i][j] = '-';
                }
            }
        }

        return crossword;
    }
}
