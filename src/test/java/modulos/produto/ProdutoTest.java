package modulos.produto;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTest {

    @Test
    @DisplayName("Validar os limites proibidos de valores do produto")
    public void testValidarLimitesProibidosValorProduto() {
        //Configurando os dados da API lojinha
        baseURI = "http://165.227.93.41";   //endereço da aplicação
        basePath = "/lojinha";             // caminho inicial da aplicação

        //Obter o token do usuário admin
        String token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"andersonrezendeptg\",\n" +
                        "  \"usuarioSenha\": \"anderson123\"\n" +
                        "}")
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");

        //Adicionando um produto novo e validar que o status code é 422
            given()
                    .contentType(ContentType.JSON)
                    .header("token", token)
                    .body("{\n" +
                            "  \"produtoNome\": \"PlayStation 5\",\n" +
                            "  \"produtoValor\": 0.00,\n" +
                            "  \"produtoCores\": [\n" +
                            "    \"preto\"\n" +
                            "  ],\n" +
                            "  \"produtoUrlMock\": \"string\",\n" +
                            "  \"componentes\": [\n" +
                            "    {\n" +
                            "      \"componenteNome\": \"Controle\",\n" +
                            "      \"componenteQuantidade\": 1\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}")
            .when()
                    .post("/v2/produtos")

            .then()
                    .assertThat()
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                        .statusCode(422);
    }

    @Test
    @DisplayName("Validar valor maior que sete mil")
    public void testValidarLimitesMaiorSeteMilProibidosValorProduto() {
        //Configurando os dados da API lojinha
        baseURI = "http://165.227.93.41";   //endereço da aplicação
        basePath = "/lojinha";             // caminho inicial da aplicação

        //Obter o token do usuário admin
        String token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"andersonrezendeptg\",\n" +
                        "  \"usuarioSenha\": \"anderson123\"\n" +
                        "}")
                .when()
                .post("/v2/login")
                .then()
                .extract()
                .path("data.token");

        //Adicionando um produto novo e validar que o status code é 422
        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .body("{\n" +
                        "  \"produtoNome\": \"PlayStation 5\",\n" +
                        "  \"produtoValor\": 7000.01,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"preto\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"string\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Controle\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .post("/v2/produtos")

                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
}
