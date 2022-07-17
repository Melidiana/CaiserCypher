package app;

import service.DecryptService;
import service.EncryptService;
import service.ReadService;
import service.WriteService;
import util.Alphabet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ReadService readService = new ReadService();
        WriteService writeService = new WriteService();
        EncryptService encryptService = new EncryptService(writeService, readService);
        DecryptService decryptService = new DecryptService(writeService, readService);
        System.out.println("Добро пожаловать в программу шифрования Цезарь." + "\n" + "Вам необходимо выбрать цифру:");
        System.out.println("1 - для шифрования файла, 2 - для расшифрования файла");
        int number = Integer.parseInt(reader.readLine());
        System.out.println("Введите путь к файлу:");
        String inputPath = reader.readLine();
        File inFile = new File(inputPath);
        String in;
        do {
            System.out.println("Введите правильный путь:");
            in = reader.readLine();
            File fileIn = new File(in);
            if (fileIn.exists()) {
                break;
            }
        } while (!inFile.exists());
        System.out.println("Введите путь для записи файла:");
        String outputPath = reader.readLine();
        File outFile = new File(outputPath);
        String out;
        do {
            System.out.println("Введите правильный путь:");
            out = reader.readLine();
            File fileOut = new File(out);
            if (fileOut.exists()) {
                break;
            }
        } while (outFile.exists());
        int key;
        do {
            System.out.println("Введите ключ в диапазоне от 1 до " + Alphabet.encryptMap.size());
            key = Integer.parseInt(reader.readLine());
        } while (key > Alphabet.encryptMap.size() || key <= 0);
        if (number == 1) {
            encryptService.encryptFile(in, out, key);
        }
        if (number == 2) {
            System.out.println(decryptService.decryptFile(in, out, key));
        }
    }
}