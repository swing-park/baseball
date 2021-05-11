package com.codesquad.team12.baseball.controller;

import com.codesquad.team12.baseball.dto.GameInitDto;
import com.codesquad.team12.baseball.dto.ScoreDto;
import com.codesquad.team12.baseball.dto.ScoreTeamDto;
import com.codesquad.team12.baseball.model.Game;
import com.codesquad.team12.baseball.service.GameService;
import com.codesquad.team12.baseball.service.TeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games/{gameId}")
public class GameController {
    private final GameService gameService;
    private final TeamService teamService;

    public GameController(GameService gameService, TeamService teamService) {
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @GetMapping
    public GameInitDto getGame(@PathVariable Long gameId) {
        Game game = gameService.findById(gameId);
        return new GameInitDto(teamService.findById(game.getHomeName()),
                teamService.findById(game.getAwayName()));
    }

    @GetMapping("/scores")
    public ScoreDto getScores(@PathVariable Long gameId) {
        Game game = gameService.findById(gameId);
        ScoreTeamDto home = new ScoreTeamDto(game.getHomeName(), null);
        ScoreTeamDto away = new ScoreTeamDto(game.getAwayName(), null);
        return new ScoreDto(home, away);
    }

    @GetMapping("/players")
    public void getPlayers(@PathVariable Long gameId) {
    }

    @PutMapping
    public void putGame(@PathVariable Long gameId) {
//        TODO: To get parameter from request body using DTO
    }

    @PutMapping("/{teamId}")
    public void putPlaying(@PathVariable Long gameId, @PathVariable Long teamId) {
    }
}
