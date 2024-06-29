package modulos.produto;

import dataFactory.produtoDataFactory;
import dataFactory.usuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.UsuarioPojo;
import pojo.componentePojo;
import pojo.produtoPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTestRefatorado {
    private String token;

    @BeforeEach
    public void beforeEach() {
        //Configurando os dados da API lojinha
        baseURI = "http://165.227.93.41";   //endereço da aplicação
        basePath = "/lojinha";             // caminho inicial da aplicação

        //Obter o token do usuário admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(usuarioDataFactory.criaUsuarioComumComSenhaA("anderson123"))
            .when()
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual 0.00 não é permitido")
    public void testValidarLimitesProibidosValorProduto() {


        //Adicionando um produto novo e validar que o status code é 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(produtoDataFactory.criarProdutoComumComValorIgualA(0.00))
            .when()
                .post("/v2/produtos")

            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto igual 7000.01 não é permitido")
    public void testValidarLimitesMaiorSeteMilProibidosValorProduto() {

        //Adicionando um produto novo e validar que o status code é 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(produtoDataFactory.criarProdutoComumComValorIgualA(7000.01))
                .when()
                .post("/v2/produtos")

                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
}

