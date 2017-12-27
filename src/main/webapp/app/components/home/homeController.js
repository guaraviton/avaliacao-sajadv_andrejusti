appController.controller('HomeController', ['$rootScope', '$scope', 'ordensServicoAtivas', '$filter', HomeController]);

function HomeController ($rootScope, $scope, ordensServicoAtivas, $filter) {
    $scope.title = "HomeController";    
    $rootScope.ordensServicoAtivas = ordensServicoAtivas;       
};