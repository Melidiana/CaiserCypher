package app;

import service.EncryptService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EncryptService encryptService = new EncryptService();
        System.out.println("Добро пожаловать в программу шифрования Цезарь");
        System.out.println("Введите путь к файлу:");
        String inputPath = reader.readLine();
        System.out.println("Введите путь к зашифрованному файлу:");
        String outputPath = reader.readLine();
        System.out.println("Введите ключ для шифрования");
        int key = Integer.parseInt(reader.readLine());
        System.out.println(encryptService.encryptFile(inputPath, outputPath, key));
    }
}