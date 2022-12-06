package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.dtos.ProductDto;
import br.com.geofusion.cart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = service.fetchAll();

        return products.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        return  ResponseEntity.status(200).body(service.searchById(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProductDto newProduct){
        service.register(newProduct);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        service.remove(id);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductDto productDto){
        service.update(productDto, id);
        return ResponseEntity.status(200).build();
    }



}
