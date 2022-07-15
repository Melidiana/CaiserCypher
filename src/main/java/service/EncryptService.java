package service;

import util.Alphabet;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EncryptService {

    public StringBuilder encryptFile(String pathIn, String pathOut, int key) {
        File inputFile = new File(pathIn);
        File outputFile = new File(pathOut);
        StringBuilder builder = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                char[] chars = line.toCharArray();
                for (char ch : chars) {
                    builder.append(encryptChar(ch, key));
                }
                builder.append('\n');
            }
            writeToFile(builder, outputFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder;
    }

    private void writeToFile(StringBuilder builder, File outputFile) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
             OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(outputStream)) {

            writer.write(builder.toString());
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
     }


    private char encryptChar(char symbol, int key) {
        if (symbol == '\n') {
            return '\n';
        }
        if (Alphabet.encryptMap.get(Character.toLowerCase(symbol)) != null) {
            if (Character.isUpperCase(symbol)) {
                int position = Alphabet.encryptMap.get(Character.toLowerCase(symbol));
                int shift = (position + key) % Alphabet.encryptMap.size();
                return Character.toUpperCase(Alphabet.decryptMap.get(shift));
            }
            int position = Alphabet.encryptMap.get(symbol);
            int shift = (position + key) % Alphabet.encryptMap.size();
            return Alphabet.decryptMap.get(shift);
        }
        return symbol;
    }
}
