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
    		},
    		processos: function() {	 	    		
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
    		}],
    		processos: ['ResponsavelResource', '$route', function(ResponsavelResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ResponsavelResource.processos({id: $route.current.params.id}).$promise;
    		}] 
		}
	})
	.when('/processo', {
		controller : 'ProcessoController',
		templateUrl : 'app/components/processo/processoListar.html',
      	resolve: {	   	    	
      		processo: function() {	 	    		
       			return null;
    		},
    		situacoes: ['SituacaoResource', function(SituacaoResource) {	 	    			   
	       		return SituacaoResource.query().$promise;
	    	}],
	    	processosVinculados: function() {	 	    		
       			return null;
    		}
		}
	})	
	.when('/processo/:id', {
		controller : 'ProcessoController',
		templateUrl : 'app/components/processo/processoEditar.html',
      	resolve: {	   	    	
      		processo: ['ProcessoResource', '$route', function(ProcessoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ProcessoResource.get({id: $route.current.params.id}).$promise;
    		}],
    		situacoes: ['SituacaoResource', function(SituacaoResource) {	 	    			   
	       		return SituacaoResource.query().$promise;
	    	}],
	    	processosVinculados: ['ProcessoResource', '$route', function(ProcessoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ProcessoResource.vinculados({id: $route.current.params.id}).$promise;
    		}],
		}
	})
}]);