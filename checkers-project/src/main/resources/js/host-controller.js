checkersApp.controller('hostController', function($scope) {
	$scope.address = "IP: " + javaOp.getIPAddress();
	javaOp.startHost();
});

function hostContinue(){
	javaOp.debug("Succesful Connection");
	networkingRole = Roles.HOST;
	window.location.href='#checkers';
}