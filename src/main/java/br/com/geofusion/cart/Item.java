package br.com.geofusion.cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */

@Getter @ToString @NoArgsConstructor
public class Item {
    @NotNull
    private Product product;

    @NotNull
    private BigDecimal unitPrice;

    private int quantity;

    public Item(Product product, BigDecimal unitPrice, int quantity) {
        this.product = product;
        this.unitPrice = new BigDecimal(String.valueOf(unitPrice));
        this.quantity = quantity;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal totalItemValue() {
       return  unitPrice.multiply(new BigDecimal(String.valueOf(quantity)));
    }

    public void updateQuantity(int quantity){
         this.quantity += quantity;
    }

}

