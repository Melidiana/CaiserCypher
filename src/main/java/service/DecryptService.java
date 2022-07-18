package service;

import util.Alphabet;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DecryptService {

    private final WriteService writeService;
    private final ReadService readService;

    public DecryptService(WriteService writeService, ReadService readService) {
        this.writeService = writeService;
        this.readService = readService;
    }

    public StringBuilder decryptFile(String pathIn, String pathOut, int key) {
        StringBuilder returnValue = new StringBuilder();
        File inputFile = new File(pathIn);
        File outputFile = new File(pathOut);
        StringBuilder builder = readService.readFile(new StringBuilder(), inputFile);
        char[] chars = builder.toString().toCharArray();
        for (char ch : chars) {
            returnValue.append(decryptChar(ch, key));
        }
        try {
            writeService.writeToFile(returnValue, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    private char decryptChar(char symbol, int key) {
        if (symbol == '\n') {
            return '\n';
        }
        if (Alphabet.encryptMap.get(Character.toLowerCase(symbol)) != null) {
            if (Character.isUpperCase(symbol)) {
                int position = Alphabet.encryptMap.get(Character.toLowerCase(symbol));
                int shift = (position + (Alphabet.encryptMap.size() - key)) % Alphabet.encryptMap.size();
                return Character.toUpperCase(Alphabet.decryptMap.get(shift));
            }
            int position = Alphabet.encryptMap.get(symbol);
            int shift = (position + (Alphabet.encryptMap.size() - key)) % Alphabet.encryptMap.size();
            return Alphabet.decryptMap.get(shift);
        }
        throw new RuntimeException("Символ не найден");
    }
}


