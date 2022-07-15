package app;

import service.EncryptService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EncryptService encryptService = new EncryptService();
        System.out.println("����� ���������� � ��������� ���������� ������");
        System.out.println("������� ���� � �����:");
        String inputPath = reader.readLine();
        System.out.println("������� ���� � �������������� �����:");
        String outputPath = reader.readLine();
        System.out.println("������� ���� ��� ����������");
        int key = Integer.parseInt(reader.readLine());
        System.out.println(encryptService.encryptFile(inputPath, outputPath, key));
    }
}