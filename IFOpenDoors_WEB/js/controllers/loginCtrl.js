angular.module("IfOpenDoorsApp").controller("loginCtrl", function ($scope, $http, $location, appAPI, $rootScope) {
	$scope.UNAUTHORIZED = false;
  $rootScope.app = "IfOpenDoors";
  $scope.logado = appAPI.isLogged();

  $scope.loginValidation = function (login) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
      $scope.UNAUTHORIZED = false;

      appAPI.login(data);
      $rootScope.logado = appAPI.isLogged();

      for(var i=0;i<data.roles.length;i++){
        //Professor
        if(data.roles[i].id==1){
          $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'});
          $rootScope.pessoasOpcaos.push({name:'Adicionar', link:'AddPessoa'});
        }

        //Aluno
        if(data.roles[i].id==2){
          $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'});
        }
      }

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