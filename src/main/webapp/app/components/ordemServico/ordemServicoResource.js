appResource.factory(

  'OrdemServicoResource', 

  ['$resource',

function($resource) {
	  var urlBase = 'api/ordemServico/:id';
  var rest = $resource(
		  urlBase,
    {
      'id': ''
    }, 
    {     	
    	acessorios: {isArray: true, method: 'GET', url: urlBase + '/acessorios', params: {id: '@id'}},
    	avarias: {isArray: true, method: 'GET', url: urlBase + '/avarias', params: {id: '@id'}},
    	ativas: {isArray: true, method: 'GET', url: urlBase + '/ativas'},
    	enviarEmailAberturaOrdemServico: {method: 'PUT', url: urlBase + '/enviarEmailAberturaOrdemServico', params: {id: '@id'}},
    	iniciarOrcamento: {method: 'PUT', url: urlBase + '/orcamento/iniciar', params: {id: '@id'}},  
    	cancelar: {method: 'DELETE', url: urlBase + '/cancelar', params: {id: '@id'}},
    	finalizarOrcamento: {method: 'PUT', url: urlBase + '/orcamento/finalizar', params: {id: '@id'}},     
    	aprovarOrcamento: {method: 'PUT', url: urlBase + '/orcamento/aprovar', params: {id: '@id'}},      
    	reprovarOrcamento: {method: 'PUT', url: urlBase + '/orcamento/reprovar', params: {id: '@id'}},      
    	iniciarExecucaoServico: {method: 'PUT', url: urlBase + '/iniciarExecucaoServico', params: {id: '@id'}},
    	finalizarExecucaoServico: {method: 'PUT', url: urlBase + '/finalizarExecucaoServico', params: {id: '@id'}},
    	liberarEquipamentoRetirada: {method: 'PUT', url: urlBase + '/liberarEquipamentoRetirada', params: {id: '@id'}},
    	finalizarOrdemServico: {method: 'PUT', url: urlBase + '/finalizarOrdemServico', params: {id: '@id'}},
    	timeline: {isArray: true, method: 'GET', url: urlBase + '/timeline', params: {id: '@id'}},
    	enviarEmailRetirada: {method: 'PUT', url: urlBase + '/enviarEmailRetirada', params: {id: '@id'}},
    	acompanhamento: {method: 'POST', url: urlBase + '/acompanhamento', params: {id: '@id'}},
      acompanhamentos: {isArray: true, method: 'GET', url: urlBase + '/acompanhamento', params: {id: '@id'}}
    }
  );

  return rest;

}]);