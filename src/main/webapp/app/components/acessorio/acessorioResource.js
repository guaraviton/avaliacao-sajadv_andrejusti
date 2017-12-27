appResource.factory(

  'AcessorioResource', 

  ['$resource',

function($resource) {

  var rest = $resource(
    'api/acessorio/:id',
    {
      'id': ''
    }, 
    {
    }
  );

  return rest;

}]);