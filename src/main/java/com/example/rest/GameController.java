package com.example.rest;

import com.example.domain.GameStatus;
import com.example.service.GameService;
import com.example.shared.GameDto;
import com.example.shared.GameStepDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/")
    public String index(Model model){
        List<GameDto> games = gameService.getAllGames();
        model.addAttribute("games",games);
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "/game")
    public ResponseEntity<GameDto> createGame(@NotNull @RequestBody GameDto request){
        GameDto gameDto = gameService.create(request);
        return ResponseEntity.ok(gameDto);
    }

    @GetMapping(value = "/game/{game_id}")
    public String getGameById(@PathVariable(value = "game_id") Long gameId, Model model){
        model.addAttribute("gameId",gameId);
        return "game";
    }

    @ResponseBody
    @GetMapping(value = "/game/rest/{game_id}")
    public ResponseEntity<GameDto> getGameByIdRest(@PathVariable(value = "game_id") Long gameId){
        GameDto game = gameService.findById(gameId);
        return ResponseEntity.ok(game);
    }

    @ResponseBody
    @PostMapping(value = "/game/step")
    public ResponseEntity<Map<String,Object>> makeStep(@NotNull @RequestBody GameStepDto request){
        GameStatus gameStatus = gameService.makeStep(request);
        Map<String,Object> response = new HashMap<>();
        response.put("status",gameStatus);
        return ResponseEntity.ok(response);
    }

}
