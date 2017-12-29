appResource.factory('ResponsavelResource', [ '$resource', ResponsavelResource]);

function ResponsavelResource($resource) {
	var urlBase = 'api/responsavel/:id';
	var rest = $resource(urlBase,{
		'id': ''
		}, {   
			processos: {isArray: true, method: 'GET', url: urlBase + '/processos', params: {id: '@id'}}
		}
	);
	return rest;
}