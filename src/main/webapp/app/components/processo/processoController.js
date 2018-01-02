appController.controller('ProcessoController', ['$scope', '$upload', '$location', 'toaster', 'ProcessoResource', 'ResponsavelResource', 'processo', 'situacoes', ProcessoController]);

function ProcessoController($scope, $upload, $location, toaster, ProcessoResource, ResponsavelResource, processo, situacoes) {
    
	$scope.processo = {};
	$scope.situacoes = situacoes;
	
	$scope.openDataDistribuicaoInicio = function() {
        $scope.processo.dataDistribuicaoInicioOpened = true;   
    };
    
    $scope.openDataDistribuicaoFim = function() {
        $scope.processo.dataDistribuicaoFimOpened = true;   
    };
    
    $scope.cadastrar = function(responsavel){
        $location.path('/processo/0');
    };
    
    $scope.voltar = function(responsavel){
        $location.path('/processo');
    };
    
    $scope.openDataDistribuicao = function() {
        $scope.processo.dataDistribuicaoOpened = true;   
    };
    
    $scope.buscarResponsaveis = function(nomeEmailCpfLike) {
    	ResponsavelResource.query({
            nomeEmailCpfLike : encodeURIComponent(nomeEmailCpfLike)
        }, function(data) {
            $scope.responsaveis = data;
        });
    };
    
    $scope.salvar = function () {
    	responsaveis = $scope.processo.responsaveis;
    	$scope.processo.responsaveis = [];
    	angular.forEach(responsaveis, function(responsavel, index) {
    		$scope.processo.responsaveis.push({responsavel: responsavel});               
        });
    	ProcessoResource.save($scope.processo,
            function(data) {
                toaster.pop('success', null, 'Processo salvo com sucesso');
            }
        ) 
        $scope.processo.responsaveis = responsaveis;
    }
}