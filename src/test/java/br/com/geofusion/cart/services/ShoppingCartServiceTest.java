package br.com.geofusion.cart.services;

import br.com.geofusion.cart.Item;
import br.com.geofusion.cart.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartService.class)
public class ShoppingCartServiceTest{
    @Autowired
    ShoppingCartService service;


    @Test
    public void addAndGetItem(){
        String clientId = "client2";
        Product productTest = new Product(9876L, "product test");
        Item itemTest = new Item(productTest,new BigDecimal(String.valueOf(12.0)),2);

        service.addItem(clientId, itemTest);
        Item response = service.getItem(clientId,9876L);

        assertEquals(itemTest,response);
    }

    @Test
    public void getItems(){
        String clientId = "client2";

        Product productTest1 = new Product(9876L, "product test");
        Product productTest2 = new Product(9877L, "product test 2");

        Item itemTest1 = new Item(productTest1,new BigDecimal(String.valueOf(12.0)),2);
        Item itemTest2 = new Item(productTest2,new BigDecimal(String.valueOf(24.0)),3);
        Item itemTest1Copy = new Item(productTest1,new BigDecimal(String.valueOf(12.0)),1);

        service.addItem(clientId, itemTest1);
        service.addItem(clientId, itemTest2);
        service.addItem(clientId, itemTest1Copy);

        Collection<Item> response = service.getItems(clientId);

        assertEquals(2, response.size());
    }


}
