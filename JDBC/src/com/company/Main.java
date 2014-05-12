package com.company;

import database.Connectable;
import database.Connector;
import io.InputReader;
import query_processor.QuerySolver;


public class Main {

    public static void main(String[] args) {

        Connectable connector = new Connector("config.properties");

        String filename = "input.txt";
        InputReader in = new InputReader(filename);

        QuerySolver solver = new QuerySolver(connector, in);
        solver.solve();

    }
}
