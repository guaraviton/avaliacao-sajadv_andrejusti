// Intercepting HTTP calls with AngularJS.
angular.module('app')
    .config(['$provide', '$httpProvider', function($provide, $httpProvider) {

        // Intercept http calls.
        $provide.factory('interceptor',

            // dependencies injection
            ['$q', '$rootScope', '$window', 'toaster', '$injector', '$location','APP_CONFIG',

                function($q, $rootScope, $window, toaster, $injector, $location,APP_CONFIG) {

                    function popErrorMessage(rejection) {
                    	if(typeof rejection.data === 'string')
                    	{
                    		toaster.pop('error', 'Erro ao realizar a sua requisição', rejection.data);
                    	}else{
                    		toaster.pop('error', 'Erro ao realizar a sua requisição', 'Favor contactar o administrador do sistema.');
                    	}
                        
                    }

                    return {                        
                        request: function(config) {     
                        	config.headers[APP_CONFIG.TOKEN_HEADER_NAME] = $rootScope.token;
                            return config || $q.when(config);
                        },

                        // On request failure
                        requestError: function(rejection) {     
                            return $q.reject(rejection);
                        },

                        // On response success
                        response: function(response) {                                   
                            if (response.data.mensagemRetorno){
                            	toaster.pop(response.data.mensagemRetorno.tipo, response.data.mensagemRetorno.titulo,response.data.mensagemRetorno.mensagem);	
                            }
                            
                            return response || $q.when(response);
                        },

                        // On response failture
                        responseError: function(rejection) {         
                            switch (rejection.status) {
                                case 401:
                                    if (rejection.config.data && rejection.config.data.isLogin) {
                                    	toaster.pop('error', null, rejection.data.errors[0].message);
                                    } else {             
                                    	$rootScope.token = null;
                            			$rootScope.auth = null;
                            			$rootScope.menu = null;
                                    	$location.path('/redirectToLogin');                                                                                        
                                    }
                                    break;
                                case 403:
                                    if (rejection.data && rejection.data.mensagem) {
                                    	toaster.pop('error', null, rejection.data.errors[0].message);
                                    } else {
                                    	popErrorMessage(rejection);
                                    }
                                    break;
                                case 400:
                                    if (rejection.data && rejection.data.errors) {
                                        for (var i = 0; i < rejection.data.errors.length; i++) {
                                        	error = rejection.data.errors[i];
                                        	toaster.pop('error', null, error.message);
                                        }
                                    } else {
                                    	popErrorMessage(rejection);
                                    }
                                    break;
                                case 500:
                                	toaster.pop('error', 'Erro ao realizar a sua requisição', 'Favor contactar o administrador do sistema. Código do erro: 500');
                                    break;
                                case 503:
                                	toaster.pop('error', 'Erro ao realizar a sua requisição', 'Favor contactar o administrador do sistema. Código do erro: 503');
                                    break;
                                case 404:
                                	toaster.pop('error', 'Erro ao realizar a sua requisição', 'Favor contactar o administrador do sistema. Código do erro: 404'); 
                                    break;                                    

                                default:
                                	popErrorMessage(rejection);
                            }
                            return $q.reject(rejection);
                        }
                    };
                }
            ]);

        $httpProvider.interceptors.push('interceptor');
    }]);