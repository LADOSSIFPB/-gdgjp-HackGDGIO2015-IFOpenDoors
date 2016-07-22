angular.module("IfOpenDoorsApp").factory("appAPI", function () {
	var _logado = false;

	var _isLogged = function () {
		return _logado;
	};

	var _login = function () {
		return _logado = true;
	};

	var _logout = function () {
		return _logado = false;
	};

	return {
		isLogged: _isLogged,
		login: _login,
		logout: _logout
	};
});