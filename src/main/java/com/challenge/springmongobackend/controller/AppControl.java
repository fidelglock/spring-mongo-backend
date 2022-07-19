package com.challenge.springmongobackend.controller;

import com.challenge.springmongobackend.entity.DataRepository;
import com.challenge.springmongobackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AppControl {
    @Autowired
    DataRepository repo;


    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Spring Boot application connected to MongoDb Atlas..", HttpStatus.OK);
    }

    @GetMapping("/allProducts")
    public List<Product> allProducts() {
        System.out.println("*** Request received to view allProducts ***");
        return repo.findAll();
    }

    @GetMapping("/findProduct/{productId}")
    public Optional<Product> findProduct(@PathVariable String productId) {
        System.out.println("*** Request received to find one Product ***");
        return repo.findById(productId);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product p){
        System.out.println("*** Request received to add a Product ***");
        repo.save(p);
        return new ResponseEntity<>("Product has been added..", HttpStatus.OK);
    }

    @PutMapping("/modifyProduct/{productId}")
    public Product updateProduct(@RequestBody Product p) {
        System.out.println("*** Request received to modify a Product ***");
        if(repo.findById(p.getId())!=null) System.out.println("*PRODUCT EXISTS* - modifying values ..");
        else System.out.println(" **SOMETHING IS WRONG**");
        return repo.save(p);
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        System.out.println("*** Request received to delete a Product ***");
        try{
            Optional<Product> deleteProduct = null;
            deleteProduct = repo.findById(productId);
            if (deleteProduct!=null){
                repo.deleteById(productId);
                return new ResponseEntity<>("Product has been removed ..", HttpStatus.OK);
            }
            else return new ResponseEntity<>("Product not found ..", HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            System.out.println("***** CRITICAL EXCEPTION *****");
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Other issue see application logs..", HttpStatus.BAD_GATEWAY);
        }
    }
}
