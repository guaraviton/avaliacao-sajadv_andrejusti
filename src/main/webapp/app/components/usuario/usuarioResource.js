appResource.factory(

  'UsuarioResource', 

  ['$resource',

function($resource) {

  var urlBase = 'api/usuario/:id';
  var rest = $resource(
    urlBase,
    {
      'id': ''
    }, 
    {    	      
    	perfis: {isArray: true, method: 'GET', url: urlBase + '/perfis', params: {id: '@id'}}
    }
  );

  return rest;

}]);