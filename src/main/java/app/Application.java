package app;
import service.DecryptService;
import service.EncryptService;
import service.WriteService;
import util.Alphabet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        WriteService writeService = new WriteService();
        EncryptService encryptService = new EncryptService(writeService);
        DecryptService decryptService = new DecryptService(writeService);
        System.out.println("Добро пожаловать в программу шифрования Цезарь." + "\n" + "Вам необходимо выбрать цифру:");
        System.out.println("1 - для шифрования файла, 2 - для расшифрования файла");
        int number = Integer.parseInt(reader.readLine());
            System.out.println("Введите путь к файлу:");
            String inputPath = reader.readLine();
            System.out.println("Введите путь для записи файла:");
            String outputPath = reader.readLine();
            int key;
             do {
                 System.out.println("Введите ключ в диапазоне от 1 до " + Alphabet.encryptMap.size());
                 key = Integer.parseInt(reader.readLine());
            } while (key > Alphabet.encryptMap.size() || key <= 0);
            if (number == 1) {
                System.out.println(encryptService.encryptFile(inputPath, outputPath, key));
            }
            if (number == 2) {
                System.out.println(decryptService.decryptFile(inputPath, outputPath, key));
            }
    }
}