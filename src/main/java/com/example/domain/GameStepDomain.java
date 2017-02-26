package com.example.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "game_step")
public class GameStepDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "step_type")
    private Short stepType;

    @Column(name = "row_")
    private Short row;

    @Column(name = "column_")
    private Short column;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",insertable = false,updatable = false)
    private Date createDate;

    public GameStepDomain() {
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
