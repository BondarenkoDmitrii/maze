package com.bondarenko;

import java.util.ArrayList;

public class Maze {
    private Cell [][] mazeArray;
    private int move_count;
    private int stock_time;

    public Maze() {
        this.mazeArray = new Cell[8][8];
        this.move_count = 0;
    }

    public Maze(int move_count) {
        this.mazeArray = new Cell[8][8];
        this.move_count = 0;
        this.stock_time = move_count;
    }

    public void mazeWalkthrough() {
        int coordinate1 = -1;
        int coordinate2 = -1;

//        ищем начало лабиринта
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.mazeArray[i][j].getStart()) {
                    coordinate1 = i;
                    coordinate2 = j;
                }
            }
        }

        if (coordinate1 == -1)
            return;

//        запускаем рекурсивную функцию для прохода лабиринта
        move(coordinate1, coordinate2);
    }

    public void move(int i, int j) {
        if (this.mazeArray[i][j].getEnd()){
            WorkWithFile.writer("src/main/files/Info.txt",
                                "Шар докатился до конца лабиринта за " + this.move_count + " ходов, при старте с " + this.stock_time + " хода");
            return;
        }

        ArrayList<Cell> arrayList = new ArrayList<Cell>();

        if (i > 0 && i < 7 && j > 0 && j < 7){
            arrayList.add(this.mazeArray[i - 1][j]);
            arrayList.add(this.mazeArray[i + 1][j]);
            arrayList.add(this.mazeArray[i][j - 1]);
            arrayList.add(this.mazeArray[i][j + 1]);
        }

        if (i > 0 && i < 7 && j == 0){
            arrayList.add(this.mazeArray[i - 1][j]);
            arrayList.add(this.mazeArray[i + 1][j]);
            arrayList.add(this.mazeArray[i][j + 1]);
        }

        if (i > 0 && i < 7 && j == 7){
            arrayList.add(this.mazeArray[i - 1][j]);
            arrayList.add(this.mazeArray[i + 1][j]);
            arrayList.add(this.mazeArray[i][j - 1]);
        }

        if (j > 0 && j < 7 && i == 0){
            arrayList.add(this.mazeArray[i][j - 1]);
            arrayList.add(this.mazeArray[i][j + 1]);
            arrayList.add(this.mazeArray[i + 1][j]);
        }

        if (j > 0 && j < 7 && i == 7){
            arrayList.add(this.mazeArray[i][j - 1]);
            arrayList.add(this.mazeArray[i][j + 1]);
            arrayList.add(this.mazeArray[i - 1][j]);
        }

        if (i == 0 && j == 0){
            arrayList.add(this.mazeArray[i][j + 1]);
            arrayList.add(this.mazeArray[i + 1][j]);
        }

        if (i == 0 && j == 7){
            arrayList.add(this.mazeArray[i][j - 1]);
            arrayList.add(this.mazeArray[i + 1][j]);
        }

        if (i == 7 && j == 0){
            arrayList.add(this.mazeArray[i][j + 1]);
            arrayList.add(this.mazeArray[i - 1][j]);
        }

        if (i == 7 && j == 7){
            arrayList.add(this.mazeArray[i][j - 1]);
            arrayList.add(this.mazeArray[i - 1][j]);
        }

        ArrayList<Cell> arrayEquals = new ArrayList<Cell>();
        Cell next = this.mazeArray[i][j];
        for (Cell cell: arrayList) {
            if (cell.getFloor_height() < next.getFloor_height()) {
                if (cell.getWall()) {
                    if (cell.getOpening_period() == 0) {
//                        алгоритм для закрывающейся перегородки
                        if (this.move_count % cell.getClosing_period() != 0 && this.move_count != 0) {
                            next = cell;
                        }
                    }
                    else {
//                        алгоритм для открывающейся перегородки
                        if (this.move_count % cell.getOpening_period() == 0 && this.move_count != 0) {
                            next = cell;
                        }
                    }
                }
                else {
                    next = cell;
                }
            }
            if (cell.getFloor_height() == next.getFloor_height()) {
                if (cell.getWall()) {
                    if (cell.getOpening_period() == 0) {
//                        алгоритм для закрывающейся перегородки
                        if (this.move_count % cell.getClosing_period() != 0 && this.move_count != 0) {
                            arrayEquals.add(cell);
                        }
                    }
                    else {
//                        алгоритм для открывающейся перегородки
                        if (this.move_count % cell.getOpening_period() == 0 && this.move_count != 0) {
                            arrayEquals.add(cell);
                        }
                    }
                }
                else {
                    arrayEquals.add(cell);
                }
            }
        }

        if (next.equals(this.mazeArray[i][j]) && !arrayEquals.isEmpty()) {
            next = arrayEquals.get(rnd(arrayEquals.size() - 1));
        }

//        если шару некуда катиться программа останавливается
        if (next.equals(this.mazeArray[i][j])){
            WorkWithFile.writer("src/main/files/Info.txt",
                                "Шару некуда катиться, программа остановлена.");
            return;
        }

        this.move_count++;
        WorkWithFile.writer("src/main/files/Info.txt",
                    "Шаг номер " + this.move_count +
                            " позиция " + "(" + next.getPosI() + ";" + next.getPosJ() + ")");
        move(next.getPosI(), next.getPosJ());
    }

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public Cell[][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(Cell[][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public int getMove_count() {
        return move_count;
    }

    public void setMove_count(int move_count) {
        this.move_count = move_count;
    }

    public Cell getCellArray(int i, int j) {
        return mazeArray[i][j];
    }

    public void setCellArray(int i, int j, Cell cell) {
        this.mazeArray[i][j] = cell;
    }

    public int getStock_time() {
        return stock_time;
    }

    public void setStock_time(int stock_time) {
        this.stock_time = stock_time;
    }
}
