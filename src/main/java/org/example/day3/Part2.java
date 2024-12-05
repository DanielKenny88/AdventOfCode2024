package org.example.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2(){
        String filePath = "src/main/java/org/example/Day3/input.txt";
        List<String> corruptedMemory = ReturnInputAsList(filePath);
        String regex = "mul\\((\\d{1,3}),\\d{1,3}\\)|do\\(\\)|don't\\(\\)";
        Pattern mulPattern = Pattern.compile(regex);
        boolean toggle = true;

        List<String> matches = new ArrayList<>();
        int result = 0;

        for (String section : corruptedMemory){

            Matcher mulMatcher = mulPattern.matcher(section);

            while (mulMatcher.find()) {
                matches.add(mulMatcher.group());
            }
        }

        for (String match : matches){
            int num1;
            int num2;

            if (match.contains("do()")){
                toggle = true;
            } else if (match.contains("don't()")) {
                toggle = false;
            } else {
                if (toggle){
                    num1 = Integer.parseInt(match.split(",")[0].split("\\(")[1]);
                    num2 = Integer.parseInt(match.split(",")[1].split("\\)")[0]);
                    result += num1 * num2;
                }
            }
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
