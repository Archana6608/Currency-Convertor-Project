package com.core.exchange.repository;
import com.core.exchange.exception.ApiNetworkException;
import com.core.exchange.exception.CurrencyMappingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RemoteExchangeRateRepository implements ExchangeRateRepository {
    private static final String API_ENDPOINT = "https://open.er-api.com/v6/latest/";
    private final HttpClient httpClient;
    public RemoteExchangeRateRepository() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
    }
    @Override
    public double fetchRate(String base, String target) throws ApiNetworkException, CurrencyMappingException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_ENDPOINT + base)).timeout(Duration.ofSeconds(5)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiNetworkException("Server responded with error status: " + response.statusCode());
            }
            return extractRateFromJson(response.body(), target);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiNetworkException("API thread execution framework was aborted.", e);
        } catch (Exception e) {
            if (e instanceof CurrencyMappingException) throw (CurrencyMappingException) e;
            throw new ApiNetworkException("Infrastructure linkage fault during remote API sync processing.", e);
        }
    }
    private double extractRateFromJson(String json, String target) throws CurrencyMappingException {
        String regex = "\"" + target + "\"\\s*:\\s*([0-9.]+)";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        if (matcher.find()) { return Double.parseDouble(matcher.group(1)); }
        else { throw new CurrencyMappingException("Target token specification code '" + target + "' is unsupported."); }
    }
}