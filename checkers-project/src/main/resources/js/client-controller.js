checkersApp.controller('clientController', function($scope) {
	$(".client #button button").click(function(){
		javaOp.debug('Entered ip: ' + $(".client #ip input").val());
        var ret = javaOp.startClient($(".client #ip input").val());
		if(ret == 0){
			networkingRole = Roles.CLIENT;
			window.location.href = '#checkers';
		}
	});
});