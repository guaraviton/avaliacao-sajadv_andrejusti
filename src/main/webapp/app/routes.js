app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider ) {
	$routeProvider
	.when('/redirectToHome',{
		redirectTo: '/'
    })
	.when('/redirectToLogin',{
		templateUrl : 'app/components/login/loginView.html',
		controller : 'LoginController',
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
	.when('/usuario', {
		controller : 'UsuarioController',
		templateUrl : 'app/components/usuario/listarUsuario.html',
      	resolve: {	   	    	
    		usuario: function() {	 	    		
       			return null;
    		},
    		perfisUsuario: function() {	 	    		
       			return null;
    		},
    		perfis: function() {	 	    		
       			return null;
    		}
		}
	})	
	.when('/usuario/:id', {
		controller : 'UsuarioController',
		templateUrl : 'app/components/usuario/editarUsuario.html',
      	resolve: {	   	    	
    		usuario: ['UsuarioResource', '$route', function(UsuarioResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return UsuarioResource.get({id: $route.current.params.id}).$promise;
    		}],
	    	perfisUsuario: ['UsuarioResource', '$route', function(UsuarioResource, $route) {	 
	    		if($route.current.params.id == 0){
	    			return;
	    		}		    
	       		return UsuarioResource.perfis({id: $route.current.params.id}).$promise;
	    	}],
	    	perfis: ['PerfilResource', function(PerfilResource) {	 	    			    
	       		return PerfilResource.query().$promise;
	    	}]
		}
	})
	.when('/emailMarketing', {
		controller : 'EmailMarketingController',
		templateUrl : 'app/components/emailMarketing/emailMarketing.html'
	})
	.when('/perfil', {
		controller : 'PerfilController',
		templateUrl : 'app/components/perfil/listarPerfil.html',
      	resolve: {	   	    	
    		perfil: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/perfil/:id', {
		controller : 'PerfilController',
		templateUrl : 'app/components/perfil/editarPerfil.html',
      	resolve: {	   	    	
    		perfil: ['PerfilResource', '$route', function(PerfilResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return PerfilResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
	.when('/equipamento', {
		controller : 'EquipamentoController',
		templateUrl : 'app/components/equipamento/listarEquipamento.html',
      	resolve: {	   	    	
    		equipamento: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/equipamento/:id', {
		controller : 'EquipamentoController',
		templateUrl : 'app/components/equipamento/editarEquipamento.html',
      	resolve: {	   	    	
      		equipamento: ['EquipamentoResource', '$route', function(EquipamentoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return EquipamentoResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
	.when('/avaria', {
		controller : 'AvariaController',
		templateUrl : 'app/components/avaria/listarAvaria.html',
      	resolve: {	   	    	
      		avaria: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/avaria/:id', {
		controller : 'AvariaController',
		templateUrl : 'app/components/avaria/editarAvaria.html',
      	resolve: {	   	    	
      		avaria: ['AvariaResource', '$route', function(AvariaResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return AvariaResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
	.when('/acessorio', {
		controller : 'AcessorioController',
		templateUrl : 'app/components/acessorio/listarAcessorio.html',
      	resolve: {	   	    	
      		acessorio: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/acessorio/:id', {
		controller : 'AcessorioController',
		templateUrl : 'app/components/acessorio/editarAcessorio.html',
      	resolve: {	   	    	
      		acessorio: ['AcessorioResource', '$route', function(AcessorioResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return AcessorioResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})	
	.when('/ordemServico', {
		controller : 'OrdemServicoController',
		templateUrl : 'app/components/ordemServico/listarOrdemServico.html',
      	resolve: {	
      		ordemServico: function() {	 	    		
       			return null;
    		},
    		ordemServicoAcessorios: function() {	 	    		
       			return null;
    		},
    		ordemServicoAvarias: function() {	 	    		
       			return null;
    		},
      		acessorios: function() {	 	    		
       			return null;
    		},
      		avarias: function() {	 	    		
       			return null;
    		},
      		acompanhamentos: function() {	 	    		
       			return null;
    		}
		}
	})	
	.when('/ordemServico/:id', {
		controller : 'OrdemServicoController',
		templateUrl : 'app/components/ordemServico/editarOrdemServico.html',
		resolve: {	
			ordemServico: ['OrdemServicoResource', '$route', function(OrdemServicoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return OrdemServicoResource.get({id: $route.current.params.id}).$promise;
    		}],
    		ordemServicoAcessorios: ['OrdemServicoResource', '$route', function(OrdemServicoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return OrdemServicoResource.acessorios({id: $route.current.params.id}).$promise;
    		}],
    		ordemServicoAvarias: ['OrdemServicoResource', '$route', function(OrdemServicoResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return OrdemServicoResource.avarias({id: $route.current.params.id}).$promise;
    		}],
    		acessorios: ['AcessorioResource', '$route', function(AcessorioResource, $route) {	 
	    		return AcessorioResource.query().$promise;
    		}],
	    	avarias: ['AvariaResource', '$route', function(AvariaResource, $route) {	 
	    		return AvariaResource.query().$promise;
	    	}],
	    	acompanhamentos: ['OrdemServicoResource', '$route', function(OrdemServicoResource, $route) {	 
	    		return OrdemServicoResource.acompanhamentos({id: $route.current.params.id}).$promise;
	    	}]
		}
	})
	.when('/ordensServicoAtivas', {
		controller : 'OrdensServicoAtivasController',
		templateUrl : 'app/components/ordensServicoAtivas/listarOrdensServicoAtivas.html',
      	resolve: {	   	    	
      		ordensServicoAtivas: ['OrdemServicoResource', function(OrdemServicoResource) {	 	    			    
       			return OrdemServicoResource.ativas().$promise;
    		}]    	
		}
	})
	.when('/configuracaoSistema', {
		controller : 'ConfiguracaoSistemaController',
		templateUrl : 'app/components/configuracaoSistema/listarConfiguracaoSistema.html',
      	resolve: {	   	    	
      		configuracaoSistema: function() {	 	    		
       			return null;
    		}   	
		}
	})	
	.when('/configuracaoSistema/:id', {
		controller : 'ConfiguracaoSistemaController',
		templateUrl : 'app/components/configuracaoSistema/editarConfiguracaoSistema.html',
      	resolve: {	   	    	
      		configuracaoSistema: ['ConfiguracaoSistemaResource', '$route', function(ConfiguracaoSistemaResource, $route) {	 
	    		if($route.current.params.id == 0){
    				return;
    			}		    
       			return ConfiguracaoSistemaResource.get({id: $route.current.params.id}).$promise;
    		}]    	
		}
	})
	.when('/dashboard', {
		controller : 'DashBoardController',
		templateUrl : 'app/components/dashboard/dashboard.html',
		resolve: {	   	    	
      		numeroOrdensServicoAtendidas: ['DashBoardResource', function(DashBoardResource) {	 	    			    
       			return DashBoardResource.numeroOrdensServicoAtendidas().$promise;
    		}],
    		ordensServicoPorEquipamento: ['DashBoardResource', function(DashBoardResource) {	 
	    		return DashBoardResource.ordensServicoPorEquipamento().$promise;
	    	}],
	    	ordensServicoPorMesAno: ['DashBoardResource', function(DashBoardResource) {	 
	    		return DashBoardResource.ordensServicoPorMesAno({ano: new Date().getFullYear()}).$promise;
	    	}],
	    	faturamentoMesAno: ['DashBoardResource', function(DashBoardResource) {	 
	    		return DashBoardResource.faturamentoMesAno({ano: new Date().getFullYear()}).$promise;
	    	}] 
		}
	})
}]);