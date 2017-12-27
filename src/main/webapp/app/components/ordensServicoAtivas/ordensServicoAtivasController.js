appController.controller('OrdensServicoAtivasController', ['$rootScope', '$scope', '$filter', '$location', 'OrdemServicoResource', '$interval', 'APP_CONFIG', 'ordensServicoAtivas',
    function($rootScope, $scope, $filter, $location, OrdemServicoResource, $interval, APP_CONFIG, ordensServicoAtivas) {
    	$scope.abrirOrdemServico = function(id){
            $location.path('/ordemServico/'+id);
        };
        
    	if(!$rootScope.timerOrdensAtivasConfigurada){
            $rootScope.timerOrdensAtivasConfigurada = true;
	        $interval(function() {
				OrdemServicoResource.ativas(function(ordensServicoAtivas) {	
					$rootScope.ordensServicoAtivas = ordensServicoAtivas;	    			
			    });
			}, APP_CONFIG.ORDENS_SERVICO_ATIVAS_TIME_REFRESH);
    	}

        $rootScope.ordensServicoAtivas = ordensServicoAtivas;
	}
]);


app.filter('filtrarOrdemServico', function () {
  return function (items, idEtapa) {
    var filtered = [];
    if(items){
        for (var i = 0; i < items.length; i++) {
            if (items[i].idEtapa == idEtapa) {        
                filtered.push(items[i]);
            }
        }
    }   
    return filtered;
  };
});