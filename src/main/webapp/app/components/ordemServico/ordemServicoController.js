appController.controller('OrdemServicoController', ['$rootScope', '$scope', '$location', 'toaster', 'OrdemServicoResource', 'ClienteResource', 'EquipamentoResource', 'ordemServico', 'ordemServicoAcessorios', 'ordemServicoAvarias', 'acessorios', 'avarias', 'acompanhamentos', '$window','LISTA_ETAPAS_ORDEM_SERVICO',
    function($rootScope, $scope, $location, toaster, OrdemServicoResource, ClienteResource, EquipamentoResource, ordemServico, ordemServicoAcessorios, ordemServicoAvarias, acessorios, avarias, acompanhamentos, $window, LISTA_ETAPAS_ORDEM_SERVICO) {
        
        $scope.consultar = function(){
            OrdemServicoResource.query({numero: $scope.ordemServico.numero, idCliente: $scope.ordemServico.cliente ? $scope.ordemServico.cliente.id : null, idEquipamento: $scope.ordemServico.equipamento ? $scope.ordemServico.equipamento.id : null, idEtapa: $scope.ordemServico.etapa ? $scope.ordemServico.etapa.id : null}, function(result) {		             
                $scope.ordensServico = result;
            })
        };

        $scope.timeline = function(){
            if(!$scope.timelineOrdemServico){
                OrdemServicoResource.timeline({id: $scope.ordemServico.id}, function(result) {             
                    $scope.timelineOrdemServico = result;
                })
            }
        };

        $scope.editar = function(ordemServico){
            $location.path('/ordemServico/'+ordemServico.id);
        };

        $scope.incluir = function(){
            $location.path('/ordemServico/0');
        };

        $scope.voltar = function(){
            $location.path($rootScope.rotaAnterior);
        };

        $scope.salvar = function () {
            $scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
            OrdemServicoResource.save($scope.ordemServico,
                function(data) {
            		$scope.ordemServico.id = data.id;
            		$scope.ordemServico.numero  = data.numero;
            		$scope.ordemServico.versao = data.versao;
            		$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    $scope.ordemServico.etapaOrdemServicoInicial = data.etapaOrdemServicoInicial;
                    toaster.pop('success', null, 'Ordem de Serviço salva com sucesso');
                }
            )
        };

        $scope.enviarAcompanhamento = function () {
            OrdemServicoResource.acompanhamento({id: $scope.ordemServico.id, texto: $scope.ordemServico.acompanhamento},
                function(data) {
                    toaster.pop('success', null, 'Acompanhamento salvo com sucesso');
                    $scope.ordemServico.acompanhamentos.push(data);
                    $scope.ordemServico.acompanhamento = null;
                }
            )
        }
        
        $scope.cancelar = function () {
        	$scope.confirm('Ordem de serviço', 'Deseja cancelar a ordem de serviço?', 'Não', 'Sim', $scope.confirmacaoCancelamento);
        };
        
        $scope.confirmacaoCancelamento = function () {
        	OrdemServicoResource.cancelar({id : $scope.ordemServico.id},
        		function(data) {
        			$scope.ordemServico.versao = data.versao;
        			$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                	toaster.pop('success', null, 'Ordem de Serviço cancelada com sucesso');
            	}
        	)        	
        }
        
        $scope.enviarEmailAberturaOrdemServico = function (id) {           
            OrdemServicoResource.enviarEmailAberturaOrdemServico({id: id},
                function(data) {      
                    toaster.pop('success', null, 'E-mail enviado com sucesso');
                }
            )
        }; 

        $scope.iniciarOrcamento = function () {
        	$scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
        	OrdemServicoResource.iniciarOrcamento($scope.ordemServico,
                function(data) {                    
	    			$scope.ordemServico.versao = data.versao;
	    			$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Orçamento iniciado com sucesso');
                }
            )
        };

        $scope.finalizarOrcamento = function () {
            $scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
            OrdemServicoResource.finalizarOrcamento($scope.ordemServico,
                function(data) {                    
    				$scope.ordemServico.versao = data.versao;
    				$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Orçamento finalizado com sucesso');
                }
            )
        };

        $scope.aprovarOrcamento = function (id) {
            OrdemServicoResource.aprovarOrcamento({id: id},
                function(data) {                    
    				$scope.ordemServico.versao = data.versao;
    				$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    $scope.ordemServico.aprovado = data.aprovado;
                    toaster.pop('success', null, 'Orçamento aprovado com sucesso');
                }
            )
        };

        $scope.reprovarOrcamento = function (id) {
            OrdemServicoResource.reprovarOrcamento({id: id},
                function(data) {                    
					$scope.ordemServico.versao = data.versao;
					$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                	$scope.ordemServico.aprovado = data.aprovado;
                    toaster.pop('success', null, 'Orçamento reprovado com sucesso');
                }
            )
        };

        $scope.iniciarExecucaoServico = function (id) {
            $scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
            OrdemServicoResource.iniciarExecucaoServico($scope.ordemServico,
                function(data) {                    
					$scope.ordemServico.versao = data.versao;
					$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Execução Serviço iniciado com sucesso');
                }
            )
        };

        $scope.finalizarExecucaoServico = function (id) {
            $scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
            OrdemServicoResource.finalizarExecucaoServico($scope.ordemServico,
                function(data) {                    
					$scope.ordemServico.versao = data.versao;
					$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Execução Serviço finalizado com sucesso');
                }
            )
        };

        $scope.liberarEquipamentoRetirada = function (id) {
            OrdemServicoResource.liberarEquipamentoRetirada({id: id},
                function(data) {                    
					$scope.ordemServico.versao = data.versao;
					$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Equipamento liberado com sucesso');
                }
            )
        };

        $scope.finalizarOrdemServico = function (id) {
            $scope.ordemServico.ordemServicoAcessorio = $scope.getAcessorios();
            $scope.ordemServico.ordemServicoAvaria = $scope.getAvarias();
            OrdemServicoResource.finalizarOrdemServico($scope.ordemServico,
                function(data) {                    
					$scope.ordemServico.versao = data.versao;
					$scope.ordemServico.etapaOrdemServicoAtual = data.etapaOrdemServicoAtual;
                    toaster.pop('success', null, 'Ordem de Serviço finalizada com sucesso');
                }
            )
        };   

        $scope.enviarEmailRetirada = function (id) {           
            OrdemServicoResource.enviarEmailRetirada({id: id},
                function(data) {      
                    toaster.pop('success', null, 'E-mail enviado com sucesso');
                }
            )
        };      

        $scope.buscarClientes = function(nomeEmailCpfLike) {
            ClienteResource.query({
                nomeEmailCpfLike : encodeURIComponent(nomeEmailCpfLike)
            }, function(data) {
                $scope.clientes = data;
            });
        };

        $scope.buscarEquipamento = function(nomeEquipamento) {
            EquipamentoResource.query({
                nome : encodeURIComponent(nomeEquipamento)
            }, function(data) {
                $scope.equipamentos = data;
            });
        };

        $scope.incluirCliente = function() {       
            $window.open('#/cliente/0?modal=S', 'Incluir Cliente','width=1100,height=490');
        }

        $scope.incluirEquipamento = function() {       
            $window.open('#/equipamento/0?modal=S', 'Incluir Equipamento','width=1100,height=203');
        }
        
        $scope.openDataPagamentoSinal = function() {
            $scope.ordemServico.dataPagamentoSinalOpened = true;   
        };

        $scope.openDataPagamentoRestante = function() {
            $scope.ordemServico.dataPagamentoRestanteOpened = true;   
        };

        $scope.getAcessorios = function () {
            ordemServicoAcessorio = [];            
            angular.forEach($scope.ordemServico.acessorios, function(acessorio, index) {
                ordemServicoAcessorio.push({acessorio: acessorio});               
            });
            return ordemServicoAcessorio;
        };

        $scope.getAvarias = function () {
            ordemServicoAvaria = [];            
            angular.forEach($scope.ordemServico.avarias, function(avaria, index) {
                ordemServicoAvaria.push({avaria: avaria});               
            });
            return ordemServicoAvaria;
        };
        
        $scope.imprimirEntradaEquipamento = function (id) {
        	$window.open('api/ordemServico/'+id+'/exportar/entradaEquipamento/pdf');
        };
        
        $scope.imprimirSaidaEquipamento = function (id) {
        	$window.open('api/ordemServico/'+id+'/exportar/saidaEquipamento/pdf');
        };
        
        $scope.initOrdemServico = function (ordemServico) {
        	if(ordemServico){                
        		$scope.ordemServico = ordemServico;
        		$scope.ordemServico.acessorios = ordemServicoAcessorios;
        		$scope.ordemServico.avarias = ordemServicoAvarias;
                $scope.ordemServico.acompanhamentos = acompanhamentos;
                if(ordemServico.dataPagamentoSinal){
                    $scope.ordemServico.dataPagamentoSinal = new Date(ordemServico.dataPagamentoSinal);                    
                }
                if(ordemServico.dataPagamentoRestante){
                    $scope.ordemServico.dataPagamentoRestante = new Date(ordemServico.dataPagamentoRestante);                    
                }
        	}else{
                $scope.ordemServico = {};
            }
        };
        
        $scope.initOrdemServico(ordemServico);
        $scope.acessorios = acessorios;
        $scope.avarias = avarias;
        $scope.listaEtapasOrdemServico = LISTA_ETAPAS_ORDEM_SERVICO
    }
]);