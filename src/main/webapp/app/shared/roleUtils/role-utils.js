'use strict';
angular.module('role-utils', [])

.directive('rolesUsuario', function () {
    return {
        restrict: 'A',
        link: function (scope, elem, attr, ctrl) {    
        	var permitirExibicao = false;    	
        	if(scope.auth && !scope.auth.isAdmin){
        		angular.forEach(scope.auth.perfis, function(objeto, index) {
	                if(attr.rolesUsuario.indexOf(objeto.nome) >= 0){
	                    permitirExibicao = true;
	                }
            	});
            	if(!permitirExibicao){
            		elem.hide();
            	}        		
        	}            
        }
    };
});