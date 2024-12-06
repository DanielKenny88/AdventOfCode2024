package org.example.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public Part1(){

        String filePath = "src/main/java/org/example/Day4/input.txt";
        char[][] wordSearch = readGridFromFile(filePath);
        String word = "XMAS";
        int counter = 0;

        int[][] directions = {
            {0, 1},   // Left to right
            {0, -1},  // Right to left
            {1, 0},   // Top to bottom
            {-1, 0},  // Bottom to top
            {1, 1},   // Top-left to bottom-right (diagonal)
            {1, -1},  // Bottom-left to top-right (diagonal)
            {-1, 1},  // Top-right to bottom-left (diagonal)
            {-1, -1}  // Bottom-right to top-left (diagonal)
        };

        for (int row = 0; row < wordSearch.length; row++) {
            for (int col = 0; col < wordSearch[0].length; col++) {
                for (int[] direction : directions) {
                    if (canFindWord(wordSearch, word, row, col, direction[0], direction[1])) {
                        System.out.println("Found 'XMAS' starting at (" + row + "," + col + ")");
                        counter++;
                    }
                }
            }
        }
        System.out.println("Found XMAS: " + counter + " times!");
    }

    public boolean canFindWord(char[][] wordSearch, String word, int row, int col, int dx, int dy) {
        int x = row;
        int y = col;

        for (int i = 0; i < 4; i++) {
            if (x < 0 || y < 0 || x >= wordSearch.length || y >= wordSearch[0].length) {
                return false;
            }

            if (wordSearch[x][y] != word.charAt(i)) {
                return false;
            }

            x += dx;
            y += dy;
        }
        return true;
    }

    private static char[][] readGridFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int rows = lines.size();
        int cols = rows > 0 ? lines.getFirst().length() : 0;
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }
}
