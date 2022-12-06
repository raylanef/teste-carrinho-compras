package br.com.geofusion.cart.controllers.mappers;

import br.com.geofusion.cart.dtos.ProductDto;
import br.com.geofusion.cart.entities.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDto dto, @MappingTarget Product entity);
    ProductDto entityToDto(Product entity);
    Product dtoToEntity(ProductDto dto);
}
