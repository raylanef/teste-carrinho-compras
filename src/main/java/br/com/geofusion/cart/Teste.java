package br.com.geofusion.cart;

import br.com.geofusion.cart.services.ShoppingCartFactory;

import java.math.BigDecimal;

public class Teste {
    public static void main(String[] args) {
        ShoppingCart carrinho = new ShoppingCart("123");

        Product produto1 = new Product(2123L, "esmalte");
        Product produto2 = new Product(2124L, "acetona");
        Product produto3 = new Product(2125L, "algodão");


        carrinho.addItem(produto1, new BigDecimal("3.0"), 2);
        carrinho.addItem(produto1, new BigDecimal("3.0"), 1);

        System.out.println(">>>>>>>>>>>>>>>>>> ITENS <<<<<<<<<<<<<<<<<<<");
        System.out.println(carrinho.getItems());
        System.out.println(">>>>>>>>>>>>>>>>>> VALOR TOTAL <<<<<<<<<<<<<<<<<<<");
        System.out.println(carrinho.getAmount());


        ShoppingCartFactory carts = new ShoppingCartFactory();


        System.out.println(">>>>>>>>>>>>>>>>>> shopping <<<<<<<<<<<<<<<<<<<");
        ShoppingCart carrinho2 = carts.create("123");
        ShoppingCart carrinho3 = carts.create("123");
        ShoppingCart carrinho4 = carts.create("345");
        carrinho2.addItem(produto1, new BigDecimal("3.0"), 2);
        carrinho2.addItem(produto1, new BigDecimal("3.0"), 1);
        carrinho4.addItem(produto2, new BigDecimal("1.8"), 2);
        carrinho4.addItem(produto2, new BigDecimal("1.8"), 1);
        System.out.println(carts.create("123"));
        System.out.println( "Tamanho carrinho: " + carts.size());
        System.out.println(carts.getAverageTicketAmount());
    }
}
