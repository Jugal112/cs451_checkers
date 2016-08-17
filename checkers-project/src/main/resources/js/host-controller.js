checkersApp.controller('hostController', function($scope) {
	$scope.address = "IP: " + javaOp.getIPAddress();
	javaOp.startHost();
	$(".host #button button").click(function(){
		window.location.href='#checkers';
	});
});

function hostContinue(){
	javaOp.debug("Succesful Connection");
	window.location.href='#checkers';
}