appController.controller('ClienteController', ['$scope', '$location', 'ClienteResource', 'toaster', 'cliente', '$http', '$sce', '$window',
    function($scope, $location, ClienteResource, toaster, cliente, $http,  $sce, $window) {
        
        $scope.consultar = function(){
			ClienteResource.query({nome: $scope.cliente.nome, cpf: $scope.cliente.cpf}, function(result) {				
				$scope.clientes = result;
		    })
		};

        $scope.editar = function(cliente){
            $location.path('/cliente/'+cliente.id);
        };

        $scope.incluir = function(){
            $location.path('/cliente/0');
        };

        $scope.voltar = function(){
            $location.path('/cliente');
        };

        $scope.fechar = function(){
            $window.close();
        };

        $scope.salvar = function () {
            var id = ClienteResource.save($scope.cliente,
                function(data) {
            		$scope.cliente.id = data.id;
                    toaster.pop('success', null, 'Cliente salvo com sucesso');
                }
            )
        };

        $scope.buscarCEP = function(cep){
            var url = 'https://viacep.com.br/ws/'+cep.replace('-', '')+'/json?callback=JSON_CALLBACK';
            $sce.trustAsResourceUrl(url);
            $http.jsonp(url, {jsonpCallbackParam: 'callback'})
            .success(function(data){
                if(data.erro){
                    toaster.pop('info', null, 'CEP não encontrado');
                }else{
                    $scope.cliente.bairro = data.bairro;
                    $scope.cliente.logradouro = data.logradouro;                
                    $scope.cliente.cidade = data.localidade;                
                    $scope.cliente.uf = data.uf;                
                }
            }).error(function(data, status, headers, config) {
                toaster.pop('error', null, 'Erro ao buscar o CEP. Preencha manualmente as informações');
            });
        }

        if(cliente){
            $scope.cliente = cliente;
        }
	}
]);