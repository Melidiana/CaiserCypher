package app;

import enums.Command;
import service.*;
import util.Alphabet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;


public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ReadService readService = new ReadService();
        WriteService writeService = new WriteService();
        BruteForceService bruteForceService = new BruteForceService(new DecryptService(writeService, readService));
        EncryptService encryptService = new EncryptService(writeService, readService);
        DecryptService decryptService = new DecryptService(writeService, readService);
        System.out.println("Введите команду: (пример: ENCRYPT, DECRYPT, BRUTE_FORCE, EXIT)");
        System.out.println("    ENCRYPT (команда для шифрования файла)");
        System.out.println("    DECRYPT (команда для расшифрования файла с помощью ключа)");
        System.out.println("    BRUTE_FORCE (команда для расшифрования файла методом brute force)");
        System.out.println("    EXIT (выход из программы)");
        System.out.print("> ");
        Command command = Command.valueOf(reader.readLine());
        switch (command) {
            case ENCRYPT, DECRYPT -> {
                System.out.println("Введите путь к файлу:");
                String inputPath = reader.readLine();
                File inFile = new File(inputPath);
                if (!inFile.exists()) {
                    do {
                        System.out.println("Введите правильный путь:");
                        inputPath = reader.readLine();
                        inFile = new File(inputPath);
                    } while (!(inFile.exists()));
                }
                System.out.println("Введите путь для записи файла:");
                String outputPath = reader.readLine();
                if (!Paths.get(outputPath).isAbsolute()) {
                    do {
                        System.out.println("Введите правильный путь:");
                        outputPath = reader.readLine();
                    } while (!Paths.get(outputPath).isAbsolute());
                }
                int key;
                do {
                    System.out.println("Введите ключ в диапазоне от 1 до " + (Alphabet.encryptMap.size() - 1));
                    key = Integer.parseInt(reader.readLine());
                } while (key > Alphabet.encryptMap.size() || key <= 0);

                switch (command) {
                    case ENCRYPT -> {
                        encryptService.encryptFile(inputPath, outputPath, key);
                        System.out.println("Файл успешно зашифрован");
                    }
                    case DECRYPT -> {
                        decryptService.decryptFile(inputPath, outputPath, key);
                        System.out.println("Файл успешно расшифрован");
                    }
                }
            }
            case BRUTE_FORCE -> {
                System.out.println("Введите путь к файлу:");
                String inputPath = reader.readLine();
                File inFile = new File(inputPath);
                if (!inFile.exists()) {
                    do {
                        System.out.println("Введите правильный путь:");
                        inputPath = reader.readLine();
                        inFile = new File(inputPath);
                    } while (!(inFile.exists()));
                }
                System.out.println("Введите путь для записи файла:");
                String outputPath = reader.readLine();
                if (!Paths.get(outputPath).isAbsolute()) {
                    do {
                        System.out.println("Введите правильный путь:");
                        outputPath = reader.readLine();
                    } while (!Paths.get(outputPath).isAbsolute());
                }
                int key = bruteForceService.bruteForceDecrypt(inputPath, outputPath);
                System.out.println("Файл успешно расшифрован с ключом: " + key);
            }
            case EXIT -> System.exit(0);
        }
    }
}