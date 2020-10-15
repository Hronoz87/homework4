package main;

import javax.imageio.IIOException;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {

        GameProgress user1 = new GameProgress(100, 2, 30, 10);
        GameProgress user2 = new GameProgress(80, 5, 50, 30);
        GameProgress user3 = new GameProgress(90, 3, 40, 20);

        StringBuilder sb = new StringBuilder();

        File res = new File("res");
        if (res.mkdir())
            System.out.println("Каталог создан");
        sb.append(res).append(" Каталог создан").append("\n");

        File drawables = new File("res", "drawbles");
        if (drawables.mkdir())
            System.out.println("Каталог создан");
        sb.append(drawables).append(" Каталог создан").append("\n");

        File vectors = new File("res", "vectors");
        if (vectors.mkdir())
            System.out.println("Каталог создан");
        sb.append(vectors).append(" Каталог создан").append("\n");

        File icons = new File("res", "icons");
        if (icons.mkdir())
            System.out.println("Каталог создан");
        sb.append(icons).append(" Каталог создан").append("\n");

        File savegames = new File("savegames");
        if (savegames.mkdir())
            System.out.println("Каталог создан");
        sb.append(savegames).append(" Каталог создан").append("\n");

        File temp = new File("temp");
        if (temp.mkdir())
            System.out.println("Каталог создан");
        sb.append(temp).append(" Каталог создан").append("\n");

        File temp1 = new File("temp", "Temp.txt");
        try {
            if (temp1.createNewFile())
                System.out.println("Файл создан");
        } catch (IIOException ex) {
            System.out.println(ex.getMessage());
        }
        sb.append(temp1).append(" Файл создан").append("\n");

        File main = new File("src", "main");
        if (main.mkdir())
            System.out.println("Каталог создан");
        sb.append(main).append(" Каталог создан").append("\n");

//        File utils = new File("main", "Utils.java");   Не получается создать файл, не могу понять почему
//        try {
//            if (utils.createNewFile())
//                System.out.println("Файл создан");
//        } catch (IIOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        sb.append(utils).append(" Файл создан").append("\n");

        File test = new File("src", "test");
        if (test.mkdir()) {
            System.out.println("Каталог создан");
            sb.append(test).append(" Каталог создан").append("\n");
        }




        saveGame("/home/andrei/Netologia/JavaCore/homework4/games/savegames/save1.dat", user1);
        saveGame("/home/andrei/Netologia/JavaCore/homework4/games/savegames/save2.dat", user2);
        saveGame("/home/andrei/Netologia/JavaCore/homework4/games/savegames/save3.dat", user3);

        zipFiles("/home/andrei/Netologia/JavaCore/homework4/games/savegames/zip1.zip",
                 "/home/andrei/Netologia/JavaCore/homework4/games/savegames/save1.dat");
        zipFiles("/home/andrei/Netologia/JavaCore/homework4/games/savegames/zip2.zip",
                "/home/andrei/Netologia/JavaCore/homework4/games/savegames/save2.dat");
        zipFiles("/home/andrei/Netologia/JavaCore/homework4/games/savegames/zip3.zip",
                "/home/andrei/Netologia/JavaCore/homework4/games/savegames/save3.dat");

        File save1 = new File("savegames", "save1.dat");
        if (save1.delete())
            System.out.println("Файл удален");
        sb.append(save1).append(" Файл удален").append("\n");

        File save2 = new File("savegames", "save2.dat");
        if (save2.delete())
            System.out.println("Файл удален");
        sb.append(save2).append(" Файл удален").append("\n");

        File save3 = new File("savegames", "save3.dat");
        if (save3.delete())
            System.out.println("Файл удален");
        sb.append(save3).append(" Файл удален").append("\n");

        String result = sb.toString();

        writeFile(result);
        readFile(result);
    }

    public static void writeFile(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter("Temp.txt", false)) {
            writer.write(fileName);
            writer.append('\n');
        } catch (IIOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readFile(String fileName) {
        try (FileReader reader = new FileReader("Temp.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println((char) c);
            }
        } catch (IIOException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {

        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String fullPath, String path) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fullPath));
             FileInputStream fis = new FileInputStream(path)) {
            ZipEntry entry = new ZipEntry(path);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
