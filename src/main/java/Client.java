
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Client {

    public String consultExchangeRate(String origin, String destination){
        URI address = URI.create("https://v6.exchangerate-api.com/v6/f1e6351857bda73294634c54/pair/" + origin + "/" + destination);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(address).build();

        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            if(jsonResponse.has("conversion_rate")){
                return String.valueOf(jsonResponse.get("conversion_rate"));
            } else {
                return "No encotre el tipo de cambio";
            }
        } catch (Exception e) {
            return "Error al consultar el tipo de cambio: " + e.getMessage();
        }
    }

    public Map<String,Double> filterRates(String currency) throws Exception {
        URI address = URI.create("https://v6.exchangerate-api.com/v6/f1e6351857bda73294634c54/latest/" + currency);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(address).build();
        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);

            if(jsonResponse.has("conversion_rates")){
                JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
                Map<String,Double> ratesMap = new Gson().fromJson(conversionRates,new TypeToken<Map<String, Double>>() {}.getType());
                return ratesMap;
            } else {
                throw new RuntimeException("Error al consultar el tipo de cambio");
            }
        } catch (Exception e) {
            throw new Exception("Error al consultar el tipo de cambio: " + e.getMessage());
        }
    }
}
