package com.codecool.shop.config;

import com.codecool.shop.dao.JDBC.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.JDBC.SupplierDaoJdbc;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.ConnectionUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ProductDao productDataStore = null;
        ConnectionUtil connectionUtil = new ConnectionUtil();

        try {
            productDataStore = new ProductDaoJdbc(connectionUtil.connect());
            ((ProductDaoJdbc) productDataStore).setProductCategoryDataStore(new ProductCategoryDaoJdbc(connectionUtil.connect()));
            ((ProductDaoJdbc) productDataStore).setSupplierDataStore(new SupplierDaoJdbc(connectionUtil.connect()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            productDataStore.getAll();
    }


}
