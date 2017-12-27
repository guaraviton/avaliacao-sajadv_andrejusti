appServices.factory('AuthService', ['$rootScope', '$location', 'AuthResource', 'UsuarioResource', '$interval', 'APP_CONFIG', 'toaster', '$filter', AuthService]);

function AuthService($rootScope, $location, AuthResource, UsuarioResource, $interval, APP_CONFIG, toaster, $filter) {
	var timer;
	var service = {
		login : function(usuario) {
			AuthResource.login(usuario, 
				function(result) {
					service.setToken(result);
					AuthResource.get(function(result) {
						service.setUsuarioRootScope(result);
						service.loginRedirect();
						service.tokenRefresh();
					});	
				},									
				function(result) {
					toaster.pop('error', null, 'Usuário ou senha inválidos');
				}
			);
		},
		tokenRefresh : function() {
			$interval(function() {
				AuthResource.refresh(function(result) {
					console.log('Token Refresh');
					service.setToken(result);				
				});
			}, APP_CONFIG.TOKEN_TIME_REFRESH);
		},
		isValid : function() {
			if($rootScope.token){
				return true;
			}
			if(typeof(Storage) !== "undefined" && sessionStorage.token){
				$rootScope.token = sessionStorage.token;
				AuthResource.get(function(result) {
					service.setUsuarioRootScope(result);
					service.tokenRefresh();
				});
				return true;
			}
			return false;
		},
		setToken : function(result) {
			sessionStorage.setItem('token', result.token);
    		$rootScope.token = result.token;
		},
		setUsuarioRootScope : function(result) {
    		$rootScope.auth = result.usuario;
    		if(result.perfisString){
    			$rootScope.auth.isAdmin = $filter('filter')(result.perfisString, 'admin').length > 0;
    			$rootScope.auth.perfis = result.perfis;
    		}    		
		},
		loginRedirect : function() {
			$location.path('/');
		},
		logout : function() {
			service.resetAndRedirect();	
		},
		reset : function() {
			sessionStorage.removeItem('token');
			$rootScope.token = null;
			$rootScope.auth = null;
		},
		resetAndRedirect : function() {
			service.reset();
			$location.path('/redirectToLogin');			
		}
	}
	return service;
};