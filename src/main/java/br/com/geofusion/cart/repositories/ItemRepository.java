package br.com.geofusion.cart.repositories;

import br.com.geofusion.cart.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //@Query("from Item i where i.product.code= ?1 and i.cart")
    //Optional<Item> findByCodeProduct(Long code);
}
