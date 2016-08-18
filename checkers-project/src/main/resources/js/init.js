var checkersApp = angular.module('checkersApp', ['ngRoute', 'ngSanitize']);
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
checkersApp.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/menu.html',
            controller  : 'menuController'
        })

        .when('/host', {
            templateUrl : 'pages/host.html',
            controller  : 'hostController'
        })

        .when('/client', {
            templateUrl : 'pages/client.html',
            controller  : 'clientController'
        })

		.when('/checkers', {
		    templateUrl : 'pages/checkers.html',
		    controller  : 'checkersController'
		});
});