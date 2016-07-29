angular.module("IfOpenDoorsApp").controller("loginCtrl", function ($scope, $http, $location, appAPI, $rootScope, $cookies) {
	$scope.UNAUTHORIZED = false;
  $rootScope.app = "IfOpenDoors";
  $rootScope.logado = appAPI.isLogged();

  $scope.setRoleInBar = function(data){
    $rootScope.salasOpcaos = [];
    $rootScope.pessoasOpcaos = [];

    if(data.role.name == "Professor"){
        $rootScope.role = "Professor";
        $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'});
        $rootScope.pessoasOpcaos.push({name:'Adicionar', link:'AddPessoa'});
      }

      if(data.role.name == "Aluno"){
        $rootScope.role = "Aluno";
        $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'});
      }
  }

  if($rootScope.logado){
    $scope.setRoleInBar($cookies.getObject('user'));
    $location.url("/Salas");
  }

  $scope.loginValidation = function (login) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
      $scope.UNAUTHORIZED = false;

      appAPI.login(data);
      $rootScope.logado = appAPI.isLogged();

      $scope.setRoleInBar(data);

      $location.url("Salas");
    }).error( function (data, status, headers, config) {
      if(status==401){
        $scope.UNAUTHORIZED = true;
      }
    });
  };

  $scope.closeAlert = function () {
    $scope.UNAUTHORIZED = false;
  };
});