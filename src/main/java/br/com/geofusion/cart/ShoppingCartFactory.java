package br.com.geofusion.cart;



import br.com.geofusion.cart.entities.Item;
import br.com.geofusion.cart.entities.ShoppingCart;
import br.com.geofusion.cart.repositories.ItemRepository;
import br.com.geofusion.cart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Service
public class ShoppingCartFactory {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ItemRepository itemRepository;

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCartService
     */
    public ShoppingCart create(String clientId) {
        return shoppingCartRepository.findById(clientId).orElse(new ShoppingCart(clientId));
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketAmount() {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        List<Item> items = itemRepository.findAll();
        BigDecimal sum = items.stream().map(Item::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(carts.size()),2, RoundingMode.HALF_DOWN);
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param clientId
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(String clientId) {
        boolean cartExists = shoppingCartRepository.existsById(clientId);
        if(!cartExists) return false;
        shoppingCartRepository.deleteById(clientId);
        return true;
    }


}
