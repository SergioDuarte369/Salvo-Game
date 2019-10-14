package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String date;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();


    public Game(){

    }


    public Game(long id, String date){
        this.id = id;
        this.date = date;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Object> transformGamePlayersDTO() {
        return gamePlayers.stream().map(GamePlayer::transformToGameViewDTO).collect(toList());
    }
    public Optional<GamePlayer> getGamePlayerbyId(Long id){
        return gamePlayers.stream().filter(gamePlayer -> gamePlayer.getId() == id).findAny();
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }

}


