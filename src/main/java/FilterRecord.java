import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FilterRecord {
    private String originCurrency;
    private Map<String,Double> rates;
    private String timestamp;

    public FilterRecord(String originCurrency, Map<String,Double> rates){
        this.originCurrency = originCurrency;
        this.rates = rates;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
