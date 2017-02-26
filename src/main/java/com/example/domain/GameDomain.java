package com.example.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class GameDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OrderBy(value = "create_date ASC")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<GameStepDomain> steps;

    public GameDomain(){

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

    public List<GameStepDomain> getSteps() {
        return steps;
    }

    public void setSteps(List<GameStepDomain> steps) {
        this.steps = steps;
    }
}
