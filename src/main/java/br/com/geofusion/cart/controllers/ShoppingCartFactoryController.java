package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.ShoppingCart;
import br.com.geofusion.cart.services.ShoppingCartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/carts")
public class ShoppingCartFactoryController {
    @Autowired
    private ShoppingCartFactory cartFactory;

    @PostMapping("/{clientId}")
    public ResponseEntity<ShoppingCart> post(@PathVariable String clientId) {
        ShoppingCart newCart = cartFactory.create(clientId);
        return ResponseEntity.status(201).body(newCart);
    }

    @GetMapping("/ticket-amount")
    public ResponseEntity<BigDecimal> getTicketAmount() {
        return ResponseEntity.status(200).body(cartFactory.getAverageTicketAmount());
    }

    @PatchMapping("/invalidate/{clientId}")
    public ResponseEntity<Boolean> invalidateCart(@PathVariable String clientId){
        return ResponseEntity.status(200).body(cartFactory.invalidate(clientId));
    }

}
