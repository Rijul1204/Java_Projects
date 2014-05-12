package query_processor;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Executor {

    private Connection conn;

    public Executor(Connection conn) {
        this.conn = conn;
    }

    public void viewTable(String table) {

        System.out.format("\n\nView " + table + "\n");

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM " + table;
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Printing Column Name . . .
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                System.out.format("%20s ", name);
            }
            System.out.println();

            // Printing Row . . .
            while (resultSet.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = resultSet.getString(i);
                    System.out.format("%20s ", name);
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

    }

    public void insert(String query, Object obj) {

        Field[] fields = obj.getClass().getDeclaredFields();
        List<Object> objs = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                objs.add(field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        executeInsertQuery(query, objs);
    }

    public void executeInsertQuery(String query, List<Object> objs) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for (Object parameter : objs) {
                if (parameter instanceof String) {
                    preparedStatement.setString(i, (String) parameter);
                } else if (parameter instanceof Integer) {
                    preparedStatement.setInt(i, (Integer) parameter);
                } else if (parameter instanceof Long) {
                    preparedStatement.setLong(i, (Long) parameter);
                } else if (parameter instanceof Float) {
                    preparedStatement.setFloat(i, (Float) parameter);
                } else if (parameter instanceof Double) {
                    preparedStatement.setDouble(i, (Double) parameter);
                } else if (parameter instanceof Date) {
                    preparedStatement.setDate(i, (Date) parameter);
                } else if (parameter instanceof Blob) {
                    preparedStatement.setBlob(i, (Blob) parameter);
                }
                i++;
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {

            }
        }
    }

    public void findMutualFriend(int id1, int id2) {

        Statement s = null;
        try {
            s = conn.createStatement();

            String s1 = "CREATE TEMPORARY TABLE AF AS ( SELECT profile.id FROM (profile,friendship) WHERE ( ( id1=" + id1 + "  AND profile.id=id2 AND profile.id!= " + id2 + " ) OR (id2=" + id1 + " AND profile.id=id1 AND profile.id!=" + id2 + " ) ))";
            String s2 = "CREATE TEMPORARY TABLE BF AS ( SELECT profile.id FROM (profile,friendship) WHERE ( ( id1=" + id2 + "  AND profile.id=id2 AND profile.id!= " + id1 + " ) OR (id2=" + id2 + " AND profile.id=id1 AND profile.id!=" + id1 + " ) ))";

            s.addBatch(s1);
            s.addBatch(s2);
            s.executeBatch();

            String s3 = "SELECT username FROM (profile,AF,BF) WHERE (AF.id=BF.id AND AF.id=profile.id)";

            try (ResultSet resultSet = s.executeQuery(s3)) {
                ResultSetMetaData rsmd = resultSet.getMetaData();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnName(i);
                    System.out.format("%20s ", name);
                }
                System.out.println();

                while (resultSet.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String name = resultSet.getString(i);
                        System.out.format("%20s ", name);
                    }
                    System.out.println();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void executeQuery(String nextLine) {
        //To change body of created methods use File | Settings | File Templates.

    }
}
