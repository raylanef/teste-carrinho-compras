package br.com.geofusion.cart;



import br.com.geofusion.cart.controllers.exception.NotFoundException;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */

@Getter
public class ShoppingCart {

    private String clientId;
    private List<Item> items;

    private boolean active;

    public ShoppingCart(String clientId) {
        this.clientId = clientId;
        this.items = new ArrayList<>();
        this.active = true;
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
    public void addItem(Product product,BigDecimal unitPrice, int quantity) {
        for (Item item: items) {
            if(item.getProduct().getCode().equals(product.getCode())){
                item.updateQuantity(quantity);
                return;
            }
        }
        Item newItem = new Item(product,unitPrice,quantity);
        items.add(newItem);
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(Product product) {
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
    public boolean removeItem(int itemIndex) {
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
    public BigDecimal getAmount() {
        return items.stream().map(Item::totalItemValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean existsCart(String clientId){
       return this.clientId.equals(clientId);
    }


    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems() {
        return items;
    }

    public void isActive(boolean active) {
        this.active = active;
    }

    public void updatePriceItem(Long codeProduct,BigDecimal unitPrice) {
        for (Item item: items) {
            if(item.getProduct().getCode().equals(codeProduct)){
                item.updateUnitPrice(unitPrice);
                return;
            }
        }
        throw new NotFoundException("item not found");
    }
}