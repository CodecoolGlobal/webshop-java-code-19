package com.codecool.shop.config;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static class App {

        private final String url = "jdbc:postgresql://localhost/codecoolshop";
        private final String user = "postgres";
        private final String password = "postgres";

        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         */
        public Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

        //ALTERNATIVE
        //PGSimpleDataSource dataSource = new PGSimpleDataSource();
        //dataSource.setUser(environments.get("APP_DB_USER_NAME"));
        //dataSource.setPassword(environments.get("APP_DB_PASSWORD"));
        //dataSource.setDatabaseName(dbName);
        //return dataSource;
    }
}
