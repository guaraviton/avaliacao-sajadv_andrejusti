appController.controller('MainController', ['$rootScope', '$scope','$location', 'ProgressConfig', MainController]);

function MainController ($rootScope, $scope, $location, ProgressConfig) {

    $scope.$on('$routeChangeSuccess', function(event, next, current) {        
    	if (current) {
    		console.log('Origem -> ' + current.$$route.controller + '(' + current.$$route.templateUrl + ')');
    	}
    	if (next) {
    		console.log('Destino -> ' + next.$$route.controller + '(' + next.$$route.templateUrl + ')');
    	}
  	});
    
    ProgressConfig.eventListeners();
    ProgressConfig.color('#398DD5');
    ProgressConfig.height('6px');
    
    $scope.go = function(path){
	  $location.path(path);
	};    
};