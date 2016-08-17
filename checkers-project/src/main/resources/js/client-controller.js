checkersApp.controller('clientController', function($scope) {
	$(".client #button button").click(function(){
		javaOp.debug('Entered ip: ' + $(".client #ip input").val());
        var ret = javaOp.startClient($(".client #ip input").val());
		if(ret == 0) window.location.href = '#checkers';
	});
});

function hostContinue(){
	javaOp.debug("FUCKING BALLSACKS");
	window.location.href='#checkers';
}