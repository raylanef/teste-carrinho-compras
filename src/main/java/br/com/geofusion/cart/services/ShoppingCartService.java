package br.com.geofusion.cart.services;

import br.com.geofusion.cart.Item;
import br.com.geofusion.cart.Product;
import br.com.geofusion.cart.ShoppingCart;
import br.com.geofusion.cart.controllers.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private List<ShoppingCart> carts = new ArrayList<>(List.of(
            new ShoppingCart("client2"),
            new ShoppingCart("client3")
    ));

    public void addItem(String clientId, Item item){
        ShoppingCart cart = cartExists(carts, clientId);
        cart.addItem(item.getProduct(), item.getUnitPrice(),item.getQuantity());
    }

    public void removeItem(String clientId, Long codeProduct){
        ShoppingCart cart = cartExists(carts, clientId);
        Product product = new Product(codeProduct);
        cart.removeItem(product);
    }

    public BigDecimal amount(String clientId){
        ShoppingCart cart = cartExists(carts, clientId);
        return cart.getAmount();
    }

    public Collection<Item> getItems(String clientId){
        ShoppingCart cart = cartExists(carts, clientId);
        return cart.getItems();
    }

    private ShoppingCart cartExists(List<ShoppingCart> carts, String clientId){
        Optional<ShoppingCart> cart = carts.stream().filter(c -> c.existsCart(clientId)).findAny();
        if(!cart.isPresent()) throw new NotFoundException("cart not found");
        return cart.get();
    }

}
