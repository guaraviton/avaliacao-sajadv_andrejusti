appController.controller('AcessorioController', ['$scope', '$location', 'AcessorioResource', 'toaster', 'acessorio',
    function($scope, $location, AcessorioResource, toaster, acessorio) {
        
        $scope.consultar = function(){
        	AcessorioResource.query({nome: $scope.acessorio.nome}, function(result) {				
				$scope.acessorios = result;
		    })
		};

        $scope.editar = function(acessorio){
            $location.path('/acessorio/'+acessorio.id);
        };

        $scope.incluir = function(){
            $location.path('/acessorio/0');
        };

        $scope.voltar = function(){
            $location.path('/acessorio');
        };

        $scope.salvar = function () {
            var id = AcessorioResource.save($scope.acessorio,
                function(data) {
            		$scope.acessorio.id = data.id;
                    toaster.pop('success', null, 'Acessório salvo com sucesso');
                }
            )
        };

        if(acessorio){
            $scope.acessorio = acessorio;
        }
	}
]);