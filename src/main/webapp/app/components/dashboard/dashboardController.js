appController.controller('DashBoardController', ['$scope', '$filter', 'DashBoardResource', 'numeroOrdensServicoAtendidas', 'ordensServicoPorEquipamento', 'ordensServicoPorMesAno', 'faturamentoMesAno',
    function($scope, $filter, DashBoardResource, numeroOrdensServicoAtendidas, ordensServicoPorEquipamento, ordensServicoPorMesAno, faturamentoMesAno) {
    	$scope.numeroOrdensServicoAtendidas = numeroOrdensServicoAtendidas.valor;
    	$scope.ordensServicoPorEquipamento = ordensServicoPorEquipamento;
    	$scope.ordensServicoPorMesAno = ordensServicoPorMesAno;
        $scope.faturamentoMesAno = faturamentoMesAno;

        $scope.buscarOrdensServicoAbertasMesAno = function () {
        	DashBoardResource.ordensServicoPorMesAno({ano: $scope.dashboard.anoOrdensServicoAbertasMesAno}, 
                function(data) {
                    $scope.ordensServicoPorMesAno = data;
                }
            );       	
        }

        $scope.buscarFaturamentoMesAno = function () {
            DashBoardResource.faturamentoMesAno({ano: $scope.dashboard.anoFaturamentoMesAno}, 
                function(data) {
                    $scope.faturamentoMesAno = data;
                }
            );          
        }

        $scope.formatarMonetario = function (index, options, content) {
            var data = options.data[index];
            return "<div class='morris-hover-row-label'>"+data.label+"</div><div class='morris-hover-point' style='color: #0b62a4'>Total:&nbsp;"+$filter('currency')(data.value, 'R$')+"</div>";
        }

        var anoAtual = new Date().getFullYear();
        $scope.dashboard = {};
        $scope.dashboard.anoOrdensServicoAbertasMesAno = String(anoAtual);
        $scope.dashboard.anoFaturamentoMesAno = String(anoAtual);
        $scope.dashboard.anos = [];
        $scope.dashboard.anos.push(anoAtual);
        $scope.dashboard.anos.push(anoAtual-1);
        $scope.dashboard.anos.push(anoAtual-2);
	}
]);