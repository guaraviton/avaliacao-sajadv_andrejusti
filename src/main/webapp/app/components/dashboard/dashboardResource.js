appResource.factory(

  'DashBoardResource', 

  ['$resource',

function($resource) {
  var urlBase = 'api/dashBoard';
  var rest = $resource(
    urlBase,
    {
    }, 
    {
      numeroOrdensServicoAtendidas: {method: 'GET', url: urlBase + '/numeroOrdensServicoAtendidas'},
      ordensServicoPorEquipamento: {isArray: true, method: 'GET', url: urlBase + '/ordensServicoPorEquipamento'},
      ordensServicoPorMesAno: {isArray: true, method: 'GET', url: urlBase + '/ordensServicoPorMesAno', params: {ano: '@ano'}},
      faturamentoMesAno: {isArray: true, method: 'GET', url: urlBase + '/faturamentoMesAno', params: {ano: '@ano'}}
    }
  );

  return rest;

}]);