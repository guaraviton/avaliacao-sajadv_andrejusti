appController.controller('LoginController', ['$scope', 'AuthService', LoginController]);

function LoginController ($scope, AuthService) {
	$scope.login = {};
	
	if(localStorage.login_pcmaker && localStorage.senha_pcmaker){
		$scope.login.chave = localStorage.login_pcmaker;
		$scope.login.senha = localStorage.senha_pcmaker;
		$scope.login.lembrarUsuarioSenha = true;
	}

	$scope.entrar = function () {
		if($scope.login.lembrarUsuarioSenha){
			localStorage.setItem('login_pcmaker', $scope.login.chave);
			localStorage.setItem('senha_pcmaker', $scope.login.senha);
		}else{
			localStorage.removeItem('login_pcmaker');
			localStorage.removeItem('login_pcmaker');
		}
		AuthService.login($scope.login);
    };
};