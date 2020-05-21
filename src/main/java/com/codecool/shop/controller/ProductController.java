package com.codecool.shop.controller;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.JDBC.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.JDBC.SupplierDaoJdbc;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.CartItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.config.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //CONNECTION TEST
        try {
            DataSource dataSource = new ConnectionUtil().connect();
            Connection connection = dataSource.getConnection();
            connection.createStatement();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //CONNECTION TEST


        ConnectionUtil connectionUtil = new ConnectionUtil();
        ProductCategoryDao productCategoryDataStore = null;
        ProductDao productDataStore = null;
        try {
            productCategoryDataStore = new ProductCategoryDaoJdbc(connectionUtil.connect());
            productDataStore = new ProductDaoJdbc(connectionUtil.connect());
            ((ProductDaoJdbc) productDataStore).setProductCategoryDataStore(new ProductCategoryDaoJdbc(connectionUtil.connect()));
            ((ProductDaoJdbc) productDataStore).setSupplierDataStore(new SupplierDaoJdbc(connectionUtil.connect()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CartDaoMem cart = CartDaoMem.getInstance();
        CartItem cartItem = cart.find(1);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(1));
        // TODO need fixing this line
        context.setVariable("products", productDataStore.getAll());
        int orderedItemsCount = 0;
        if (!cart.getProducts().isEmpty()){
            orderedItemsCount = cartItem.getOrderedItemsCount(cart.getProducts());}
        context.setVariable("orderedItems", orderedItemsCount);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
