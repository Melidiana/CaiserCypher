package app;

import service.DecryptService;
import service.EncryptService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EncryptService encryptService = new EncryptService();
        DecryptService decryptService = new DecryptService();
        System.out.println("Добро пожаловать в программу шифрования Цезарь." + "\n" + "Вам необходимо выбрать цифру:");
        System.out.println("1 - для шифрования файла, 2 - для расшифрования файла");
        int number = Integer.parseInt(reader.readLine());
            System.out.println("Введите путь к файлу:");
            String inputPath = reader.readLine();
            System.out.println("Введите путь для записи файла:");
            String outputPath = reader.readLine();
            System.out.println("Введите ключ:");
            int key = Integer.parseInt(reader.readLine());
            if (number == 1) {
                System.out.println(encryptService.encryptFile(inputPath, outputPath, key));
            }
            if (number == 2) {
                System.out.println(decryptService.decryptFile(inputPath, outputPath, key));
            }
    }
}