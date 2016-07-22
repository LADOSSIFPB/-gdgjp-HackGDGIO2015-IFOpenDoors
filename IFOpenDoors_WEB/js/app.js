var app = angular.module("IfOpenDoorsApp", ['ngRoute']);


app.config(['$routeProvider',function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl : 'views/login.html',
		controller     : 'loginCtrl',
	}).
	otherwise({
		redirectTo: '/'
	});
}]);