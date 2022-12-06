package br.com.geofusion.cart.services;

import br.com.geofusion.cart.controllers.exception.NotFoundException;
import br.com.geofusion.cart.controllers.mappers.ProductMapper;
import br.com.geofusion.cart.dtos.ProductDto;
import br.com.geofusion.cart.entities.Product;
import br.com.geofusion.cart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;


    public void register(ProductDto newProduct){
        repository.save(mapper.dtoToEntity(newProduct));
    }

    public void update(ProductDto product, Long id){
        Product entity = repository.findById(id)
                                  .orElseThrow(() -> new NotFoundException("Product not found"));
        mapper.updateProductFromDto(product, entity);
        repository.save(entity);
    }

    public void remove(Long id){
        repository.deleteById(id);
    }

    public ProductDto searchById(Long id){
        Product entity = repository.findById(id)
                                   .orElseThrow(() -> new NotFoundException("Product not found"));
        return mapper.entityToDto(entity);
    }

    public List<ProductDto> fetchAll(){
        return repository.findAll()
                         .stream()
                         .map(product -> mapper.entityToDto(product))
                         .collect(Collectors.toList());
    }
}
