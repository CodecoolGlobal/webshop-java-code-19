package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
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
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartItems", cart.getProducts());
//        context.setVariable("subTotalPrice", );
        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDaoMem cartDao = CartDaoMem.getInstance();
        ProductDaoMem productDao = ProductDaoMem.getInstance();
        String id = req.getParameter("id");
        int productId = Integer.parseInt(id);
        Product product = productDao.find(productId);
        CartItem cartItem = cartDao.find(productId);
        if (cartItem == null) {
            cartDao.addToCart(new CartItem(1, product));
        } else {
            cartItem.changeQuantity(1);
        }
        int orderedItemsCount = cartItem.getOrderedItemsCount(cartDao.getProducts());

        resp.setContentType("application/json");
        resp.getWriter().write("{\"orderedItems\":" + orderedItemsCount + "}");
    }
}

