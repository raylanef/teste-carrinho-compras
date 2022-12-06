package br.com.geofusion.cart.services;


import br.com.geofusion.cart.controllers.mappers.ItemMapper;
import br.com.geofusion.cart.entities.Item;
import br.com.geofusion.cart.entities.Product;
import br.com.geofusion.cart.entities.ShoppingCart;
import br.com.geofusion.cart.repositories.ItemRepository;
import br.com.geofusion.cart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper mapper;


    public List<ShoppingCart> getAll(){
        return shoppingCartRepository.findAll();
    }

    public void post(ShoppingCart c){
        shoppingCartRepository.save(c);
    }



    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public void addItem(Product product, BigDecimal unitPrice, int quantity, String clientId) {

        var item = shoppingCartRepository
                .findById(clientId)
                .orElseThrow()
                .getItems().stream()
                .filter(i -> i.getProduct()
                        .getCode()
                        .equals(product.getCode()))
                .findAny();


        if(item.isPresent()) {
            var i = item.get();
            i.updateQuantity(quantity);
            itemRepository.save(i);
            return;
        }

        Item newItem = new Item(product, unitPrice, quantity, clientId);
        itemRepository.save(newItem);

    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(Product product,String clientId ) {
        List<Item> items = itemRepository.findAll();
        return items.removeIf(item -> item.getProduct().getCode().equals(product.getCode()));
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na
     * coleção, em que zero representa o primeiro item.
     *
     * @param itemIndex
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(int itemIndex,String clientId) {
        List<Item> items = (List<Item>) this.getItems(clientId);
        boolean validIndex = itemIndex > 0 && itemIndex < items.size();
        if(validIndex) {
            items.remove(itemIndex);
        }
        return validIndex ;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount(String clientId) {
        Collection<Item> items = getItems(clientId);
        return items.stream().map(Item::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems(String clientId) {
        return shoppingCartRepository
                .findById(clientId)
                .orElseThrow()
                .getItems();
    }


}