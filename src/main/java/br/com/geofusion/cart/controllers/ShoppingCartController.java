package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.Item;
import br.com.geofusion.cart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping("/shopping-cart")

public class ShoppingCartController {
    @Autowired
    ShoppingCartService service;

    @PostMapping("/{clientId}")
    public ResponseEntity<?> addItemInCart(@PathVariable String clientId, @RequestBody @Valid Item item){
        service.addItem(clientId, item);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/amount/{clientId}")
    public ResponseEntity<BigDecimal> getAmountCart(@PathVariable String clientId){
        return ResponseEntity.status(200).body(service.amount(clientId));
    }

    @GetMapping("/items/{clientId}")
    public ResponseEntity<Collection<Item>> getItemsFromCart(@PathVariable String clientId){
        return ResponseEntity.status(200).body(service.getItems(clientId));
    }

    @GetMapping("/client/{clientId}/product/{codeProduct}")
    public ResponseEntity<Item> getItem(@PathVariable Long codeProduct, @PathVariable String clientId){
        return ResponseEntity.status(200).body(service.getItem(clientId, codeProduct));
    }

    @DeleteMapping("/client/{clientId}/product/{codeProduct}")
    public ResponseEntity<?> deleteItem(@PathVariable Long codeProduct, @PathVariable String clientId){
        service.removeItem(clientId, codeProduct);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/client/{clientId}")
    public ResponseEntity<?> updateItem( @PathVariable String clientId, @RequestBody Item item){
        service.updatePriceItem(clientId, item);
        return ResponseEntity.status(200).build();
    }
}
