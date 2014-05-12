package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/6/14
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileInput {

    BufferedReader inputFromFile;

    FileInput(String filename) {
        try {
            inputFromFile = new BufferedReader(new FileReader(filename));
        } catch (Exception E) {
            System.out.println("File not Found");
        }
    }

    String getNextLine() {
        String returnString;
        try {
            if ((returnString = inputFromFile.readLine()) != null) return returnString;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
