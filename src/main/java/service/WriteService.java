package service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteService {

     void writeToFile(StringBuilder builder, File outputFile) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
             OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(outputStream)) {

            writer.write(builder.toString());
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
