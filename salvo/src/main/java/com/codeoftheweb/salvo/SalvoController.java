package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private GamePlayerRepository gPlayerRepo;
    @Autowired
    private ShipRepository shipRepo;

    @RequestMapping("/games")
    public Map< String, Object > getGames() {
        return new LinkedHashMap< String, Object >() {{
            put("games", gameRepo.findAll()
                    .stream()
                    .map(game -> infoGame(game))
                    .collect(toList()));

            System.out.println(gameRepo);
        }};

    }
    public Map<String,Object> infoGame (Game game){

        return new LinkedHashMap<String, Object>() {{
            put("id", game.getId());
            put("created", game.getDate());
            put("gamePlayers", game.getGamePlayers()
                    .stream()
                    .map(GamePlayer::transformToDTOInfoGame)
                    .collect(toList()));
        }};
    }


    @RequestMapping("/game_view/{gamePlayerId}")
    public Map <String, Object> gamesView(@PathVariable Long gamePlayerId){

        Optional<GamePlayer> gamePlayer = gPlayerRepo.findById(gamePlayerId);
            return new LinkedHashMap<String,Object>(){{
                put("games", gameRepo.findAll()
                        .stream()
                        .filter(game -> game.getId() == gamePlayerId)///filtrando por el id del juego igual al id del GAmepleyer
                        .map(game -> new LinkedHashMap<String, Object>() {{
                            put("game_id", game.getId());
                            put("gamePlayers", game.transformGamePlayersDTO());
                            put("ships", game.getGamePlayerbyId(gamePlayerId));


            }}));
    }};


    }
}







/*

    @RequestMapping("/games")
    public List<Object> getGames() {
        //Datos sacados de la BBDD
        List< Game > lista = gameRepo.findAll();

        //Array final
        List<Object> result = new ArrayList<Object>();


        Map<Object, String> data = new LinkedHashMap<Object, String>();
        for (int i = 0; i < lista.size(); i++) {
            data.put("id", String.valueOf(lista.get(i).getId()));
            data.put("created", lista.get(i).getDate());
            data.put("gp", )
            result.add(data);
         //   data.put("gamePlayers", lista.get(i).getGamePlayers().toString());
        }

        return  result;
    }

*/
/* esto va a mostrar 123

    @RequestMapping("/games")
    public List< Object > getGames() {
        List< Object > data = new ArrayList< Object >();
        List< Game > lista = gameRepo.findAll();
        for (int i = 0; i < lista.size(); i++) {
            data.add(lista.get(i).getId());

        }

        return data;
    }
*/


