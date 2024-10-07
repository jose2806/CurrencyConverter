import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionRecord {
    private String originCurrency;
    private String destinationCurrency;
    private double amount;
    private double exchangeRate;
    private double result;
    private String timestamp;

    public ConversionRecord(String originCurrency, String destinationCurrency, double amount, double exchangeRate, double result){
        this.originCurrency = originCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.result = result;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
