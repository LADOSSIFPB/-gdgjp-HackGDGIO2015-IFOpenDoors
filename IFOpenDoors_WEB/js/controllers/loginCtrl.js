angular.module("IfOpenDoorsApp").controller("loginCtrl", function ($scope, $http, $location, appAPI) {
	$scope.UNAUTHORIZED = false;
  $scope.app = "IfOpenDoors";
  $scope.logado = appAPI.isLogged();

  $scope.loginValidation = function (login) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
      $scope.UNAUTHORIZED = false;

      appAPI.login();
      $scope.logado = appAPI.isLogged();

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