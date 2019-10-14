const battle = new Vue({
    el: "#vue-battle",

    data : {
gamesData : [],
playerData : [],
    },
    methods:{

        getData(){
            console.log("Vue");
        fetch("http://localhost:8080/api/games")
        .then(function(response)  {
            return response.json();
        })
        .then(function(myJson){
            console.log(myJson)
            battle.gamesData = myJson.games.sort((a, b) => { return a.id - b.id; });
            battle.playerData = myJson.games[0].gamePlayers.sort((a, b) => { return a.id - b.id; });
            console.log(battle.gamesData[0].gamePlayers)
           //// battle.players = myJson.games.gamePlayers.player.name;

        })

        }


    },

    created () {
        this.getData();

    }



});