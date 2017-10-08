function ResultsRecorderController(playerService) {
	    var $ctrl = this;
	    $ctrl.saveMatchParticipants = function (matchParticipants) {
	      playerService.saveMatchParticipants(matchParticipants).then(function (response) {
	        alert("Data Saved");
	      }, function myError(response) {
	        alert("error");
	      });
	    };
}