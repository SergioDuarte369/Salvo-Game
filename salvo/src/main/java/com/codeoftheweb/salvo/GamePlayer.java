package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    String date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;


    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Ship> tiposBarcos = new HashSet<>();


    public GamePlayer(){

    }


    public GamePlayer(long id, String date, Player player, Game game){
        this.id = id;
        this.date = date;
        this.player = player;
        this.game = game;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void añadirBarco(Ship ship) {
        ship.setGamePlayer(this);
        tiposBarcos.add(ship);
    }

    public Map<String, Object> transformToGameViewDTO() {
        return new LinkedHashMap<String, Object>(){{
            put("id", getId());
            put("player", getPlayer().transformToDTO());
        }};
    }

    public Map<String, Object> transformToDTOInfoGame() {
        return new LinkedHashMap<String, Object>(){{
            put("id", getId());
            put("player", getPlayer().transformToDTO());
        }};
    }


    @Override
    public String toString() {
        return "GamePlayer{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", player=" + player +
                ", game=" + game +
                '}';
    }

}