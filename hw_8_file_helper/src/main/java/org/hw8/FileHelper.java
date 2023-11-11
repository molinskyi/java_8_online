package org.hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    public static String startLink = System.getProperty("user.home");

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String position = "";
        while ((position) != null) {
            menu();
            position = bufferedReader.readLine();
            func(position, bufferedReader);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("List of files and packages 1");
        System.out.println("Create file 2");
        System.out.println("Create folder 3");
        System.out.println("Delete file or folder 4");
        System.out.println("Move file or folder 5");
        System.out.println("Find file or folder 6");
        System.out.println("Find text in name of file or folder 7");
        System.out.println("Move in file helper 8");
        System.out.println("Exit 9");
    }

    private void func(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case ("1"):
                list();
                break;
            case ("2"):
                createFile();
                break;
            case ("3"):
                createFolder();
                break;
            case ("4"):
                delete();
                break;
            case ("5"):
                move();
                break;
            case ("6"):
                find();
                break;
            case ("7"):
                findText();
                break;
            case ("8"):
                goTo();
                break;
            case ("9"):
                System.exit(0);
        }
    }

    private void list() {
        System.out.println("You in " + startLink);
        System.out.println("List of directory:");
        File f = new File(startLink);
        String[] pathnames = f.list();
        for (String pathname : pathnames) {
            System.out.println(pathname);
        }
    }

    private void goTo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int way = 0;
        System.out.println("Where to go? 1 to go to previous folder, 2 to go next.");
        way = Integer.valueOf(bufferedReader.readLine());
        if (way == 1) {
            startLink = startLink.substring(0, startLink.lastIndexOf("/"));
            System.out.println(startLink);
        }
        if (way == 2) {
            System.out.println("Which folder to go?");
            String linkAdd = bufferedReader.readLine();
            File checker = new File(startLink + "/" + linkAdd);
            if (checker.isDirectory()) {
                startLink += "/" + linkAdd;
            }
        }
    }

    private void createFile() throws IOException {
        System.out.println("Write name and format of file");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        File myObj = new File(startLink + "/" + name);
        myObj.createNewFile();
    }

    private void createFolder() throws IOException {
        System.out.println("Write name of folder");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        File myObj = new File(startLink + "/" + name);
        if (!myObj.exists()) {
            myObj.mkdirs();
        }
    }

    private void delete() throws IOException {
        System.out.println("Write name of file or folder to delete");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        File myObj = new File(startLink + "/" + name);
        myObj.delete();
    }

    private void move() throws IOException {
        System.out.println("Write name of target folder");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String url = bufferedReader.readLine();
        System.out.println("Write name folder or file to move");
        String name = bufferedReader.readLine();
        Files.move(Paths.get(startLink + "/" + name), Paths.get(startLink + "/" + url + "/" + name));
    }

    private void find() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write what to find");
        String name = bufferedReader.readLine();
        File directory = new File(startLink);
        String[] flist = directory.list();
        int flag = 0;
        if (flist == null) {
            System.out.println("Empty directory.");
        } else {
            for (int i = 0; i < flist.length; i++) {
                String filename = flist[i];
                if (filename.equalsIgnoreCase(name)) {
                    System.out.println(filename + " found");
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            System.out.println("Not Found");
        }
    }

    void findText() throws IOException {
        System.out.println("Write text to find");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        Files.walk(Paths.get(startLink))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try (BufferedReader reader = Files.newBufferedReader(file)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(s)) {
                                System.out.println(file + " found");
                            }
                        }
                    } catch (IOException e) {
                    }
                });
    }
}