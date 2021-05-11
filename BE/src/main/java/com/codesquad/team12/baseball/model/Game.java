package com.codesquad.team12.baseball.model;

import com.codesquad.team12.baseball.dto.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    @Id
    private Long id;

    private Integer homeScore;
    private Integer awayScore;
    private boolean isEnd;
    private String homeName;
    private String awayName;

    @MappedCollection(idColumn = "game_id", keyColumn = "id")
    private Map<Long, Inning> innings = new HashMap<>();

    @MappedCollection(idColumn = "game_id", keyColumn = "id")
    private Map<Long, Playing> playings = new HashMap<>();


    public Game(Long id, Integer homeScore, Integer awayScore, boolean isEnd, String homeName, String awayName) {
        this.id = id;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.isEnd = isEnd;
        this.homeName = homeName;
        this.awayName = awayName;
    }

    public static GameDto createGameDto(Game game) {
//        TODO: findTeamById needed to get isPlaying
        TeamDto home = Team.createTeamDto(new Team(game.homeName, false));
        TeamDto away = Team.createTeamDto(new Team(game.awayName, false));
        return new GameDto(game.id, home, away);
    }

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public static ScoreDto createScoreDto(Game game) {
        ScoreTeamDto home = new ScoreTeamDto(game.homeName, null);
        ScoreTeamDto away = new ScoreTeamDto(game.awayName, null);

        return new ScoreDto(home, away);
    }

}
