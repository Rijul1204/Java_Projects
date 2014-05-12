package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/6/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */

public class Parser {

    int findHour(String pattern) {

        String[] splitString = pattern.split("[\\s+]");

        String regexForTime;
        regexForTime = "\\d+:\\d+:\\d+,\\d*"; // finding hour format

        for (String string : splitString) {
            if (string.matches(regexForTime)) {
                String[] splitTimes = string.split(":");
                String hour = splitTimes[0];
                int returnVal = Integer.parseInt(hour);
                return returnVal;
            }
        }

        return -1; // doesn't match the format;
    }

    int findResponseTime(String pattern) {

        String[] splitString = pattern.split("[\\s+]");

        String regexForResponseTime = "time=\\d*ms";

        for (String string : splitString) {

            if (string.matches(regexForResponseTime)) {

                String[] splitResponseTime = string.split("\\D+");

                for (String string1 : splitResponseTime) {
                    if (string1.matches("\\d+")) {
                        int returnVal = Integer.parseInt(string1);
                        return returnVal;
                    }
                }

            }
        }

        return 0;
    }

    public void process(String line, String basePattern, String userInputPattern, int[] hourAccessCount, int[] hourResponseTime) {

        if (line.contains(userInputPattern) && line.contains(basePattern)) {
            int hour = findHour(line);
            hourAccessCount[hour]++;
            int responseTime = findResponseTime(line);
            hourResponseTime[hour] += responseTime;
        }
    }


}
