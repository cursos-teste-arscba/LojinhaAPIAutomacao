package dataFactory;

import pojo.componentePojo;
import pojo.produtoPojo;

import java.util.ArrayList;
import java.util.List;

public class produtoDataFactory {
    public static produtoPojo criarProdutoComumComValorIgualA(double valor) {

        produtoPojo produto = new produtoPojo();
        produto.setProdutoNome("PlayStation 5");
        produto.setProdutoValor(0.00);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<componentePojo> componentes = new ArrayList<>();

        componentePojo componente = new componentePojo();
        componente.setComponenteNome("controle");
        componente.setComponenteQuantidade(1);

        componentes.add(componente);

        produto.setComponentes(componentes);

        return produto;
    }
}
