appController.controller('EquipamentoController', ['$scope', '$location', 'EquipamentoResource', 'toaster', 'equipamento', '$window',
    function($scope, $location, EquipamentoResource, toaster, equipamento, $window) {
        
        $scope.consultar = function(){
        	EquipamentoResource.query({nome: $scope.equipamento.nome}, function(result) {				
				$scope.equipamentos = result;
		    })
		};

        $scope.editar = function(equipamento){
            $location.path('/equipamento/'+equipamento.id);
        };

        $scope.incluir = function(){
            $location.path('/equipamento/0');
        };

        $scope.voltar = function(){
            $location.path('/equipamento');
        };

        $scope.fechar = function(){
            $window.close();
        };

        $scope.salvar = function () {
            var id = EquipamentoResource.save($scope.equipamento,
                function(data) {
            		$scope.equipamento.id = data.id;
                    toaster.pop('success', null, 'Equipamento salvo com sucesso');
                }
            )
        };

        if(equipamento){
            $scope.equipamento = equipamento;
        }
	}
]);