package com.example.service.converter;


import com.example.domain.GameStepDomain;
import com.example.shared.GameStepDto;

import java.util.List;

public interface GameStepConverter {

    GameStepDto entityToDto(GameStepDomain gameStepDomain);

    GameStepDomain dtoToEntity(GameStepDto gameStepDto);

    List<GameStepDto> entityToDto(List<GameStepDomain> gameStepDomains);

}
