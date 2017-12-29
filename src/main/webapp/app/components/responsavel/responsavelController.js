appController.controller('ResponsavelController', ['$scope', '$upload', '$location', 'toaster', 'DTOptionsBuilder', 'ResponsavelResource', 'responsavel', ResponsavelController]);

function ResponsavelController($scope, $upload, $location, toaster, DTOptionsBuilder, ResponsavelResource, responsavel) {
    
	$scope.dtOptions = DTOptionsBuilder.newOptions()
		.withOption('bFilter', false)
		.withOption('paging', false)
		.withOption('bSort', false)
		.withOption('bInfo', false)
		.withOption('searching', false);
	
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
        $upload.upload({
            url: 'api/responsavel',                
            data: $scope.responsavel,                             
            file: $scope.imagem ? $scope.imagem[0] : null            
        })
        .success(function (data, status, headers, config) {
            toaster.pop('success', null, 'Responsável salvo com sucesso');
            $scope.responsavel.id = data.id;
            if($scope.imagem){
            	$scope.imagem = null;	
            	angular.element('#fotoResponsavel').attr('src', 'http://localhost:8080/gestaodeprocessos/api/responsavel/'+$scope.responsavel.id+'/foto?'+Date.now());
            	$scope.responsavel.hasFoto = true;
            }
        });
    }
}