app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider ) {
	$routeProvider
	.when('/redirectToHome',{
		redirectTo: '/'
    })
	.when('/', {
		controller : 'HomeController',
		templateUrl : 'app/components/home/homeView.html',
		resolve: {	   	    	
    		ordensServicoAtivas: ['OrdemServicoResource', function(OrdemServicoResource) {	 		
    			return OrdemServicoResource.ativas().$promise;
    		}]
		}
	})	
	.when('/cliente', {
		controller : 'ClienteController',
		templateUrl : 'app/components/cliente/listarCliente.html',
      	resolve: {	   	    	
    		cliente: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/cliente/:id', {
		controller : 'ClienteController',
		templateUrl : 'app/components/cliente/editarCliente.html',
      	resolve: {	   	    	
    		cliente: ['ClienteResource', '$route', function(ClienteResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ClienteResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
}]);