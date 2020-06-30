package com.bondarenko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/files/DataMaze.txt"));
        Integer task = Integer.parseInt(sc.nextLine());

        switch (task) {
            case 1:
                WorkWithFile.parser(sc).mazeWalkthrough();
                break;

//            case 2:
//                WorkWithFile.parser(sc).mazeWalkthrough();
//                break;

            default:
                WorkWithFile.writer("src/main/files/Info.txt", "Неверный номер задания.");
                break;
        }

    }
}
