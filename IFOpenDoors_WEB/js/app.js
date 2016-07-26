var app = angular.module("IfOpenDoorsApp", ['ngRoute']);


app.config(['$routeProvider',function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl : 'views/login.html',
		controller     : 'loginCtrl',
	}).
	when('/Salas', {
		templateUrl : 'views/gerenciarSalas.html',
		controller     : 'gerenciarSalasCtrl',
	}).
	when('/AddSala', {
		templateUrl : 'views/adiconarSala.html',
		controller     : 'addSalaCtrl',
	}).
	when('/AddPessoa', {
		templateUrl : 'views/adicionarPessoa.html',
		controller     : 'addPessoaCtrl',
	}).
	otherwise({
		redirectTo: '/'
	});
}]);