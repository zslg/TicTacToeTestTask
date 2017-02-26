package com.example.shared;


import com.example.domain.GameStatus;

import java.util.List;

public class GameDto {

    private Long id;

    private String name;

    private GameStatus status;

    private List<GameStepDto> steps;

    public GameDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<GameStepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<GameStepDto> steps) {
        this.steps = steps;
    }
}
