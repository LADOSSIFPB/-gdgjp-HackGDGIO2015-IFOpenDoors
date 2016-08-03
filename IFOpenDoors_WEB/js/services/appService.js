angular.module("IfOpenDoorsApp").factory("appAPI", function ($cookies, $location) {

	var _isLogged = function () {
		if ($cookies.getObject('user')) {
			return true;
		} else {
			return false;
		}
	};

	var _login = function (person) {
		$cookies.putObject('user', person);
	};

	var _logout = function () {
		$cookies.remove("user");
		$location.url("/");
	};

	var _getRole = function () {
		var person = $cookies.getObject('user');
		
		return person.role.name;
	};

	return {
		isLogged: _isLogged,
		login: _login,
		logout: _logout,
		getRole:_getRole
	};
});