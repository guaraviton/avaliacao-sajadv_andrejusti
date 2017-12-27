appResource.factory('ResponsavelResource', [ '$resource', ResponsavelResource]);

function ResponsavelResource($resource) {
	var urlBase = 'api/responsavel/:id';
	var rest = $resource(urlBase,{
		'id': ''
		}, {    	      
		}
	);
	return rest;
}