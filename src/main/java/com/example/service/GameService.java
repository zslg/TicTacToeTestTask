package com.example.service;

import com.example.domain.GameStatus;
import com.example.shared.GameDto;
import com.example.shared.GameStepDto;

import java.util.List;

public interface GameService {

    List<GameDto> getAllGames();

    GameDto create(GameDto gameDto);

    GameDto findById(Long id);

    GameStatus checkGameResult(GameDto gameDto);

    GameStatus makeStep(GameStepDto gameStepDto);

}
