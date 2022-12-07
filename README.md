## GEOFUSION SHOPPING CART 
 Case para avaliação Dev JR.

 O projeto conta com a branch principal (master) que contém resolução em memória, como também a branch (com-banco) que possui banco de dados relacional H2.
 
## Decisões
 Parte do que foi pedido, sobre atualização de preços e manipulação de items não ficou totalmente claro para mim. Então decidi adicionar  um identificador([que é passadao como path parameter](https://github.com/raylanef/teste-carrinho-compras/blob/master/src/main/java/br/com/geofusion/cart/controllers/ShoppingCartController.java#L20)) para cada carrinho, que é o ID do cliente, e o items só podem ser adicionados se existir um carrinho para tal cliente.


## Requisitos
* Java 11
* Maven 3.8+
  

## Executar:

Para executar apenas os testes, utilize o seguinte comando:
```
mvn test
```


Execução do projeto:

Primeiramente, build o projeto com  o seguinte comando:

``` 
mvn clean package
```

Então, execute:

``` 
java -jar target/cart-1.0-SNAPSHOT.jar
```

A aplicação estará disponível em : http://localhost:8080/

E a documentação da API, disponível em : http://localhost:8080/swagger-ui/index.html#/

