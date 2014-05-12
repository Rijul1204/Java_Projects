package query_processor;

import database.Connectable;
import entity.Profile;
import entity.QueryString;
import entity.Status;
import io.InputReader;

import java.sql.Connection;


/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/27/14
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */


public class QuerySolver {

    public Connectable connector;
    private InputReader in;
    private Executor executor;
    private String command;

    public QuerySolver(Connectable connector, InputReader in) {

        this.connector = connector;
        this.in = in;
    }

    public void solve() {

        while (Parse()) {

            executor = new Executor(connector.getConnection());

            if (command.equals("view")) {

                String table = in.getNextString();
                executor.viewTable(table);
            }

            if (command.equals("add")) {

                String table = in.getNextString();
                if (table.equals("profile")) {
                    addProfile(in);
                }
                if (table.equals("status")) {
                    addStatus(in);
                }
            }

            if(command.equals("query")){

                executor.executeQuery(in.getNextLine());
            }

        }
    }

    public boolean Parse() {

        String s = in.getNextString();
        if (s.isEmpty() || s.equals("quit")) return false;

        command = s;

        return true;
    }

    public void addStatus(InputReader in) {
        QueryString pr = new Status(in.getNextString(), in.getNextString());
        //executor.insert("status", pr.getHashMap());
    }

    public void addProfile(InputReader in) {
        QueryString pr = new Profile(in.getNextString(), in.getNextString(), in.getNextString(), in.getNextInt());
        System.out.println(pr.getInsertQuery("profile"));
        executor.insert(pr.getInsertQuery("profile"), pr);

        //executor.insert("profile", pr.getHashMap());
    }

}
