appController.controller('AvariaController', ['$scope', '$location', 'AvariaResource', 'toaster', 'avaria',
    function($scope, $location, AvariaResource, toaster, avaria) {
        
        $scope.consultar = function(){
        	AvariaResource.query({nome: $scope.avaria.nome}, function(result) {				
				$scope.avarias = result;
		    })
		};

        $scope.editar = function(avaria){
            $location.path('/avaria/'+avaria.id);
        };

        $scope.incluir = function(){
            $location.path('/avaria/0');
        };

        $scope.voltar = function(){
            $location.path('/avaria');
        };

        $scope.salvar = function () {
            var id = AvariaResource.save($scope.avaria,
                function(data) {
            		$scope.avaria.id = data.id;
                    toaster.pop('success', null, 'Avaria salva com sucesso');
                }
            )
        };

        if(avaria){
            $scope.avaria = avaria;
        }
	}
]);