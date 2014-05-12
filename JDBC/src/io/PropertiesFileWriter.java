package io;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFileWriter {

    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("host","localhost");
            prop.setProperty("user", "root");
            prop.setProperty("password", "therap");
            prop.setProperty("dbms", "mysql");
            prop.setProperty("port","3306");
            prop.setProperty("dbname","facebook");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
