angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http, appAPI, $rootScope) {
  $scope.app = "IfOpenDoors";
  $rootScope.logado = false;
  $rootScope.salasOpcaos = [];
  $rootScope.pessoasOpcaos = [];

  $scope.barra = function () {
    $rootScope.show = !$scope.show;
    $rootScope.shows = '';
  };

  $scope.logout = function () {
    appAPI.logout();
  };

  $scope.salasOptions = function () {
    $rootScope.shows = 'sala';
  };

  $scope.pessoasOptions = function () {
    $rootScope.shows = 'pessoa';
  };
});