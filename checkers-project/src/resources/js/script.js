var checkersApp = angular.module('checkersApp', ['ngRoute']);

// configure our routes
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
            controller  : 'contactController'
        })

		.when('/checkers', {
		    templateUrl : 'pages/checkers.html',
		    controller  : 'checkersController'
		});
});

// create the controller and inject Angular's $scope
checkersApp.controller('menuController', function($scope) {
	javaOp.debug("TEST");
});

checkersApp.controller('hostController', function($scope) {
});

checkersApp.controller('clientController', function($scope) {
});

checkersApp.controller('checkerController', function($scope) {
});

function receieveMessage(string){
	JSON.parse(string);
}

function sendBackMessage(string){
	JavaOp.debug(string);
}