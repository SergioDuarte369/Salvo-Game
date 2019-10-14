package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.jvm.hotspot.ui.EditableAtEndDocument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


/////Función para crear los jugadores con CommandLineRunner
    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
        return (args) -> {

            ///Players
            Player p1 = new Player(1, "Sergio");
            Player p2  = new Player(2, "Duarte");

            ///Games
            Game g1 = new Game(1, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));
            Game g2 = new Game(2, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusSeconds(3600)));

            ///GamePlayer repo
            GamePlayer gp1 =  new GamePlayer(1, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()),p1,g1);
            GamePlayer gp2 =  new GamePlayer(2, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusSeconds(3600)),p2,g1);
            GamePlayer gp3 =  new GamePlayer(3, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusSeconds(7200)),p1,g2);

            ///Ships     Creando los barcoss y asociandolos con un gamePlayer (gp)
            Ship ship1 = new Ship (1, "Titanic", Arrays.asList("A1", "C1"), gp1);
            Ship ship2 = new Ship (2,"Pato Hinchable",  Arrays.asList("B2", "C3"), gp2);
            Ship ship3 = new Ship (3,"Cayuco", Arrays.asList("C1","B2"), gp3);




            ///Guardando los objetos en el repositorio
            ///Guardando players al repositorio
            playerRepository.save(p1);
            playerRepository.save(p2);
            ///Guardando games al repositorio
            gameRepository.save(g1);
            gameRepository.save(g2);
            ///Guardando gameplayers al repositorio
            gamePlayerRepository.save(gp1);
            gamePlayerRepository.save(gp2);
            gamePlayerRepository.save(gp3);
            /// Guardando ships en gamePlayer
            gp1.añadirBarco(ship1);
            gp2.añadirBarco(ship2);
            gp3.añadirBarco(ship3);
            ///Añadiendo ships en shipRepository
            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);


        };
    }



    }





