package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String shipType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    private List<String> shipLocalitation = new ArrayList<>();

    public Ship (){

    }

    public Ship ( long id, String shipType, List <String> shipLocalization,GamePlayer gamePlayer){
        this.id = id;
        this.shipType = shipType;
        this.shipLocalitation = shipLocalization;
        this.gamePlayer = gamePlayer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List< String > getShipLocalitation() {
        return shipLocalitation;
    }

    public void setShipLocalitation(List< String > shipLocalitation) {
        this.shipLocalitation = shipLocalitation;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", shipType='" + shipType + '\'' +
                ", gamePlayer=" + gamePlayer +
                '}';
    }
}
