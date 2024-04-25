import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private String monedaBase;
    private String monedaAObtener;

    public Moneda consultar(int opcion, Double monto) {
        establecerMonedas(opcion);
        URI url = URI.create("https://v6.exchangerate-api.com/v6/636698ed0979b2c8fd4c6b2a/pair/" + monedaBase + "/" + monedaAObtener + "/" + monto + "/");
        String responseBody = realizarSolicitud(url);
        return new Gson().fromJson(responseBody, Moneda.class);
    }

    private void establecerMonedas(int opcion) {
        switch (opcion) {
            case 1 -> {
                monedaBase = "USD";
                monedaAObtener = "ARS";
            }
            case 2 -> {
                monedaBase = "ARS";
                monedaAObtener = "USD";
            }
            case 3 -> {
                monedaBase = "USD";
                monedaAObtener = "BRL";
            }
            case 4 -> {
                monedaBase = "BRL";
                monedaAObtener = "USD";
            }
            case 5 -> {
                monedaBase = "USD";
                monedaAObtener = "COP";
            }
            case 6 -> {
                monedaBase = "COP";
                monedaAObtener = "USD";
            }
            case 7 -> {
            }
            default -> throw new IllegalArgumentException("Opción no válida: " + opcion);
        }
    }

    private String realizarSolicitud(URI url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al realizar la solicitud: " + e.getMessage());
        }
    }
}
