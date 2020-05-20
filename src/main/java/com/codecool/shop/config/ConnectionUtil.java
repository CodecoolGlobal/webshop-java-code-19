package com.codecool.shop.config;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

        private final String database = "codecoolshop";
        private final String user = "postgres";
        private final String password = "postgres";

        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         */
        public DataSource connect() throws SQLException {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUser(user);
            dataSource.setPassword(password);
            dataSource.setDatabaseName(database);
            System.out.println("Connected to the PostgreSQL server successfully.");
            return dataSource;
        }
    }

