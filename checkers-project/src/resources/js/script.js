var checkersApp = angular.module('checkersApp', ['ngRoute']);

// configure routes
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

// angular controllers
checkersApp.controller('menuController', function($scope) {
	//javaOp.debug("Entered Main Menu");
});

checkersApp.controller('hostController', function($scope) {
	//javaOp.debug("Entered Host");
	$scope.address = "IP: " + javaOp.getIPAddress();
	$(".host #button button").click(function(){
		javaOp.startHost();
	});

});

checkersApp.controller('clientController', function($scope) {
	//javaOp.debug("Entered Client!");
	
	$(".client #button button").click(function(){
		javaOp.debug('Entered ip: ' + $(".client #ip input").val());
                javaOp.startClient($(".client #ip input").val());
		window.location.href = '#checkers';
	});
});

checkersApp.controller('checkersController', function($scope) {
	//javaOp.debug("Entered Checkers!");
	
	var pw = $(".pane").height();

	$('.pane').css({
	    'width': pw + 'px'
	});
	
	//create board
	var board = "";
	var alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g'];

	for(var i = 0; i < 8; i++){
		board += "<td>";
		for(var j = 0; j < 8; j++){
			var location = alphabet[i] + j.toString();
			
			if((i+j)%2 == 1){
				board += "<tr class='light' id='" + location + "'></tr>";
			}
			else{
				board += "<tr class='dark' id='" + location + "'></tr>";
			}
		}
		board += "</td>";
	}
});

// helper functions.  Should mainly, if only, be called by java side
function receieveMessage(string){
	JSON.parse(string);
}

function sendBackMessage(string){
	//javaOp.debug(string);
}

function exit(){
	//javaOp.exit();
}
