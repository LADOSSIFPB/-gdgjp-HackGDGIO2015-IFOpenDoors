var app = angular.module("IfOpenDoorsApp", ['ngRoute', 'ngCookies', 'ngSanitize']);

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

	when('/ListagemEsp', {
		templateUrl : 'views/ListagemEsp.html',
		controller     : 'ListagemEspCtrl',
	}).

	when('/AddPessoa', {
		templateUrl : 'views/adicionarPessoa.html',
		controller     : 'addPessoaCtrl',
	}).
	otherwise({
		redirectTo: '/'
	});
}]);

app.directive('tooltip', function(){
	return {
		restrict: 'A',
		link: function(scope, element, attrs){
			if(scope.show){
				$(element).tooltip('hide');
			}
			else{
				$(element).tooltip('show');
			}

			$(element).hover(function(){
                // on mouseenter
                if(scope.show){
                	$(element).tooltip('hide');
                }
                else{
                	$(element).tooltip('show');
                }
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });

            $(element).tooltip('hide');
		}
	};
});