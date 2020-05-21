package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private final DataSource dataSource;
    ConnectionUtil connectionUtil = new ConnectionUtil();
    SupplierDao supplierDataStore = null;
    ProductCategoryDao productCategoryDataStore = null;
    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setSupplierDataStore(SupplierDao supplierDataStore) {
        try {
            this.supplierDataStore = new SupplierDaoJdbc(connectionUtil.connect());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setProductCategoryDataStore(ProductCategoryDao productCategoryDataStore) {
        try {
            this.productCategoryDataStore = new ProductCategoryDaoJdbc(connectionUtil.connect());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {

        Statement stmt;
        String query = "SELECT id, name,default_price,currency_string, description FROM products WHERE id =" + id + ";";
        try (Connection con = dataSource.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int dbID = rs.getInt("id");
                String name = rs.getString("name");
                float defaultPrice = rs.getFloat("default_price");
                String currencyString = rs.getString("currency_string");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                int supplierId = rs.getInt("supplier_id");
                Product product = new Product(name, defaultPrice, currencyString, description, productCategoryDataStore.find(categoryId), supplierDataStore.find(supplierId));
                product.setId(dbID);
                return product;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Statement stmt;
        String query = "SELECT id, name,default_price,currency_string, description, category_id, supplier_id FROM products";
        try (Connection con = dataSource.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float defaultPrice = rs.getFloat("default_price");
                String currencyString = rs.getString("currency_string");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                int supplierId = rs.getInt("supplier_id");
                Product product = new Product(name, defaultPrice, currencyString, description, productCategoryDataStore.find(categoryId), supplierDataStore.find(supplierId));
                product.setId(id);
                products.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        Statement stmt;
        String query = "SELECT products.id, products.name,default_price,currency_string, products.description, category_id, supplier_id FROM products WHERE supplier_id =" + supplier.getId() + ";";
        try (Connection con = dataSource.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int yourId = rs.getInt("id");
                String name = rs.getString("name");
                float defaultPrice = rs.getFloat("default_price");
                String currencyString = rs.getString("currency_string");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                Product product = new Product(name, defaultPrice, currencyString, description, productCategoryDataStore.find(categoryId), supplierDataStore.find(supplier.getId()));
                product.setId(yourId);
                products.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();
        Statement stmt;
        String query = "SELECT products.id, products.name,default_price,currency_string, products.description, category_id, supplier_id FROM products  WHERE category_id =" + productCategory.getId() + ";";
        try (Connection con = dataSource.getConnection()) {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int itId = rs.getInt("id");
                String name = rs.getString("name");
                float defaultPrice = rs.getFloat("default_price");
                String currencyString = rs.getString("currency_string");
                String description = rs.getString("description");
                int supplierId = rs.getInt("supplier_id");
                Product product = new Product(name, defaultPrice, currencyString, description, productCategoryDataStore.find(productCategory.getId()), supplierDataStore.find(supplierId));
                product.setId(itId);
                products.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
