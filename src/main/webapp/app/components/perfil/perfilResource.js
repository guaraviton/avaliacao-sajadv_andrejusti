appResource.factory(

  'PerfilResource', 

  ['$resource',

function($resource) {

  var rest = $resource(
    'api/perfil/:id',
    {
      'id': ''
    }, 
    {
    }
  );

  return rest;

}]);