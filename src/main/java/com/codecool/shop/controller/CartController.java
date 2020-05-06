package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDaoMem cart = CartDaoMem.getInstance();
        ProductDaoMem product = ProductDaoMem.getInstance();
        String id = req.getParameter("id");
        cart.addToCart(product.find(Integer.parseInt(id)));
        int orderedItems = cart.getOrder().size();
        resp.setContentType("application/json");
        resp.getWriter().write("{\"orderedItems\":"+orderedItems+"}");

    }


}
