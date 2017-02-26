package com.example.shared;


import java.util.Date;

public class GameStepDto {

    private Long id;

    private Long gameId;

    private Short stepType;

    private Short row;

    private Short column;

    private Date createDate;

    public GameStepDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Short getStepType() {
        return stepType;
    }

    public void setStepType(Short stepType) {
        this.stepType = stepType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getRow() {
        return row;
    }

    public void setRow(Short row) {
        this.row = row;
    }

    public Short getColumn() {
        return column;
    }

    public void setColumn(Short column) {
        this.column = column;
    }
}
