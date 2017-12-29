appController.controller('ProcessoController', ['$scope', '$upload', '$location', 'toaster', 'ProcessoResource', 'processo', 'situacoes', ProcessoController]);

function ProcessoController($scope, $upload, $location, toaster, ProcessoResource, processo, situacoes) {
    
	$scope.processo = {};
	$scope.situacoes = situacoes;
	
	$scope.openDataDistribuicaoInicio = function() {
        $scope.processo.dataDistribuicaoInicioOpened = true;   
    };
    
    $scope.openDataDistribuicaoFim = function() {
        $scope.processo.dataDistribuicaoFimOpened = true;   
    };
    
}