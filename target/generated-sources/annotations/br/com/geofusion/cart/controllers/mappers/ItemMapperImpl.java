package br.com.geofusion.cart.controllers.mappers;

import br.com.geofusion.cart.dtos.ItemDto;
import br.com.geofusion.cart.entities.Item;
import br.com.geofusion.cart.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-06T21:03:08-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public void updateProductFromDto(ItemDto dto, Item entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUnitPrice() != null ) {
            entity.setUnitPrice( dto.getUnitPrice() );
        }
        entity.setQuantity( dto.getQuantity() );
    }

    @Override
    public ItemDto entityToDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setCodeProduct( entityProductCode( entity ) );
        itemDto.setId( entity.getId() );
        itemDto.setUnitPrice( entity.getUnitPrice() );
        itemDto.setQuantity( entity.getQuantity() );

        return itemDto;
    }

    @Override
    public Item dtoToEntity(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        item.setProduct( itemDtoToProduct( dto ) );
        if ( dto.getId() != null ) {
            item.setId( dto.getId() );
        }
        item.setUnitPrice( dto.getUnitPrice() );
        item.setQuantity( dto.getQuantity() );

        return item;
    }

    private Long entityProductCode(Item item) {
        if ( item == null ) {
            return null;
        }
        Product product = item.getProduct();
        if ( product == null ) {
            return null;
        }
        Long code = product.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    protected Product itemDtoToProduct(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCode( itemDto.getCodeProduct() );

        return product;
    }
}
