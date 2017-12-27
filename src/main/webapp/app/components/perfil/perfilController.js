appController.controller('PerfilController', ['$scope', '$location', 'PerfilResource', 'toaster', 'perfil',
    function($scope, $location, PerfilResource, toaster, perfil) {
        
        $scope.consultar = function(){
        	PerfilResource.query({nome: $scope.perfil.nome}, function(result) {				
				$scope.perfis = result;
		    })
		};

        $scope.editar = function(cliente){
            $location.path('/perfil/'+cliente.id);
        };

        $scope.incluir = function(){
            $location.path('/perfil/0');
        };

        $scope.voltar = function(){
            $location.path('/perfil');
        };

        $scope.salvar = function () {
            var id = PerfilResource.save($scope.perfil,
                function(data) {
            		$scope.perfil.id = data.id;
                    toaster.pop('success', null, 'Perfil salvo com sucesso');
                }
            )
        };

        if(perfil){
            $scope.perfil = perfil;
        }
	}
]);