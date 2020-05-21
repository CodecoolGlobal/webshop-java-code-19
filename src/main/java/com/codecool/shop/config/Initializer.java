package com.codecool.shop.config;

import com.codecool.shop.dao.JDBC.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.JDBC.SupplierDaoJdbc;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ProductDaoJdbc productDataStore = null;
        ConnectionUtil connectionUtil = new ConnectionUtil();

        try {
            productDataStore = new ProductDaoJdbc(connectionUtil.connect());
            productDataStore.setProductCategoryDataStore(new ProductCategoryDaoJdbc(connectionUtil.connect()));
            productDataStore.setSupplierDataStore(new SupplierDaoJdbc(connectionUtil.connect()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            productDataStore.getAll();
    }


}
