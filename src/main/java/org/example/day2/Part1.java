package org.example.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public Part1(){

        String filePath = "src/main/java/org/example/Day2/input.txt";
        List<String> input = ReturnInputAsList(filePath);
        int safeReportCounter = 0;

        for (String report : input){
            String[] levels = report.split("\\s+");
            int levelToCheck = Integer.parseInt(levels[0]);
            int numAsc = 0;
            int numDesc = 0;
            boolean isValid = false;

            for (int i = 1; i < levels.length; i++) {
                int currentLevel = Integer.parseInt(levels[i]);

                if (currentLevel > levelToCheck){
                    numAsc++;
                }
                else if (currentLevel < levelToCheck){
                    numDesc++;
                }

                levelToCheck = currentLevel;
            }

            if (numAsc == (levels.length - 1)){
                isValid = CheckValid(levels);
            }
            else if (numDesc == (levels.length - 1)){
                isValid = CheckValid(levels);
            }

            if (isValid){
                safeReportCounter++;
            }

        }

        System.out.println("The solution is: " + safeReportCounter);
    }

    public boolean CheckValid(String[] levels){
        int levelToCheck = Integer.parseInt(levels[0]);

        for (int i = 1; i < levels.length; i++) {
            int currentLevel = Integer.parseInt(levels[i]);

            if (Math.abs(currentLevel - levelToCheck) > 3){
                return false;
            }

            levelToCheck = currentLevel;
        }

        return true;
    }

    public List<String> ReturnInputAsList(String filePath)
    {
        List<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while(( line = br.readLine()) != null){
                input.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }
}
