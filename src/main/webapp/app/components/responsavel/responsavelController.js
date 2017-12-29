appController.controller('ResponsavelController', ['$scope', '$location', 'toaster', 'ResponsavelResource', 'responsavel', ResponsavelController]);

function ResponsavelController($scope, $location, toaster, ResponsavelResource, responsavel) {
    
    if(responsavel){
        $scope.responsavel = responsavel;
    }
    
    $scope.consultar = function(){
    	ResponsavelResource.query({nome: $scope.responsavel.nome, cpf: $scope.responsavel.cpf, numeroProcessoUnificado: $scope.responsavel.numeroProcessoUnificado}, function(result) {
			$scope.responsaveis = result;
	    })
	};
	
    $scope.editar = function(responsavel){
        $location.path('/responsavel/'+responsavel.id);
    };


    $scope.cadastrar = function(responsavel){
        $location.path('/responsavel/0');
    };

    $scope.voltar = function(responsavel){
        $location.path('/responsavel');
    };
    
    $scope.excluir = function (responsavel) {
    	$scope.confirm('Excluir Responsável', 'Deseja excluir o responsável ' + responsavel.nome + '?', 'Não', 'Sim', function(){$scope.confirmacaoExclusao(responsavel)});
    };
    
    $scope.confirmacaoExclusao = function (responsavel) {
    	ResponsavelResource.remove({id: responsavel.id},
            function(data) {
                toaster.pop('success', null, 'Responsável excluído com sucesso');
                $scope.consultar();
            }
        )      	
    }

    $scope.salvar = function () {
        ResponsavelResource.save($scope.responsavel,
            function(data) {
       			toaster.pop('success', null, 'Responsável salvo com sucesso');
        		$scope.responsavel.id = data.id;
            }
        )
    };
}