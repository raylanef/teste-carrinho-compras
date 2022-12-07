## GEOFUSION SHOPPING CART 
 Case para avaliação Dev JR.

 O projeto conta com a branch principal (master) que contém resolução em memória, como também a branch (com-banco) que possui banco de dados relacional H2.


## Requisitos
* Java 11
* Maven 3.8+
  

## Executar:

Execução projeto:

Primeiramente, build o projeto com  o seguinte comando:

``` 
mvn clean package
```

Então, execute:

``` 
java -jar target/cart-1.0-SNAPSHOT.jar
```

Para executar apenas os testes, utilize o seguinte comando:
```
mvn test
```