package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws Exception {
        String urlApi = "yourAPIkey";

        URI uri = new URI(urlApi);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlApi)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        //System.out.println("Resposta: " + json);
        String[] movies = json.split("\\},\\{");
        for(String movie : movies) {
            //System.out.println("Filme: " + movie);
            int startIndexTile = movie.indexOf("\"original_title\":")+ "\"original_title\":".length();
            int endIndexTitle = movie.indexOf(",", startIndexTile);
            String titleValue = movie.substring(startIndexTile, endIndexTitle);
            System.out.println("Título do Filme: "+titleValue);

            int startIndexPic = movie.indexOf("\"backdrop_path\":") + "\"backdrop_path\":".length();
            int endIndexPic = movie.indexOf(",", startIndexPic);
            String urlPicture = movie.substring(startIndexPic, endIndexPic);
            System.out.println("Imagem do filme: " + urlPicture);

            int startIndexId = movie.indexOf("\"id\":") + "\"id\":".length();
            int endIndexId = movie.indexOf(",", startIndexId);
            String idValue = movie.substring(startIndexId, endIndexId);
            System.out.println("Id do filme: " + idValue);

            int startIndexSip = movie.indexOf("\"overview\":") + "\"overview\":".length();
            int endIndexSip = movie.indexOf(",", startIndexSip);
            String sip = movie.substring(startIndexSip, endIndexSip);
            System.out.println("Sinopse: " + sip);
        }
    }
}
