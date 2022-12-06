package br.com.geofusion.cart.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long codeProduct;
    private Long id;
    private BigDecimal unitPrice;
    private int quantity;
    private Long idCart;
}
