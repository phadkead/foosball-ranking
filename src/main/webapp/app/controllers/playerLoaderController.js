function PlayerLoaderController(playerService) {
		var $ctrl = this;
		$ctrl.getAllPlayers = function () {
			playerService.getPlayers().then(function (response) {
				$ctrl.players = response.data;
			}, function myError(response) {
				//alert("error");
			});
		};
		
		$ctrl.setWinLossChartData = function (player) {
			 $ctrl.colors = ["rgba(159,204,0,0.5)","rgba(154,154,154,0.5)"];
			 $ctrl.labels = ['Win','Loss'];
			 var winningRatio = (player.wonMatches/player.totalMatches) * 100;
			 var lossRatio = 100 - winningRatio;
			 $ctrl.data = [winningRatio, lossRatio];
		};
		
		$ctrl.setHead2HeadChartData = function (head2Head) {
			 $ctrl.colors = ["#472b93","#f47920"];
			 $ctrl.labels = [head2Head[0].name,head2Head[1].name];
			 $ctrl.data = [head2Head[0].percentageWin, head2Head[1].percentageWin];
		};
		
		
		$ctrl.getwonMatches = function (name) {
			playerService.getwonMatches(name).then(function (response) {
				$ctrl.players = response.data;
			}, function myError(response) {
				//alert("error");
			});
		};
		
		$ctrl.findHead2Head = function(selectedPlayers){
			playerService.findHead2Head(selectedPlayers).then (function(response){
				$ctrl.head2Head = response.data;
				playerService.setWinPercentage($ctrl.head2Head);
				$ctrl.setHead2HeadChartData($ctrl.head2Head)
			}, function myError(response) {
				//alert("error");
			});
		};
	}