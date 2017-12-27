appController.controller('UsuarioController', ['$scope', '$location', 'UsuarioResource', 'toaster', 'usuario', 'perfisUsuario', 'perfis',
    function($scope, $location, UsuarioResource, toaster, usuario, perfisUsuario, perfis) {
        
        $scope.consultar = function(){
        	UsuarioResource.query({nome: $scope.usuario.nome, login: $scope.usuario.login}, function(result) {				
				$scope.usuarios = result;
		    })
		};

        $scope.editar = function(usuario){
            $location.path('/usuario/'+usuario.id);
        };

        $scope.incluir = function(){
            $location.path('/usuario/0');
        };

        $scope.voltar = function(){
            $location.path('/usuario');
        };

        $scope.getUsuarioPerfil = function () {
            usuarioPerfil = [];            
            angular.forEach($scope.usuario.perfis, function(perfil, index) {
                usuarioPerfil.push({usuario: {id: $scope.usuario.id}, perfil: perfil});               
            });
            return usuarioPerfil;
        };

        $scope.salvar = function () {
            $scope.usuario.usuarioPerfil = $scope.getUsuarioPerfil();
            var id = UsuarioResource.save($scope.usuario,
                function(data) {
            		$scope.usuario.id = data.id;
                    toaster.pop('success', null, 'Usuário salvo com sucesso');
                }
            )
        };
        
        $scope.perfis = perfis;
        
        if(usuario){
            $scope.usuario = usuario;
            $scope.usuario.perfis = perfisUsuario;
        }else{
            $scope.usuario = {};
            $scope.usuario.statusRegistro = 'A';
        }
	}
]);