appController.controller('EmailMarketingController', ['$rootScope', '$scope', '$location', 'toaster', 'EmailMarketingResource', 
    function($rootScope, $scope, $location, toaster, EmailMarketingResource) {

		$scope.voltar = function () {
	        $location.path('/emailMarketing');
	    };
	    
        $scope.enviar = function () {
            $scope.confirm('Envio de email', 'Deseja confirmar o envio do email?', 'Não', 'Sim', $scope.confirmacaoEnvioEmail);
        };
        
        $scope.confirmacaoEnvioEmail = function () {
        	EmailMarketingResource.enviar($scope.emailMarketing, 
                function(data) {
                    toaster.pop('success', null, 'Email enviado com sucesso');
                }
            );       	
        }

        $scope.incluirDestinatario = function () {          
            $scope.emailMarketing.emailDestinatarios.push(' ');
        };

        $scope.excluirDestinatario = function () {
            $scope.emailMarketing.emailDestinatarios.splice(this.$index, 1);
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

        $scope.emailMarketing = {};
        $scope.emailMarketing.emailDestinatarios = [];        
	}
]);