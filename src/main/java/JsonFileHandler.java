

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileHandler {
    private static final String FILE = "conversiones.json";
    private static final String FILE2 = "filtraciones.json";

    public void saveConversionToFile(ConversionRecord conversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(FILE,true);
        writer.write(gson.toJson(conversion) + ",\n");
        writer.close();
    }

    public void saveFilterToFile(FilterRecord conversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(FILE2,true);
        writer.write(gson.toJson(conversion) + ",\n");
        writer.close();
    }
}
