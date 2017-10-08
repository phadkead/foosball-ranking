function PlayerService($http) {

	    this.getPlayer = function (id) {
	      var playerDetailPromise = $http.get("/players/" + id);
	      return playerDetailPromise;
	    };

	    this.findHead2Head = function (selectedPlayers) {
	        console.log("finding head2head");
	        var url = "/foosballRanking/match/head2head/players?player1="+selectedPlayers[0].name+"&player2="+selectedPlayers[1].name;
	        var playersPromise = $http.get(url);
	        return playersPromise;
	      };
	      
	      this.getPlayers = function () {
		        console.log("initializing players");
		        var playersPromise = $http.get("/foosballRanking/match/players/");
		        return playersPromise;
		 };  
		 
		 this.setWinPercentage = function (head2Head){
			 for(var i=0, len=head2Head.length; i < len; i++){
				 	var player = head2Head[i];
		    		var totalMatches = player.totalMatches;
		    		var totalWins = player.wonMatches;
		    		player.percentageWin = (totalWins/totalMatches)*100;
		    	}
			 return head2Head;
		 }
	      
	    this.saveMatchParticipants = function(matchParticipants){
	    	var jsonStr = '{"matchParticipants":[]}';
	    	var obj = JSON.parse(jsonStr);
	    	for (var i in matchParticipants) {
	    		obj['matchParticipants'].push(matchParticipants[i]);
	    	}
	    	var jsonStr = JSON.stringify(obj);
	    	var config =  {
	    		url: '/foosballRanking/match',
	    		method: "POST",
	    		data: jsonStr,
	    		headers: {
	    			'Content-Type': 'application/json; charset=utf-8'
	    		}
	    	};
	    	return $http(config);
	    }
	}