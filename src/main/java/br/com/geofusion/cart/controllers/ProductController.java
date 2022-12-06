package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>(List.of(
            new Product(1234L, "Book"),
            new Product(1235L, "Notebook"),
            new Product(1236L, "Magazine")
    ));

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody @Valid Product newProduct) {
        products.add(newProduct);
        return ResponseEntity.status(201).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        if (products.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(products);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<?> delete(@PathVariable int index) {
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{index}")
    public ResponseEntity<Product> put(@PathVariable int index,
                                       @RequestBody @Valid Product product) {
        if (index >= 0 && index < products.size()) {
            products.set(index, product);
            return ResponseEntity.status(200).body(product);
        }
        return ResponseEntity.status(404).build();
    }
}
