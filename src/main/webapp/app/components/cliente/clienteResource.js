appResource.factory(

  'ClienteResource', 

  ['$resource',

function($resource) {

  var urlBase = 'api/cliente/:id';
  var rest = $resource(
    urlBase,
    {
      'id': ''
    }, 
    {    	      
    }
  );

  return rest;

}]);