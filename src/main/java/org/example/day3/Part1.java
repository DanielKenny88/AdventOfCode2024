package org.example.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public Part1(){

        String filePath = "src/main/java/org/example/Day3/input.txt";
        List<String> corruptedMemory = ReturnInputAsList(filePath);
        String regex = "mul\\((\\d{1,3}),\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);
        List<String> matches = new ArrayList<>();
        int result = 0;

        for (String section : corruptedMemory){

            Matcher matcher = pattern.matcher(section);
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }

        for (String match : matches){
            int num1;
            int num2;

            num1 = Integer.parseInt(match.split(",")[0].split("\\(")[1]);
            num2 = Integer.parseInt(match.split(",")[1].split("\\)")[0]);
            result += num1 * num2;
        }

        System.out.println("Result is : " + result);

    }

    public List<String>ReturnInputAsList(String filePath)
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
