package br.com.geofusion.cart.controllers.mappers;

import br.com.geofusion.cart.dtos.ProductDto;
import br.com.geofusion.cart.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-04T23:38:39-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2.1 (Homebrew)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public void updateProductFromDto(ProductDto dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCode() != null ) {
            entity.setCode( dto.getCode() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }

    @Override
    public ProductDto entityToDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCode( entity.getCode() );
        productDto.setDescription( entity.getDescription() );

        return productDto;
    }

    @Override
    public Product dtoToEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCode( dto.getCode() );
        product.setDescription( dto.getDescription() );

        return product;
    }
}
