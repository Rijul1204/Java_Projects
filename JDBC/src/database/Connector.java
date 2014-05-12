package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */

public class Connector implements Connectable {

    String userName, password, dbName, dbms, host;
    int port;
    private Connection connection;


    public Connector(String fileName) {

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.setUserName(prop.getProperty("user"));
            this.setPassWord(prop.getProperty("password"));
            this.setDBName(prop.getProperty("dbname"));
            this.setDBMS(prop.getProperty("dbms"));
            this.setHost(prop.getProperty("host"));
            this.setPort(Integer.parseInt(prop.getProperty("port")));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    Connector(String s1, String s2, String s3) {
        this.setUserName(s1);
        this.setPassWord(s2);
        this.setDBName(s3);
    }

    public Connection getConnection() {

        if (connection != null) return connection;

        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        try {
            connection = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.host + ":" + this.port + "/" + this.dbName, connectionProps);
        } catch (SQLException ex) {
        }

        System.out.println("Connected to database " + dbName);
        return connection;
    }

    public void setUserName(String s) {
        this.userName = s;

    }

    public void setPassWord(String s) {
        this.password = s;

    }

    public void setDBName(String s) {
        this.dbName = s;
    }

    public void setDBMS(String s) {
        this.dbms = s;
    }

    public void setHost(String s) {
        this.host = s;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
