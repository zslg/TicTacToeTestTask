package com.example.service.converter.impl;

import com.example.domain.GameStepDomain;
import com.example.service.converter.GameStepConverter;
import com.example.shared.GameStepDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameStepConverterImpl implements GameStepConverter {
    public GameStepDto entityToDto(GameStepDomain gameStepDomain) {
        if (gameStepDomain == null) {
            return null;
        }
        GameStepDto gameStepDto = new GameStepDto();
        gameStepDto.setId(gameStepDomain.getId());
        gameStepDto.setGameId(gameStepDomain.getGameId());
        gameStepDto.setStepType(gameStepDomain.getStepType());
        gameStepDto.setCreateDate(gameStepDomain.getCreateDate());
        gameStepDto.setRow(gameStepDomain.getRow());
        gameStepDto.setColumn(gameStepDomain.getColumn());
        return gameStepDto;
    }

    public GameStepDomain dtoToEntity(GameStepDto gameStepDto) {
        if (gameStepDto == null) {
            return null;
        }
        GameStepDomain gameStepDomain = new GameStepDomain();
        gameStepDomain.setId(gameStepDto.getId());
        gameStepDomain.setGameId(gameStepDto.getGameId());
        gameStepDomain.setStepType(gameStepDto.getStepType());
        gameStepDomain.setCreateDate(gameStepDto.getCreateDate());
        gameStepDomain.setColumn(gameStepDto.getColumn());
        gameStepDomain.setRow(gameStepDto.getRow());
        return gameStepDomain;
    }

    @Override
    public List<GameStepDto> entityToDto(List<GameStepDomain> gameStepDomains) {
        if (gameStepDomains == null) {
            return null;
        }
        return gameStepDomains.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
