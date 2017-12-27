appResource.factory(

  // resource name
  'EmailMarketingResource', 

  ['$resource',

function($resource) {

  var rest = $resource(
    'api/emailMarketing/:id',
    {
      'id': ''
    }, 
    {
    	enviar: {method: 'PUT', url:'api/emailMarketing/enviar'}      
    }
  );

  return rest;

}]);