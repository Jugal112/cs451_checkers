checkersApp.controller('menuController', function($scope) {
	$("#host").click(function(){
		window.location.href = '#host'
	});
	$("#join").click(function(){
		window.location.href = '#client'
	});
});