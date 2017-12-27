appResource.factory(

  'EquipamentoResource', 

  ['$resource',

function($resource) {

  var rest = $resource(
    'api/equipamento/:id',
    {
      'id': ''
    }, 
    {
    }
  );

  return rest;

}]);