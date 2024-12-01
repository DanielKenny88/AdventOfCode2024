package org.example.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public Part2(){
        String filePath = "src/main/java/org/example/Day1/input.txt";
        List<String> input = ReturnInputAsList(filePath);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int result = 0;

        for (String line : input){
            int listOneNumber = Integer.parseInt(line.split("\\s+")[0]);
            int listTwoNumber = Integer.parseInt(line.split("\\s+")[1]);

            list1.add(listOneNumber);
            list2.add(listTwoNumber);
        }

        for (int locationId : list1){
            if (list2.contains(locationId)){
                result += locationId * Collections.frequency(list2, locationId);
            }
        }

        System.out.println("The result is:" + result);
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
