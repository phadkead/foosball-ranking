/*

angular.module('app',['ngComponentRouter'])



.config(function($locationProvider) {
  $locationProvider.html5Mode(true);
})


.value('$routerRootComponent', 'app')

.component('app', {
  template:
    `yes I am working!`
});
*/

//1. create module
(function(angular) {
   'use strict';
	angular.module('app', ['ngMaterial','ngAria', 'md.data.table','ngAnimate','ngComponentRouter','chart.js'])
//2. declare that my locationProvider will be HTML5. so it will create # based values
	.config(function($locationProvider) {
	  $locationProvider.html5Mode(true);
	})
//3. define main router of our application. here it is app
	.value('$routerRootComponent', 'app')
	.service('playerService', PlayerService)
//4. add all components
	.component('app', {
			templateUrl: 'tabs.html',
			$routeConfig: [
				{ path: '/ResultsRecorder', name: 'ResultsRecorder', component: 'resultsRecorder' , useAsDefault: true},
				{ path: '/Head2Head/', name: 'Head2Head', component: 'head2Head' },
				{ path: '/WinLossRate/', name: 'WinLossRate', component: 'winLossRate' }
			]
		})
	.component('resultsRecorder', {
	  templateUrl:'/app/views/recordPlayerResults.html',
	  bindings: { matchParticipants : '<' },
	  controller: ResultsRecorderController
	})
	.component('head2Head', {
		  templateUrl:'/app/views/head2Head.html',
		  bindings: { $router: '<'  },
		  controller: PlayerLoaderController
		})
	.component('winLossRate', {
		  templateUrl:'/app/views/winLossRate.html',
		  bindings: { $router: '<'  },
		  controller: PlayerLoaderController
	})  
	.config(function($mdThemingProvider) {
		$mdThemingProvider.theme('default')
		.primaryPalette('indigo')
		.accentPalette('orange')
		.backgroundPalette('grey') ;	
});
})(window.angular);