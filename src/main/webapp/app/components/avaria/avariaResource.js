appResource.factory(

  'AvariaResource', 

  ['$resource',

function($resource) {

  var rest = $resource(
    'api/avaria/:id',
    {
      'id': ''
    }, 
    {
    }
  );

  return rest;

}]);