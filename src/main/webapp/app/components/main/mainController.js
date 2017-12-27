appController.controller('MainController', ['$rootScope', '$scope','$location', 'ProgressConfig','AuthService', '$anchorScroll','toaster', MainController]);

function MainController ($rootScope, $scope, $location, ProgressConfig, AuthService, $anchorScroll, toaster) {

    $scope.$on('$routeChangeSuccess', function(event, next, current) {        
    	if (current) {
            $rootScope.rotaAnterior = current.$$route.originalPath;
    		console.log('Origem -> ' + current.$$route.controller + '(' + current.$$route.templateUrl + ')');
    	}
    	if (next) {
            $scope.rota = next.$$route.controller;
            if(next.params && next.params.modal){
                $scope.modal = true;
            }            
    		console.log('Destino -> ' + next.$$route.controller + '(' + next.$$route.templateUrl + ')');
    	}
  	});
    
    $scope.$on('$routeChangeStart', function(event, current, prevRoute){              
    	if (!AuthService.isValid()) {
    		AuthService.resetAndRedirect();
        }
    });
    
    //--- @begin: loading progressbar config
    ProgressConfig.eventListeners();
    //progressConfig.color('#008542');
    ProgressConfig.color('#398DD5');
    //progressConfig.height('4px');
    ProgressConfig.height('6px');
    //--- @end: loading progressbar config  
    
    $scope.go = function(path){
	  $location.path(path);
	};    
	
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	} 
    
    $scope.logout = function(){
    	AuthService.logout();      
    }	
};
