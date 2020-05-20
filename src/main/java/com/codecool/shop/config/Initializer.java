package com.codecool.shop.config;

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
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Shitty overpriced electronical stuff");
        supplierDataStore.add(apple);
        Supplier samsung = new Supplier("Samsung", "Samsung is great");
        supplierDataStore.add(apple);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Apple iPad Pro", 899.99f, "USD", "11-Inch edge-to-edge Liquid Retina display with Promotion, true Tone, and wide Color\n" +
                "A12X Bionic chip with Neural Engine\n" +
                "Face ID for secure authentication and Apple Pay\n" +
                "12MP back camera, 7MP True Depth front camera", tablet, apple));
        productDataStore.add(new Product("Samsung Galaxy Tab 4", 249.99f, "USD", "Android 4.4 Kit Kat OS, 1.2 GHz quad-core Qualcomm processor ; 16 GB Flash Memory, 1.5 GB RAM Memory and battery life is up to 10 hours", tablet, apple));
        productDataStore.add(new Product("Samsung Galaxy Tab A", 243.88f, "USD", "Big sound for big entertainment. The perfect complement to a wide, immersive picture, Dolby Atmos Surround sound fills the room with cinematic clarity.", tablet, apple));
    }


}
