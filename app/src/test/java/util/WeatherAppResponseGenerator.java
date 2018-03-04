package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class WeatherAppResponseGenerator {
    private String basePath;
    private File file;

    public WeatherAppResponseGenerator(String pathUnderFixturesDirectory) {
        this.basePath = "src/test/resources/";
        this.file = new File(this.basePath, pathUnderFixturesDirectory);
    }

    public String readAll() throws IOException {
        InputStream in = new FileInputStream(file);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return out.toString();
    }
}
