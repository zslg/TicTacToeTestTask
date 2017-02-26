package com.example.service.impl;

import com.example.dao.GameDao;
import com.example.dao.GameStepDao;
import com.example.domain.GameDomain;
import com.example.domain.GameStatus;
import com.example.domain.GameStepDomain;
import com.example.service.GameService;
import com.example.service.converter.GameConverter;
import com.example.service.converter.GameStepConverter;
import com.example.shared.GameDto;
import com.example.shared.GameStepDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;

    private GameConverter gameConverter;

    private GameStepDao gameStepDao;

    private GameStepConverter gameStepConverter;

    private final int maxRows = 3;
    private final int maxColumns = 3;
    private final List<GameStatus> finishStatuses = Arrays.asList(GameStatus.X_WON, GameStatus.O_WON, GameStatus.DRAW);

    public GameServiceImpl(GameDao gameDao, GameConverter gameConverter, GameStepDao gameStepDao, GameStepConverter gameStepConverter) {
        this.gameDao = gameDao;
        this.gameConverter = gameConverter;
        this.gameStepDao = gameStepDao;
        this.gameStepConverter = gameStepConverter;
    }

    @Transactional
    @Override
    public List<GameDto> getAllGames() {
        List<GameDomain> games = gameDao.findAll();
        List<GameDto> gameDtos = gameConverter.entityToDto(games);
        return gameDtos;
    }

    @Transactional
    @Override
    public GameDto create(GameDto gameDto) {
        gameDto.setStatus(GameStatus.IN_PROGRESS);
        GameDomain gameDomain = gameConverter.dtoToEntity(gameDto);
        gameDao.saveAndFlush(gameDomain);
        return gameConverter.entityToDto(gameDomain);
    }

    @Transactional
    @Override
    public GameDto findById(Long id) {
        GameDomain game = gameDao.findOne(id);
        return gameConverter.entityToDto(game);
    }

    @Override
    public GameStatus checkGameResult(GameDto gameDto) {
        List<GameStepDto> steps = gameDto.getSteps();
        if (steps == null || steps.isEmpty()) {
            return null;
        }
        GameStatus gameStatus = null;
        Short[][] gameMap = new Short[maxRows][maxColumns];
        for (GameStepDto step : steps) {
            gameMap[step.getRow()][step.getColumn()] = step.getStepType();
        }
//        check horizontal win
        for (int i = 0; i < maxRows; i++) {
            Short firstElement = gameMap[i][0];
            if (firstElement == null) {
                continue;
            }
            int theSameInLine = 0;
            for (int i1 = 1; i1 < maxColumns; i1++) {
                if (firstElement.equals(gameMap[i][i1])) {
                    theSameInLine++;
                }
            }
            if (theSameInLine == maxColumns - 1) {
                gameStatus = firstElement == 1 ? GameStatus.X_WON : GameStatus.O_WON;
                return gameStatus;
            }
        }
//        check vertical win
        for (int i = 0; i < maxColumns; i++) {
            Short firstElement = gameMap[0][i];
            if (firstElement == null) {
                continue;
            }
            int theSameInColumn = 0;
            for (int i1 = 1; i1 < maxRows; i1++) {
                if (firstElement.equals(gameMap[i1][i])) {
                    theSameInColumn++;
                }
            }
            if (theSameInColumn == maxColumns - 1) {
                gameStatus = firstElement == 1 ? GameStatus.X_WON : GameStatus.O_WON;
                return gameStatus;
            }
        }

//        check diagonal 1 win (right-left)
        Short firstElement = gameMap[0][0];
        int theSameInDiagonal1 = 0;
        if (firstElement != null) {
            for (int i = 1; i < maxRows; i++) {
                if (firstElement.equals(gameMap[i][i])) {
                    theSameInDiagonal1++;
                }
            }
            if (theSameInDiagonal1 == maxRows - 1) {
                gameStatus = firstElement == 1 ? GameStatus.X_WON : GameStatus.O_WON;
                return gameStatus;
            }
        }

//            check diagonal 2 win (left-right)
        int start = 0;
        firstElement = gameMap[maxRows - 1][start];
        if (firstElement != null) {
            int theSameInDiagonal2 = 0;
            for (int i = maxRows - 2; i >= 0; i--) {
                if (firstElement.equals(gameMap[i][start + 1])) {
                    theSameInDiagonal2++;
                }
                start++;
            }
            if (theSameInDiagonal2 == maxRows - 1) {
                gameStatus = firstElement == 1 ? GameStatus.X_WON : GameStatus.O_WON;
                return gameStatus;
            }
        }

//        if no win then check on draw
        if (steps.size() == maxRows * maxColumns) {
            gameStatus = GameStatus.DRAW;
            return gameStatus;
        }

        return gameStatus;
    }

    @Transactional
    @Override
    public GameStatus makeStep(GameStepDto gameStepDto) {
        GameStepDomain gameStepDomain = gameStepConverter.dtoToEntity(gameStepDto);
        gameStepDao.saveAndFlush(gameStepDomain);
        GameDomain game = gameDao.findOne(gameStepDomain.getGameId());
        GameDto gameDto = gameConverter.entityToDto(game);
        GameStatus gameStatus = checkGameResult(gameDto);
        if (gameStatus != null && finishStatuses.contains(gameStatus)) {
            game.setStatus(gameStatus);
            gameDao.save(game);
        }
        return gameStatus;
    }
}
