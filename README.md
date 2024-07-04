# Lojinha API Automação
Esse é um repositório que contém a automação de API Rest de um software denominado Lojinha, Os subtópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

## Tecnologias Utilizadas

- Java
  (https://www.java.com/pt-BR/download/)
- Junit
  (https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.7.1)
- RestAssured
  (https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.4.0)
- Maven
- (https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.4.0)

## Testes Automatizados
- Testes para validar as partições de equivalência relacionadas ao valor do produto na Lojinha, que estão vinculados diretamente a regra de negócio que diz que o valor do produto deve estar entre R$ 0,01 a 7.000,00.

## Notas Gerais

- Sempre utilizamos a anotação Before Each para capturar o token que será utilizado posteriormente nos métodos de teste
- Armazenamos os dados que são enviados para a API através do uso de classes POLO
- Criamos os dados inicias através do uso de classe Data Factory, para facilitar a criação e controle dos mesmos
- Foi utilizado o Junit 5, o que nos dá a possibilidade de usar a anotação DisplayName para dar descrições em portugueses para nossos testes