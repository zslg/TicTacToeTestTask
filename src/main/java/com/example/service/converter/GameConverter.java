package com.example.service.converter;

import com.example.domain.GameDomain;
import com.example.shared.GameDto;

import java.util.List;

public interface GameConverter {

    GameDto entityToDto(GameDomain gameDomain);

    GameDomain dtoToEntity(GameDto gameDto);

    List<GameDto> entityToDto(List<GameDomain> gameDomains);

}
