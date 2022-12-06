package br.com.geofusion.cart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    private String clientId;

    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();

    public ShoppingCart(String clientId) {
        this.clientId = clientId;
    }
}
