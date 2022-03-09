package com.company;

import com.company.model.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter the path to the folder: ");
        Scanner scanner = new Scanner(System.in);
        String pathStr = scanner.nextLine();
        Path path = Paths.get(pathStr);
        if (Files.exists(path)) {
            System.out.println("Enter number of documents to process: ");
            int num = scanner.nextInt();
            File directory = new File(pathStr);
            List<File> fileList = List.of(Objects.requireNonNull(directory.listFiles()));
            if (fileList.size() == 0) {
                System.out.println("Folder is empty.");
            } else {
                if (fileList.stream().noneMatch(file -> file.getName().endsWith(".txt"))) {
                    System.out.println("All files are in the wrong format (only txt files).");
                } else {
                    Map<String, Document> map = new TreeMap<>();
                    fileList.stream()
                            .filter(file -> file.getName().endsWith(".txt"))
                            .filter(file -> fileList.indexOf(file) < num)
                            .forEach(file -> {
                                String nameOfFile = file.getName().substring(0, file.getName().lastIndexOf('.'));
                                map.put(nameOfFile, new Document(file));
                            });
                    System.out.println("Number of processed documents: " + map.size() + ".");
                    System.out.println("Number of invalid documents: " + (fileList.size() - map.size()) + ".");
                }
            }
        } else System.out.println("This folder does not exist.");
    }
}
