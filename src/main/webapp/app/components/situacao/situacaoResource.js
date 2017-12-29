appResource.factory('SituacaoResource', [ '$resource', SituacaoResource]);

function SituacaoResource($resource) {
	var urlBase = 'api/situacao/:id';
	var rest = $resource(urlBase,{
		'id': ''
		}, {    	      
		}
	);
	return rest;
}