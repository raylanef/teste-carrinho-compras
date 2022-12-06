package br.com.geofusion.cart.controllers.mappers;

import br.com.geofusion.cart.dtos.ItemDto;
import br.com.geofusion.cart.entities.Item;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ItemDto dto, @MappingTarget Item entity);

    @Mapping(target = "codeProduct", source = "product.code")
    ItemDto entityToDto(Item entity);

    @Mapping(target = "product.code", source = "codeProduct")
    Item dtoToEntity(ItemDto dto);
}
