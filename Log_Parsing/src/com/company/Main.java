package com.company;

import java.util.Scanner;


public class Main {

    static int[] hourAccessCount = new int[30];
    static int[] hourResponseTime = new int[30];
    static int numOfHour = 24;
    static String basePattern = "PROFILER";

    public static void main(String[] args) throws Exception {

        solve();
    }

    public static void solve() {

        Scanner inputFromSystem = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println("Please Enter File Name"); // /home/rashedul.hasan/therap.log
        String fileNameInput;
        fileNameInput = inputFromSystem.nextLine();
        FileInput inputFromFile = new FileInput(fileNameInput);

        String userInputPattern;
        System.out.println("Please Enter Pattern");
        userInputPattern = inputFromSystem.nextLine();

        String nextLine = inputFromFile.getNextLine();
        while (nextLine != null) {
            parser.process(nextLine, basePattern, userInputPattern, hourAccessCount, hourResponseTime);
            nextLine = inputFromFile.getNextLine();
        }

        System.out.println("Hour\t\tNumber of Access\t\tTotal Response Time");
        System.out.println("----\t\t----------------\t\t-------------------\n");

        for (int i = 0; i < numOfHour; i++) {
            System.out.format("%2d==>\t\t    %6d  \t\t\t\t %6d\n", i, hourAccessCount[i], hourResponseTime[i]);
        }
    }

}
