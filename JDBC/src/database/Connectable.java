package database;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Connectable {

    void setUserName(String s);
    void setPassWord(String s);
    void setDBName(String s);
    void setDBMS(String s);
    void setHost(String s);
    void setPort(int port);
    public Connection getConnection();
}
