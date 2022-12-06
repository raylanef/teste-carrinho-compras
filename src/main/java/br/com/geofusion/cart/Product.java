package br.com.geofusion.cart;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {

    @NotNull
    private Long code;

    @NotBlank
    private String description;

    public Product(Long code) {
        this.code = code;
    }


}