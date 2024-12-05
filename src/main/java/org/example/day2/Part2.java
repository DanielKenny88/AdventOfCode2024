package org.example.day2;

import java.io.*;
import java.util.*;

public class Part2 {
    public Part2() {

        String fileName = "src/main/java/org/example/Day2/input.txt";
        int safeReportCounter = 0;

        List<List<Integer>> allReports = GetAllReportsFromFile(fileName);

        for (List<Integer> report : allReports){
            if (ListIsAscendingOrDescending(report) && NumbersLessThanThreeApart(report)){
                safeReportCounter++;
            }else if (RemoveOneWillBeValid(report)){
                safeReportCounter++;
            }
        }

        System.out.println("The solution for part 2 is : " + safeReportCounter);
    }

    public List<List<Integer>> GetAllReportsFromFile(String fileName){

        List<List<Integer>> allReports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] levels = line.split(" ");
                List<Integer> numbers = new ArrayList<>();

                for (String level : levels) {
                    numbers.add(Integer.parseInt(level));
                }

                allReports.add(numbers);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allReports;
    }

    public boolean ListIsAscendingOrDescending(List<Integer> report){
        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 0; i < report.size() - 1; i++) {
            if (Objects.equals(report.get(i), report.get(i + 1))){
                isAscending = false;
                isDescending = false;
            }
            if (report.get(i) > report.get(i + 1)) {
                isAscending = false;
            }
            if (report.get(i) < report.get(i + 1)) {
                isDescending = false;
            }
        }

        return isAscending || isDescending;
    }

    public boolean NumbersLessThanThreeApart(List<Integer> report){
        for (int i = 0; i < report.size() - 1; i++) {
            if (Math.abs(report.get(i) - report.get(i + 1)) > 3) {
                return false;
            }
        }
        return true;
    }

    public boolean RemoveOneWillBeValid(List<Integer> report){
        for (int i = 0; i < report.size(); i++) {
            List<Integer> reducedReport = new ArrayList<>(report);
            reducedReport.remove(i);

            if (ListIsAscendingOrDescending(reducedReport) && NumbersLessThanThreeApart(reducedReport)){
                return true;
            }
        }
        return false;
    }
}
