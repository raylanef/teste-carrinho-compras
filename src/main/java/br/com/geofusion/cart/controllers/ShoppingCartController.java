package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.entities.ShoppingCart;
import br.com.geofusion.cart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService service;


    @GetMapping
    public ResponseEntity<List<ShoppingCart>> get(){

        return ResponseEntity.status(200).body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ShoppingCart c){
        service.post(c);
        return ResponseEntity.status(201).build();
    }
}
