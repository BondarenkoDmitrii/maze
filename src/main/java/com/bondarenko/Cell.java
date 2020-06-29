package com.bondarenko;

public class Cell {
    private Boolean wall;
    private Integer opening_period;
    private Integer closing_period;
    private Integer floor_height;
    private Boolean start;
    private Boolean end;
    private Integer posI;
    private Integer posJ;

    public Cell(Boolean wall, Integer opening_period, Integer closing_period, Integer floor_height, Boolean start, Boolean end, Integer posI, Integer posJ) {
        this.wall = wall;
        this.opening_period = opening_period;
        this.closing_period = closing_period;
        this.floor_height = floor_height;
        this.start = start;
        this.end = end;
        this.posI = posI;
        this.posJ = posJ;
    }

    public Boolean getWall() {
        return wall;
    }

    public void setWall(Boolean wall) {
        this.wall = wall;
    }

    public Integer getOpening_period() {
        return opening_period;
    }

    public void setOpening_period(Integer opening_period) {
        this.opening_period = opening_period;
    }

    public Integer getClosing_period() {
        return closing_period;
    }

    public void setClosing_period(Integer closing_period) {
        this.closing_period = closing_period;
    }

    public Integer getFloor_height() {
        return floor_height;
    }

    public void setFloor_height(Integer floor_height) {
        this.floor_height = floor_height;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Integer getPosI() {
        return posI;
    }

    public void setPosI(Integer posI) {
        this.posI = posI;
    }

    public Integer getPosJ() {
        return posJ;
    }

    public void setPosJ(Integer posJ) {
        this.posJ = posJ;
    }
}
