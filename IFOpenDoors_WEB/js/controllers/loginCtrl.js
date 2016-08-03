angular.module("IfOpenDoorsApp").controller("loginCtrl", function ($scope, $http, $location, appAPI, $rootScope, $cookies, $sce, $window) {
  $rootScope.app = "IfOpenDoors";
  $rootScope.logado = appAPI.isLogged();

  if(appAPI.isLogged()){
    $rootScope.setRoleInBar();
    $window.history.back();
  }

  $scope.loginValidation = function (login) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
      $rootScope.ERROR.ative = false;
      $rootScope.ERROR.data = "";

      appAPI.login(data);
      $rootScope.logado = appAPI.isLogged();

      $rootScope.setRoleInBar();

      $location.url("Salas");
    }).error( function (data, status, headers, config) {
      if(status==401){
        data = "<strong>Acesso Negado!</strong> Email ou senha incorreto.";

        $rootScope.alertERROR(data);
      }
      else{
        data = "<strong>Ocorreu algum problema!</strong> Por favor tente novamente mais tarde.";

        $rootScope.alertERROR(data);
      }
    });
  };
});