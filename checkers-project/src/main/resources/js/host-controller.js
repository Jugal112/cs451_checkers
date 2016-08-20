checkersApp.controller('hostController', function($scope) {
	$scope.address = "IP: " + javaOp.getIPAddress();
	javaOp.startHost();
});

function hostContinue(){
	javaOp.debug("Succesful Connection");
	networkingRole = Roles.HOST;
	angular.element('#main').scope().changeState('/checkers');
}