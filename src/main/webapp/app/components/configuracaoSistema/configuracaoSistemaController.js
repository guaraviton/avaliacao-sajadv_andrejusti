appController.controller('ConfiguracaoSistemaController', ['$rootScope', '$scope', '$location', 'toaster', 'ConfiguracaoSistemaResource', 'configuracaoSistema', 
    function($rootScope, $scope, $location, toaster, ConfiguracaoSistemaResource, configuracaoSistema) {

	    $scope.consultar = function(){
	    	ConfiguracaoSistemaResource.query({chave: $scope.configuracaoSistema.chave}, function(result) {				
				$scope.configuracoesSistema = result;
		    })
		};
	
        $scope.editar = function(configuracaoSistema){
            $location.path('/configuracaoSistema/'+configuracaoSistema.id);
        };

        $scope.incluir = function(){
            $location.path('/configuracaoSistema/0');
        };

        $scope.voltar = function(){
            $location.path('/configuracaoSistema');
        };

        $scope.salvar = function () {
            var id = ConfiguracaoSistemaResource.save($scope.configuracaoSistema,
                function(data) {
            		$scope.configuracaoSistema.id = data.id;
                    toaster.pop('success', null, 'Configuração do sistema salvo com sucesso');
                }
            )
        };
        
        $scope.customMenu = [
            ['bold', 'italic', 'underline', 'strikethrough', 'subscript', 'superscript'],
            ['font'],
            ['font-size'],
            ['font-color', 'hilite-color'],
            ['remove-format'],
            ['ordered-list', 'unordered-list', 'outdent', 'indent'],
            ['left-justify', 'center-justify', 'right-justify'],
            ['link', 'image']
        ];

        $scope.configuracaoSistema = configuracaoSistema;
	}
]);