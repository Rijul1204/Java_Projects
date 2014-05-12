package io;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

import query_processor.QuerySolver;

import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InputReader {

    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {

        try {
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        tokenizer = null;
    }

    public InputReader(String filename) {

        InputStream inputStream = null;
        if (filename.equals("System.in")) {
            inputStream = System.in;
        } else {
            try {
                inputStream = new FileInputStream(filename);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(QuerySolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(QuerySolver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getNextString() {

        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (Exception e) {
                return "";
            }
        }
        return tokenizer.nextToken();
    }
    public String getNextLine() {

        String ret="";
        try {
            ret=reader.readLine();
        } catch (Exception e) {
            return "";
        }
        return ret;
    }

    public int getNextInt() {
        return Integer.parseInt(getNextString());
    }
}
