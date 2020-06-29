package com.bondarenko;

import java.util.ArrayList;

public class Maze {
    private Cell [][] mazeArray;
    private int move_count;

    public Maze() {
        this.mazeArray = new Cell[8][8];
        this.move_count = 0;
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

//        запускаем рекурсивную функцию высчитывания следующего хода
        move(coordinate1, coordinate2);
    }

    public void move(int i, int j) {
        if (this.mazeArray[i][j].getEnd())
            return;

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

        Cell next = this.mazeArray[i][j];
        for (Cell cell: arrayList) {
            if (cell.getFloor_height() < next.getFloor_height() && !cell.getWall()) {
                next = cell;
                return;
            }
        }

        if (next == this.mazeArray[i][j]){
            return;
        }

        move(next.getPosI(), next.getPosJ());
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
}
