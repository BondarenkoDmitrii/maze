package com.bondarenko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public static Maze parser(Scanner sc) throws FileNotFoundException {
        String [] splitter;
        Boolean wall;
        Integer opening_period;
        Integer closing_period;
        Integer floor_height;
        Boolean start;
        Boolean end;
        Integer posI;
        Integer posJ;

        Integer time = Integer.parseInt(sc.nextLine());

        Maze maze = new Maze(time);

        while(sc.hasNext()) {
            splitter = sc.nextLine().split(";");
            wall = Boolean.parseBoolean(splitter[0]);
            opening_period = Integer.parseInt(splitter[1]);
            closing_period = Integer.parseInt(splitter[2]);
            floor_height = Integer.parseInt(splitter[3]);
            start = Boolean.parseBoolean(splitter[4]);
            end = Boolean.parseBoolean(splitter[5]);
            posI = Integer.parseInt(splitter[6]);
            posJ = Integer.parseInt(splitter[7]);

            Cell cell = new Cell(wall, opening_period, closing_period, floor_height, start, end, posI, posJ);

            maze.setCellArray(posI, posJ, cell);
        }
        writer("C:/Users/bonda/Downloads/Info.txt", "Файл успешно прочитан, данные записаны.");
        return maze;
    }

    public static void writer(String fileName, String message) {
        try(FileWriter writer = new FileWriter(fileName, true))
        {
            writer.write(message);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
