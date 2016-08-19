checkersApp.controller('menuController', ['$scope', '$state',
                                          function($scope, $state) {
	$("#host").click(function(){
		$state.go('/host');
	});
	$("#join").click(function(){
		$state.go('/client');
	});
}]);