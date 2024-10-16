package br.edu.fatecpg;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import br.edu.fatecpg.view.ImprimeProdutos;
import br.edu.fatecpg.service.ConsomeAPI;

public class Main {
    public static void main(String[] args) {

        // Solicitando ao usuário o valor máximo para o filtro
        Scanner scanner = new Scanner(System.in);
        System.out.println("*#*#*#*#*#*#*#");
        System.out.print("Informe o valor máximo que deseja pagar: R$ ");
        double precoMaximo = scanner.nextDouble();

        try {
            // Coleta os produtos com preço abaixo do valor informado
            List<String> produtos = ConsomeAPI.coletarProdutos(precoMaximo);

            // Exibe os produtos imperdíveis usando a view
            ImprimeProdutos.imprimeProdutos(produtos);
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao coletar ou exibir produtos: " + e.getMessage());
        }
    }
}
