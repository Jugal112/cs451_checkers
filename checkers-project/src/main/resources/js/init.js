const Roles = {
    HOST: 'HOST',
    CLIENT: 'CLIENT',
};
const Colors = {
	    RED: 'RED',
	    BLACK: 'BLACK',
	};
var networkingRole;

/*
 * configure routes
 * handles controller configuration and template routing
*/

var checkersApp = angular.module('checkersApp', ['ui.router'])
.config(
  [          '$stateProvider', '$urlRouterProvider',
    function ($stateProvider,   $urlRouterProvider) {
	$urlRouterProvider.otherwise('/menu');

	$stateProvider
        .state('/menu', {
            templateUrl : 'pages/menu.html',
            controller  : 'menuController',
            url : '/menu'
        })

        .state('/host', {
            templateUrl : 'pages/host.html',
            controller  : 'hostController',
            url : '/host'
        })

        .state('/client', {
            templateUrl : 'pages/client.html',
            controller  : 'clientController',
            url : '/client'
        })

		.state('/checkers', {
		    templateUrl : 'pages/checkers.html',
		    controller  : 'checkersController',
            url : '/checkers'
		});
}]);

checkersApp.controller("mainController", ['$scope', '$state',
                                            function($scope, $state) {
	$scope.changeState = function (state) {
		console.log("changing the state!");
	    $state.go(state);
	};
}]);