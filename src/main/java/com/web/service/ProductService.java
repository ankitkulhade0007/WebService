package com.web.service;

import com.web.Model.storage.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public  List<Product> getProductByCategory(String category){
        List<Product> products = getAllProduct();
        products = products.stream().filter(p-> p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
       System.out.println("found List "+ products);
        return products;

    }


    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();

        Product pro1 = new Product(1,"oil", 10,"grocerry");
        Product pro2 = new Product(2,"maggie", 100,"grocerry");
        Product pro3 = new Product(3,"Tea", 1000,"grocerry");
        Product pro4 = new Product(4,"Sofa", 10000,"furniture");
        Product pro5 = new Product(5,"Chair", 1000000,"furniture");
        products.add(pro1);
        products.add(pro2);
        products.add(pro3);
        products.add(pro4);
        products.add(pro5);
        return  products;
    }
}
