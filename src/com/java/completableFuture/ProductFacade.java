package com.java.completableFuture;

import java.util.concurrent.CompletableFuture;

public class ProductFacade {
    ProductService productService = new ProductService();
    ProductPriceService productPriceService = new ProductPriceService();

    public static void main(String[] args) {
        new ProductFacade().process();
    }

    void process() {
        CompletableFuture<Product> product = productService.getProduct(1);
        CompletableFuture<ProductPrice> productPrice = productPriceService.getProductPrice(1);
        System.out.println("Created all CFs");


        CompletableFuture<Void> allProcess = CompletableFuture.allOf(product, productPrice);
        System.out.println("CompletableFuture.allOf");

//        allProcess.join(); // blocking; Waits for all to finish
        System.out.println("allProcess join");

        System.out.println(product.join()); // blocking; waits for itself to finish
        System.out.println(productPrice.join()); // blocking; waits for itself to finish

        // total time taken is equal to slowest process.
    }

}

class ProductService {
    CompletableFuture<Product> getProduct(int productId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {

            }
            return new Product(1, "Product 1");
        });
    }
}

class ProductPriceService {
    CompletableFuture<ProductPrice> getProductPrice(int productId) {
        return CompletableFuture.supplyAsync(() -> new ProductPrice(1, 1, 100.0));
    }
}

class Product {
    int id;
    String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class ProductPrice {
    int id;
    int productId;
    double price;

    public ProductPrice(int id, int productId, double price) {
        this.id = id;
        this.productId = productId;
        this.price = price;
    }
}
