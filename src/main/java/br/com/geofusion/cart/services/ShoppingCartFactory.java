package br.com.geofusion.cart.services;


import br.com.geofusion.cart.ShoppingCart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Service
public class ShoppingCartFactory {
    private List<ShoppingCart> carts = new ArrayList<>();

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCart
     */
    public ShoppingCart create(String clientId) {
        Optional<ShoppingCart> cart = carts.stream()
                                           .filter(c -> c.existsCart(clientId))
                                           .findAny();

        if(cart.isPresent()) return cart.get();

        ShoppingCart newCart = new ShoppingCart(clientId);
        carts.add(newCart);
        return newCart;
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
        BigDecimal sum = carts.stream()
                              .map(ShoppingCart::getAmount)
                              .reduce(BigDecimal.ZERO, BigDecimal::add);

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
        Optional<ShoppingCart> cart = carts.stream()
                                           .filter(c -> c.existsCart(clientId))
                                           .findAny();

        cart.ifPresent(c -> c.isActive(false));
        return cart.isPresent();
    }

    /**
     * Verifica o número de carrinhos cadastrados
     *
     * @return int
     */
    public int size(){
        return carts.size();
    }

}
