app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider ) {
	$routeProvider
	.when('/redirectToHome',{
		redirectTo: '/'
    })
	.when('/', {
		controller : 'HomeController',
		templateUrl : 'app/components/home/homeView.html'
	})	
	.when('/responsavel', {
		controller : 'ResponsavelController',
		templateUrl : 'app/components/responsavel/responsavelListar.html',
      	resolve: {	   	    	
      		responsavel: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/responsavel/:id', {
		controller : 'ResponsavelController',
		templateUrl : 'app/components/responsavel/responsavelEditar.html',
      	resolve: {	   	    	
      		responsavel: ['ResponsavelResource', '$route', function(ResponsavelResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ResponsavelResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
}]);