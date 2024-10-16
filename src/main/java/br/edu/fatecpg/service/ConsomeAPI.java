package br.edu.fatecpg.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConsomeAPI {

    public static List<String> coletarProdutos(double precoMaximo) throws IOException, InterruptedException {
        // URL da API
        String url = "https://api.escuelajs.co/api/v1/products/";

        // criando instância de HttpClient , HttpRequest e HttpResponse
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // verificando statusCode ( 200 é OK!! )
        if (response.statusCode() == 200) {
            String result = response.body();

            // Transformando a String JSON em um array de objetos JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode produtosArray = mapper.readTree(result);

            // Usando Stream e função lambda para coletar os produtos filtrados
            return StreamSupport.stream(produtosArray.spliterator(), false) // Converte JsonNode para stream
                    .filter(campo -> campo.get("price").asDouble() < precoMaximo) // Filtra produtos pelo preço informado
                    .sorted((p1, p2) -> Double.compare(p2.get("price").asDouble(), p1.get("price").asDouble())) // Ordena em ordem decrescente
                    .map(campo ->" R$ " + campo.get("price").asDouble() + "     " + campo.get("title").asText().toUpperCase()) // Extrai o título do produto e coloca em maiúsculas

                    .collect(Collectors.toList()); // coleta em uma List

        } else {
            throw new IOException( "erro ao acessar a API. Status code: " + response.statusCode());
        }
    }
}
