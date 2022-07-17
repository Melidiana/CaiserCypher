package service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadService {

    public StringBuilder readFile(StringBuilder builder, File inputFile) {

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                builder.append(line);
                builder.append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder;
    }
}
