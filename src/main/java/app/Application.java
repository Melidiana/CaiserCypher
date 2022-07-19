package app;

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
        System.out.println("Добро пожаловать в программу шифрования Цезарь." + "\n" + "Вам необходимо выбрать цифру:");
        System.out.println("""
                1 - для шифрования файла
                2 - для расшифрования файла с ключом
                3 - для расшифрования методом brute force""");
        int number = Integer.parseInt(reader.readLine());
        System.out.println("Введите путь к файлу:");
        String inputPath = reader.readLine();
        File inFile = new File(inputPath);
        String newPath;
        File newFile;
        if (!inFile.exists()) {
            do {
                System.out.println("Введите правильный путь:");
                newPath = reader.readLine();
                newFile = new File(newPath);
            } while (!(newFile.exists()));
            inputPath = newPath;
        }
        System.out.println("Введите путь для записи файла:");
        String outputPath = reader.readLine();
        String newOutPath;
        if (!Paths.get(outputPath).isAbsolute()) {
            do {
                System.out.println("Введите правильный путь:");
                newOutPath = reader.readLine();
            } while (!Paths.get(newOutPath).isAbsolute());
            outputPath = newOutPath;
        }
        if (number == 3) {
            System.out.println("Файл расшифрован с ключом:" + bruteForceService.bruteForceDecrypt(inputPath, outputPath));
            System.exit(1);
        }
        int key;
        do {
            System.out.println("Введите ключ в диапазоне от 1 до " + Alphabet.encryptMap.size());
            key = Integer.parseInt(reader.readLine());
        } while (key > Alphabet.encryptMap.size() || key <= 0);

        if (number == 1) {
            encryptService.encryptFile(inputPath, outputPath, key);
        }
        if (number == 2) {
            decryptService.decryptFile(inputPath, outputPath, key);
        }
    }
}