package br.edu.fatecpg.view;
import java.util.List;

public class ImprimeProdutos {

    public static void imprimeProdutos(List<String> produtos) {
        System.out.println("Produtos IMPERDÍVEIS e em promoção (exibidos em ordem descrescente): ");
        produtos.forEach(System.out::println);
    }
}
