appController.controller('ProcessoController', ['$scope', '$upload', '$location', 'toaster', 'ProcessoResource', 'ResponsavelResource', 'processo', 'situacoes', 'processosVinculados', 'responsaveis', ProcessoController]);

function ProcessoController($scope, $upload, $location, toaster, ProcessoResource, ResponsavelResource, processo, situacoes, processosVinculados, responsaveis) {
    
	$scope.situacoes = situacoes;
	
	if(processo){
        $scope.processo = processo;
        $scope.isSomenteLeitura = $scope.processo.situacao.nome == 'Finalizado';
        $scope.processosVinculados = processosVinculados;
        $scope.processo.responsaveisArray = responsaveis;
        /*angular.forEach($scope.processo.responsaveis, function(processoResponsavel, index) {
        	processoResponsavel.responsavel.idAssociacao = processoResponsavel.id;
        	$scope.processo.responsaveisArray.push(processoResponsavel.responsavel);               
        });*/
        if($scope.processo.dataDistribuicao){
        	$scope.processo.dataDistribuicao = new Date($scope.processo.dataDistribuicao);                    
        }
    }else{
    	$scope.processo = {};
    	$scope.processo.responsaveisArray = [];
    }
	
    $scope.buscarProcessos = function(numeroProcessoUnificado){
    	ProcessoResource.query({numeroProcessoUnificado: numeroProcessoUnificado}, function(result) {
			$scope.processos = result;
	    })
	};
	
    $scope.consultar = function(){
    	ProcessoResource.query({
	    			numeroProcessoUnificado: $scope.processo.numeroProcessoUnificado,
	    			dataDistribuicaoInicio: $scope.processo.dataDistribuicaoInicio,
	    			dataDistribuicaoFim: $scope.processo.dataDistribuicaoFim,
	    			idSituacao: $scope.processo.situacao ? $scope.processo.situacao.id : null,
	    			segredoJustica: $scope.processo.segredoJustica,
	    			pastaFisicaCliente: $scope.processo.pastaFisicaCliente,
	    			idResponsavel: $scope.processo.responsavel ? $scope.processo.responsavel.id : null
	    		}, function(result) {
			$scope.processos = result;
	    })
	};
	
    $scope.editar = function(processo){
        $location.path('/processo/'+processo.id);
    };
	
	$scope.openDataDistribuicaoInicio = function() {
        $scope.dataDistribuicaoInicioOpened = true;   
    };
    
    $scope.openDataDistribuicaoFim = function() {
        $scope.dataDistribuicaoFimOpened = true;   
    };
    
    $scope.cadastrar = function(responsavel){
        $location.path('/processo/0');
    };
    
    $scope.voltar = function(responsavel){
        $location.path('/processo');
    };
    
    $scope.openDataDistribuicao = function() {
        $scope.dataDistribuicaoOpened = true;   
    };
    
    $scope.optionsDataDistribuicao = {
    	maxDate: new Date()
    }
    
    $scope.buscarResponsaveis = function(nomeEmailCpfLike) {
    	ResponsavelResource.query({
            nomeEmailCpfLike : encodeURIComponent(nomeEmailCpfLike)
        }, function(data) {
            $scope.responsaveis = data;
        });
    };
    
    $scope.salvar = function () {
    	$scope.processo.responsaveis = [];
    	angular.forEach($scope.processo.responsaveisArray, function(responsavel, index) {
    		$scope.processo.responsaveis.push({id: responsavel.idAssociacao, 'responsavel': responsavel});               
        });
    	ProcessoResource.save($scope.processo,
            function(data) {
                toaster.pop('success', null, 'Processo salvo com sucesso');
                $scope.processo.id = data.id;
                responsaveisTmp = [];
                angular.forEach(data.responsaveis, function(processoResponsavel, index) {
                	processoResponsavel.responsavel.idAssociacao = processoResponsavel.id;
                	responsaveisTmp.push(processoResponsavel.responsavel);               
                });
                $scope.processo.responsaveisArray = responsaveisTmp;
            }
        ) 
    }
    
    $scope.excluir = function (processo) {
    	$scope.confirm('Excluir Processo', 'Deseja excluir o processo ' + processo.numeroProcessoUnificado + '?', 'Não', 'Sim', function(){$scope.confirmacaoExclusao(processo)});
    };
    
    $scope.confirmacaoExclusao = function (processo) {
    	ProcessoResource.remove({id: processo.id},
            function(data) {
                toaster.pop('success', null, 'Processo excluído com sucesso');
                $scope.consultar();
            }
        )      	
    }
}