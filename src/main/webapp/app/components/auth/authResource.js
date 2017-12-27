appResource.factory('AuthResource', [ '$resource', AuthResource]);

function AuthResource($resource) {
	var urlBase = 'api/auth';
	var rest = $resource(urlBase, {
	}, {
		login : {
			method : 'POST'
		},
		logout : {
			method : 'DELETE'
		},
		refresh : {
			url : urlBase + '/refresh'
		}
	});
	return rest;
}