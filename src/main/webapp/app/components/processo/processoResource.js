appResource.factory('ProcessoResource', [ '$resource', ProcessoResource]);

function ProcessoResource($resource) {
	var urlBase = 'api/processo/:id';
	var rest = $resource(urlBase,{
		'id': ''
		}, {   
			vinculados: {isArray: true, method: 'GET', url: urlBase + '/vinculados', params: {id: '@id'}},
			responsaveis: {isArray: true, method: 'GET', url: urlBase + '/responsaveis', params: {id: '@id'}}	
		}
	);
	return rest;
}