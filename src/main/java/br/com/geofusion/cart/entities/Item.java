package br.com.geofusion.cart.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Entity @Getter @Setter @NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue()
    private long id;
    @OneToOne
    private Product product;
    private BigDecimal unitPrice = new BigDecimal("0");
    private int quantity;

    @ManyToOne
    private ShoppingCart cart;


    public Item(Product product, BigDecimal unitPrice, int quantity, String clientId) {
        this.product = product;
        this.unitPrice = new BigDecimal(String.valueOf(unitPrice));
        this.quantity = quantity;
        this.cart = new ShoppingCart(clientId);
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
       return  unitPrice.multiply(new BigDecimal(String.valueOf(quantity)));
    }

    public void updateQuantity(int quantity){
         this.quantity += quantity;
    }


}

