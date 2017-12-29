appResource.factory('ProcessoResource', [ '$resource', ProcessoResource]);

function ProcessoResource($resource) {
	var urlBase = 'api/processo/:id';
	var rest = $resource(urlBase,{
		'id': ''
		}, {    	      
		}
	);
	return rest;
}