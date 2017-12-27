appResource.factory(

  // resource name
  'ConfiguracaoSistemaResource', 

  ['$resource',

function($resource) {

  // http://code.angularjs.org/1.2.1/docs/api/ngResource.$resource
  var rest = $resource(
    'api/configuracao/:id',
    {
      'id': ''
    }, 
    {
    	update: { method: 'PUT' }
    }
  );

  return rest;

}]);