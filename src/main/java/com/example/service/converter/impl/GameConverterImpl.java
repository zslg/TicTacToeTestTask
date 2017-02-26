package com.example.service.converter.impl;

import com.example.domain.GameDomain;
import com.example.service.converter.GameConverter;
import com.example.service.converter.GameStepConverter;
import com.example.shared.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameConverterImpl implements GameConverter {

    @Autowired
    private GameStepConverter gameStepConverter;

    public GameDto entityToDto(GameDomain gameDomain) {
        if (gameDomain == null) {
            return null;
        }
        GameDto gameDto = new GameDto();
        gameDto.setId(gameDomain.getId());
        gameDto.setName(gameDomain.getName());
        gameDto.setStatus(gameDomain.getStatus());
        gameDto.setSteps(gameStepConverter.entityToDto(gameDomain.getSteps()));
        return gameDto;
    }

    public GameDomain dtoToEntity(GameDto gameDto) {
        if (gameDto == null) {
            return null;
        }
        GameDomain gameDomain = new GameDomain();
        gameDomain.setId(gameDto.getId());
        gameDomain.setName(gameDto.getName());
        gameDomain.setStatus(gameDto.getStatus());
        return gameDomain;
    }

    @Override
    public List<GameDto> entityToDto(List<GameDomain> gameDomains) {
        if (gameDomains == null) {
            return null;
        }
        return gameDomains.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
